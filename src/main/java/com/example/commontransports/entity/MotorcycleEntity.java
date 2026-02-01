package com.example.commontransports.entity;

import com.example.commontransports.GenericMod;
import com.example.commontransports.item.GasCanItem;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Input;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.Nullable;

/**
 * Base motorcycle entity. Uses VehicleStats.MOTORCYCLE for configuration.
 * Extend this class or change getStats() to create different vehicle variants.
 */
public class MotorcycleEntity extends BaseVehicleEntity {
    
    private static final EntityDataAccessor<Integer> FUEL = SynchedEntityData.defineId(MotorcycleEntity.class,
            EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DURABILITY = SynchedEntityData.defineId(MotorcycleEntity.class,
            EntityDataSerializers.INT);

    private SimpleContainer inventory;
    private float currentSpeed = 0.0f;
    private float currentLean = 0.0f;
    private float wheelRotation = 0.0f;
    private float fuelAccumulator = 0.0f;
    private boolean hadRiderLastTick = false;

    public MotorcycleEntity(EntityType<? extends MotorcycleEntity> type, Level level) {
        super(type, level);
        this.blocksBuilding = true;
        this.inventory = new SimpleContainer(getStats().inventorySize);
    }

    /** Matches renderer Y translate so rider/passenger height matches the model. */
    private static final double MODEL_Y_OFFSET = 0.5;

    /** Step height 1 block, same as horses (LivingEntity.maxUpStep / AbstractHorse). */
    @Override
    public float maxUpStep() {
        return 1.0f;
    }

    /**
     * Get the vehicle stats for this entity.
     * Override this method in subclasses to use different stats.
     */
    protected VehicleStats getStats() {
        return VehicleStats.MOTORCYCLE;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(FUEL, 0);
        builder.define(DURABILITY, VehicleStats.MOTORCYCLE.maxDurability); // Use default for initial
    }

    @Override
    public void tick() {
        super.tick();
        VehicleStats stats = getStats();

        LivingEntity rider = getControllingPassenger();
        if (rider != null) {
            // Just mounted: start from standstill so no carried-over momentum
            if (!hadRiderLastTick) {
                currentSpeed = 0.0f;
                setDeltaMovement(Vec3.ZERO);
            }
            handleRiderInput(rider);
            
            // Display fuel level to rider (every 20 ticks = 1 second)
            if (!level().isClientSide() && rider instanceof ServerPlayer serverPlayer && tickCount % 20 == 0) {
                displayFuelLevel(serverPlayer);
            }
        } else {
            // No rider: coast to stop using deceleration params, then zero momentum
            currentSpeed = approach(currentSpeed, 0.0f, stats.deceleration);
            currentLean = approach(currentLean, 0.0f, stats.leanSpeed);
            double forwardDir = currentSpeed >= 0.0f ? 1.0 : -1.0;
            Vec3 inputDir = new Vec3(0.0, 0.0, forwardDir);
            float speedMagnitude = Math.abs(currentSpeed);
            Vec3 motion = inputDir.scale(speedMagnitude).yRot(-getYRot() * Mth.DEG_TO_RAD);
            setDeltaMovement(new Vec3(motion.x, getDeltaMovement().y, motion.z));
        }

        applyVehicleGravity();

        Vec3 delta = getDeltaMovement();
        move(MoverType.SELF, delta);

        // Explicit step-up: vanilla Entity.move() step-up may not run for VehicleEntity in 1.21
        if (horizontalCollision && (delta.x != 0 || delta.z != 0)) {
            double yBeforeStep = getY();
            setPos(getX(), getY() + maxUpStep(), getZ());
            move(MoverType.SELF, new Vec3(delta.x, 0, delta.z));
            if (horizontalCollision) {
                setPos(getX(), yBeforeStep, getZ());
            }
        }

        setDeltaMovement(getDeltaMovement().scale(stats.movementFriction));
        updateWheelRotation();
        hadRiderLastTick = (rider != null);
    }

    private void displayFuelLevel(ServerPlayer player) {
        VehicleStats stats = getStats();
        int fuel = getFuel();
        int fuelPercent = (int) ((float) fuel / stats.maxFuel * 100);
        String fuelBar = createFuelBar(fuelPercent);
        Component message = Component.literal("Fuel: " + fuelBar + " " + fuelPercent + "%");
        player.displayClientMessage(message, true);
    }
    
    private String createFuelBar(int percent) {
        int filled = percent / 10;
        int empty = 10 - filled;
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < filled; i++) {
            bar.append("█");
        }
        for (int i = 0; i < empty; i++) {
            bar.append("░");
        }
        bar.append("]");
        return bar.toString();
    }

