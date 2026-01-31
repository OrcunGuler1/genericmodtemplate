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
import net.minecraft.world.entity.vehicle.VehicleEntity;
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

public class MotorcycleEntity extends VehicleEntity {
    public static final int MAX_FUEL = 10000;
    public static final int FUEL_PER_CAN = 1000;
    public static final int FUEL_CONSUMPTION_PER_TICK = 1; // Consume 1 fuel per tick while moving
    public static final int MAX_DURABILITY = 256;
    public static final int INVENTORY_SIZE = 9;
    private static final float MAX_SPEED = 2.5f;
    private static final float MAX_REVERSE_SPEED = 0.05f;
    private static final float ACCELERATION = 0.08f;
    private static final float DECELERATION = 0.12f;
    private static final float DRAG = 0.03f;
    private static final float TURN_RATE_DEGREES = 4.0f;
    private static final float WHEEL_ROTATION_MULT = 20.0f;

    public static float getMaxSpeed() {
        return MAX_SPEED;
    }

    private static final EntityDataAccessor<Integer> FUEL = SynchedEntityData.defineId(MotorcycleEntity.class,
            EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DURABILITY = SynchedEntityData.defineId(MotorcycleEntity.class,
            EntityDataSerializers.INT);

    private final SimpleContainer inventory = new SimpleContainer(INVENTORY_SIZE);
    private float currentSpeed = 0.0f;
    private float currentLean = 0.0f;
    private float wheelRotation = 0.0f;

    public MotorcycleEntity(EntityType<? extends MotorcycleEntity> type, Level level) {
        super(type, level);
        this.blocksBuilding = true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(FUEL, 0);
        builder.define(DURABILITY, MAX_DURABILITY);
    }

    @Override
    public void tick() {
        super.tick();

        LivingEntity rider = getControllingPassenger();
        if (rider != null) {
            handleRiderInput(rider);
            
            // Display fuel level to rider (every 20 ticks = 1 second)
            if (!level().isClientSide() && rider instanceof ServerPlayer serverPlayer && tickCount % 20 == 0) {
                displayFuelLevel(serverPlayer);
            }
        } else {
            setDeltaMovement(getDeltaMovement().scale(0.6));
        }

        move(MoverType.SELF, getDeltaMovement());
        setDeltaMovement(getDeltaMovement().scale(0.9));
        updateWheelRotation();
    }
    
    private void displayFuelLevel(ServerPlayer player) {
        int fuel = getFuel();
        int fuelPercent = (int) ((float) fuel / MAX_FUEL * 100);
        String fuelBar = createFuelBar(fuelPercent);
        Component message = Component.literal("Fuel: " + fuelBar + " " + fuelPercent + "%");
        player.displayClientMessage(message, true); // true = action bar
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
        Vec3 move = getRiderMoveVector(rider);
        float forward = (float) move.z;
        float strafe = (float) move.x;
        
        // Keep bike heading independent of rider look
        setXRot(0.0f);

        // Check if we have fuel
        boolean hasFuel = getFuel() > 0;
        
        boolean accelerating = forward > 0f && hasFuel;
        boolean reversing = forward < 0f && hasFuel;
        float targetSpeed = accelerating ? MAX_SPEED : (reversing ? -MAX_REVERSE_SPEED : 0.0f);
        float step = accelerating || reversing ? ACCELERATION : DRAG;
        if ((reversing && currentSpeed > 0.0f) || (accelerating && currentSpeed < 0.0f)) {
            step = DECELERATION;
        }
        currentSpeed = approach(currentSpeed, targetSpeed, step);

        // Consume fuel when moving (server-side only) - more speed = more fuel
        if (!level().isClientSide() && Math.abs(currentSpeed) > 0.01f && hasFuel) {
            consumeFuel(Math.abs(currentSpeed));
        }

        double forwardDir = currentSpeed >= 0.0f ? 1.0 : -1.0;
        float speedRatio = Mth.clamp(Math.abs(currentSpeed) / MAX_SPEED, 0.0f, 1.0f);
        float targetLean = Mth.clamp(strafe * 65.0f * speedRatio, -65.0f, 65.0f);
        currentLean = approach(currentLean, targetLean, 3.0f);
        float leanRatio = currentLean / 65.0f;
        float turnAmount = -leanRatio * TURN_RATE_DEGREES * speedRatio;
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
    
    private float fuelAccumulator = 0.0f;
    
    private void consumeFuel(float speed) {
        int current = getFuel();
        if (current > 0) {
            // Base consumption + speed-scaled consumption (up to 5x at max speed)
            float speedRatio = speed / MAX_SPEED;
            float consumption = FUEL_CONSUMPTION_PER_TICK * (1.0f + 4.0f * speedRatio);
            
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
        float speed = (float) getDeltaMovement().horizontalDistance();
        wheelRotation += speed * WHEEL_ROTATION_MULT;
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
        // Allow driver + 1 passenger (2 total)
        return getPassengers().size() < 2 && passenger instanceof LivingEntity;
    }

    @Override
    protected void positionRider(Entity passenger, MoveFunction moveFunction) {
        if (!hasPassenger(passenger)) {
            return;
        }
        
        int passengerIndex = getPassengers().indexOf(passenger);
        
        // Driver sits at front, passenger sits behind
        double yOffset = getPassengersRidingOffset();
        double zOffset = passengerIndex == 0 ? 0.0 : -0.6; // Passenger sits 0.6 blocks behind driver
        
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
        return 0.65;
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
        if (getFuel() >= MAX_FUEL) {
            return InteractionResult.PASS;
        }

        // Only gas cans can fuel the motorcycle (must contain petrol from refinery)
        if (held.getItem() instanceof GasCanItem) {
            int canFuel = GasCanItem.getFluidAmount(held);
            if (canFuel <= 0) {
                return InteractionResult.PASS; // Gas can is empty
            }
            
            int spaceInTank = MAX_FUEL - getFuel();
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
        entityData.set(DURABILITY, Mth.clamp(value, 0, MAX_DURABILITY));
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
            dropStack(new ItemStack(GenericMod.MOTORCYCLE_ITEM.get()));
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
        setFuel(input.getIntOr("Fuel", 0));
        setDurability(input.getIntOr("Durability", MAX_DURABILITY));
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