    private void handleRiderInput(LivingEntity rider) {
        VehicleStats stats = getStats();
        Vec3 move = getRiderMoveVector(rider);
        float forward = (float) move.z;
        float strafe = (float) move.x;
        
        // Keep bike heading independent of rider look
        setXRot(0.0f);

        // Check if we have fuel
        boolean hasFuel = getFuel() > 0;
        
        boolean accelerating = forward > 0f && hasFuel;
        boolean reversing = forward < 0f && hasFuel;
        float targetSpeed = accelerating ? stats.maxSpeed : (reversing ? -stats.maxReverseSpeed : 0.0f);
        float step = accelerating || reversing ? stats.acceleration : stats.drag;
        if ((reversing && currentSpeed > 0.0f) || (accelerating && currentSpeed < 0.0f)) {
            step = stats.deceleration;
        }
        currentSpeed = approach(currentSpeed, targetSpeed, step);

        // Consume fuel when moving (server-side only) - more speed = more fuel
        if (!level().isClientSide() && Math.abs(currentSpeed) > 0.01f && hasFuel) {
            consumeFuel(Math.abs(currentSpeed));
        }

        double forwardDir = currentSpeed >= 0.0f ? 1.0 : -1.0;
        float speedRatio = Mth.clamp(Math.abs(currentSpeed) / stats.maxSpeed, 0.0f, 1.0f);
        
        // Low-speed maneuvering: allow sharp turns when slow or stationary
        // At low speed, use direct turning; at high speed, use lean-based turning
        float directTurnStrength = 1.0f - Mth.clamp(speedRatio / stats.lowSpeedThreshold, 0.0f, 1.0f);
        float leanTurnStrength = Mth.clamp((speedRatio - 0.1f) / (stats.lowSpeedThreshold - 0.1f), 0.0f, 1.0f);
        
        // Direct turning for low speed (like walking a bike)
        float directTurn = -strafe * stats.directTurnRate * directTurnStrength;
        
        // Lean-based turning for higher speeds
        float targetLean = Mth.clamp(strafe * stats.maxLeanAngle * leanTurnStrength, -stats.maxLeanAngle, stats.maxLeanAngle);
        currentLean = approach(currentLean, targetLean, stats.leanSpeed);
        float leanRatio = currentLean / stats.maxLeanAngle;
        float leanTurn = -leanRatio * stats.turnRateDegrees * speedRatio;
        
        // Combine both turning methods
        float turnAmount = directTurn + leanTurn;
        setYRot(getYRot() + turnAmount);

        Vec3 inputDir = new Vec3(0.0, 0.0, forwardDir);
        if (inputDir.lengthSqr() > 1.0) {
            inputDir = inputDir.normalize();
        }
        if (inputDir.lengthSqr() == 0.0 && currentSpeed != 0.0f) {
            inputDir = new Vec3(0.0, 0.0, forwardDir);
        }

        float speedMagnitude = Math.abs(currentSpeed);
        Vec3 motion = inputDir.scale(speedMagnitude).yRot(-getYRot() * Mth.DEG_TO_RAD);
        setDeltaMovement(new Vec3(motion.x, getDeltaMovement().y, motion.z));
    }
    
    private void consumeFuel(float speed) {
        VehicleStats stats = getStats();
        int current = getFuel();
        if (current > 0) {
            // Base consumption + speed-scaled consumption
            float speedRatio = speed / stats.maxSpeed;
            float consumption = stats.fuelConsumptionPerTick * (1.0f + stats.fuelConsumptionSpeedMultiplier * speedRatio);
            
            // Accumulate fractional fuel and consume whole units
            fuelAccumulator += consumption;
            int toConsume = (int) fuelAccumulator;
            if (toConsume > 0) {
                fuelAccumulator -= toConsume;
                setFuel(current - toConsume);
            }
        }
    }

    private static float approach(float value, float target, float step) {
        if (value < target) {
            return Math.min(value + step, target);
        }
        return Math.max(value - step, target);
    }

    private void updateWheelRotation() {
        VehicleStats stats = getStats();
        float speed = (float) getDeltaMovement().horizontalDistance();
        wheelRotation += speed * stats.wheelRotationMultiplier;
        if (wheelRotation > Mth.TWO_PI) {
            wheelRotation -= Mth.TWO_PI;
        }
    }

    public float getCurrentLean() {
        return currentLean;
    }

    public float getWheelRotation() {
        return wheelRotation;
    }
    
    public float getMaxSpeed() {
        return getStats().maxSpeed;
    }

    private Vec3 getRiderMoveVector(LivingEntity rider) {
        if (!level().isClientSide() && rider instanceof ServerPlayer serverPlayer) {
            Input input = serverPlayer.getLastClientInput();
            float forward = input.forward() == input.backward() ? 0.0f : (input.forward() ? 1.0f : -1.0f);
            float strafe = input.left() == input.right() ? 0.0f : (input.left() ? 1.0f : -1.0f);
            return new Vec3(strafe, 0.0, forward);
        }
        return new Vec3(rider.xxa, 0.0, rider.zza);
    }

    @Override
    public boolean canAddPassenger(Entity passenger) {
        return getPassengers().size() < getStats().maxPassengers && passenger instanceof LivingEntity;
    }

    @Override
    protected void positionRider(Entity passenger, MoveFunction moveFunction) {
        if (!hasPassenger(passenger)) {
            return;
        }
        
        VehicleStats stats = getStats();
        int passengerIndex = getPassengers().indexOf(passenger);
        
        // Driver sits at configured offset, passenger sits behind driver (Y includes model offset so rider matches visual seat)
        double yOffset = MODEL_Y_OFFSET + stats.riderYOffset;
        double zOffset = passengerIndex == 0 ? stats.driverZOffset : (stats.driverZOffset + stats.passengerZOffset);
        
        // Apply offset relative to bike rotation
        float yawRad = -getYRot() * Mth.DEG_TO_RAD;
        double xPos = getX() + zOffset * Math.sin(yawRad);
        double zPos = getZ() + zOffset * Math.cos(yawRad);
        
        moveFunction.accept(passenger, xPos, getY() + yOffset, zPos);
        
        // Make passenger face same direction as bike
        if (passenger instanceof LivingEntity living) {
            living.setYBodyRot(getYRot());
            living.setYHeadRot(living.getYHeadRot()); // Keep head free to look around
        }
    }

    protected double getPassengersRidingOffset() {
        return MODEL_Y_OFFSET + getStats().riderYOffset;
    }
    
    /**
     * Gets the passenger riding on the back (not the driver).
     * @return The passenger entity, or null if no passenger
     */
    public @Nullable Entity getBackPassenger() {
        return getPassengers().size() > 1 ? getPassengers().get(1) : null;
    }

    @Override
    public LivingEntity getControllingPassenger() {
        Entity first = getFirstPassenger();
        return first instanceof LivingEntity living ? living : null;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public boolean canBeCollidedWith(@Nullable Entity entity) {
        return true;
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        ItemStack held = player.getItemInHand(hand);
        if (player.isSecondaryUseActive()) {
            return handleInventoryInteract(player, hand);
        }

        if (isFuelItem(held)) {
            return handleFueling(player, held);
        }

        if (!player.isPassenger()) {
            if (level().isClientSide()) {
                return InteractionResult.SUCCESS;
            }
            return player.startRiding(this) ? InteractionResult.SUCCESS_SERVER : InteractionResult.PASS;
        }

        return InteractionResult.PASS;
    }

    private InteractionResult handleFueling(Player player, ItemStack held) {
        VehicleStats stats = getStats();
        if (getFuel() >= stats.maxFuel) {
            return InteractionResult.PASS;
        }

        // Only gas cans can fuel the motorcycle (must contain petrol from refinery)
        if (held.getItem() instanceof GasCanItem) {
            int canFuel = GasCanItem.getFluidAmount(held);
            if (canFuel <= 0) {
                return InteractionResult.PASS; // Gas can is empty
            }
            
            int spaceInTank = stats.maxFuel - getFuel();
            int toTransfer = Math.min(canFuel, spaceInTank);
            
            if (toTransfer > 0 && !player.getAbilities().instabuild) {
                GasCanItem.consumeFuel(held, toTransfer);
            }
            setFuel(getFuel() + toTransfer);
            gameEvent(GameEvent.ENTITY_INTERACT);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    private InteractionResult handleInventoryInteract(Player player, InteractionHand hand) {
        ItemStack held = player.getItemInHand(hand);
        if (held.isEmpty()) {
            ItemStack extracted = extractOneItem();
            if (!extracted.isEmpty()) {
                player.addItem(extracted);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }

        ItemStack remainder = addToInventory(held);
        if (remainder.getCount() != held.getCount()) {
            player.setItemInHand(hand, remainder);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    private boolean isFuelItem(ItemStack stack) {
        return stack.is(GenericMod.GAS_CAN.get());
    }

    private ItemStack addToInventory(ItemStack stack) {
        ItemStack remaining = stack.copy();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack slot = inventory.getItem(i);
            if (slot.isEmpty()) {
                inventory.setItem(i, remaining.split(remaining.getCount()));
                return remaining;
            }
            if (ItemStack.isSameItemSameComponents(slot, remaining) && slot.getCount() < slot.getMaxStackSize()) {
                int transfer = Math.min(remaining.getCount(), slot.getMaxStackSize() - slot.getCount());
                if (transfer > 0) {
                    slot.grow(transfer);
                    remaining.shrink(transfer);
                }
                if (remaining.isEmpty()) {
                    return remaining;
                }
            }
        }
        return remaining;
    }

    private ItemStack extractOneItem() {
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack slot = inventory.getItem(i);
            if (!slot.isEmpty()) {
                ItemStack extracted = slot.split(1);
                if (slot.isEmpty()) {
                    inventory.setItem(i, ItemStack.EMPTY);
                }
                return extracted;
            }
        }
        return ItemStack.EMPTY;
    }

    public int getFuel() {
        return entityData.get(FUEL);
    }

    public void setFuel(int value) {
        entityData.set(FUEL, Math.max(0, value));
    }

    public int getDurability() {
        return entityData.get(DURABILITY);
    }

    public void setDurability(int value) {
        VehicleStats stats = getStats();
        entityData.set(DURABILITY, Mth.clamp(value, 0, stats.maxDurability));
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float amount) {
        if (isRemoved()) {
            return false;
        }
        int damage = Math.max(1, Mth.ceil(amount));
        setDurability(getDurability() - damage);
        if (getDurability() <= 0) {
            discard();
            dropStack(new ItemStack(getDropItem()));
        }
        return true;
    }

    @Override
    public void remove(RemovalReason reason) {
        if (!level().isClientSide()) {
            dropInventory();
        }
        super.remove(reason);
    }

    private void dropInventory() {
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                dropStack(stack);
            }
        }
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        output.putInt("Fuel", getFuel());
        output.putInt("Durability", getDurability());
        ValueOutput.TypedOutputList<ItemStack> list = output.list("Inventory", ItemStack.CODEC);
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                list.add(stack);
            }
        }
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        VehicleStats stats = getStats();
        setFuel(input.getIntOr("Fuel", 0));
        setDurability(input.getIntOr("Durability", stats.maxDurability));
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            inventory.setItem(i, ItemStack.EMPTY);
        }
        int index = 0;
        for (ItemStack stack : input.listOrEmpty("Inventory", ItemStack.CODEC)) {
            if (index >= inventory.getContainerSize()) {
                break;
            }
            inventory.setItem(index, stack);
            index++;
        }
    }

    @Override
    protected Item getDropItem() {
        return GenericMod.MOTORCYCLE_ITEM.get();
    }

    private void dropStack(ItemStack stack) {
        if (stack.isEmpty() || level().isClientSide()) {
            return;
        }
        ItemEntity itemEntity = new ItemEntity(level(), getX(), getY() + 0.5, getZ(), stack);
        level().addFreshEntity(itemEntity);
    }
}
