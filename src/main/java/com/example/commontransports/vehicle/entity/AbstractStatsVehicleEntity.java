package com.example.commontransports.vehicle.entity;

import com.example.commontransports.api.entity.DurableVehicle;
import com.example.commontransports.api.entity.FuelableVehicle;
import com.example.commontransports.api.entity.HasDropItem;
import com.example.commontransports.api.entity.StatsDrivenVehicle;
import com.example.commontransports.api.entity.VehicleStats;

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
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.damagesource.DamageSource;
import org.jspecify.annotations.Nullable;

/**
 * Abstract base for vehicles driven by {@link VehicleStats}. Handles movement, fuel,
 * durability, passengers, and interaction. Subclasses provide stats, drop item,
 * and fuel/durability data accessors.
 */
public abstract class AbstractStatsVehicleEntity extends BaseVehicleEntity
        implements StatsDrivenVehicle, FuelableVehicle, DurableVehicle, HasDropItem {

    protected static final double MODEL_Y_OFFSET = 0.5;

    protected float currentSpeed = 0.0f;
    protected float currentLean = 0.0f;
    protected float wheelRotation = 0.0f;
    protected float fuelAccumulator = 0.0f;
    protected boolean hadRiderLastTick = false;

    protected AbstractStatsVehicleEntity(EntityType<? extends BaseVehicleEntity> type, Level level) {
        super(type, level);
        this.blocksBuilding = true;
    }

    @Override
    public abstract VehicleStats getStats();

    @Override
    public abstract Item getDropItem();

    @Override
    public abstract boolean isFuelItem(ItemStack stack);

    @Override
    public abstract int getFuel();
    @Override
    public abstract void setFuel(int value);
    @Override
    public abstract int getDurability();
    @Override
    public abstract void setDurability(int value);

    @Override
    public float maxUpStep() {
        return 1.0f;
    }

    @Override
    public void tick() {
        super.tick();
        VehicleStats stats = getStats();

        LivingEntity rider = getControllingPassenger();
        if (rider != null) {
            if (!hadRiderLastTick) {
                currentSpeed = 0.0f;
                setDeltaMovement(Vec3.ZERO);
            }
            handleRiderInput(rider);
        } else {
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

    protected void handleRiderInput(LivingEntity rider) {
        VehicleStats stats = getStats();
        Vec3 move = getRiderMoveVector(rider);
        float forward = (float) move.z;
        float strafe = (float) move.x;
        setXRot(0.0f);
        boolean hasFuel = getFuel() > 0;
        boolean accelerating = forward > 0f && hasFuel;
        boolean reversing = forward < 0f && hasFuel;
        float targetSpeed = accelerating ? stats.maxSpeed : (reversing ? -stats.maxReverseSpeed : 0.0f);
        float step = accelerating || reversing ? stats.acceleration : stats.drag;
        if ((reversing && currentSpeed > 0.0f) || (accelerating && currentSpeed < 0.0f)) step = stats.deceleration;
        currentSpeed = approach(currentSpeed, targetSpeed, step);

        if (!level().isClientSide() && Math.abs(currentSpeed) > 0.01f && hasFuel) consumeFuel(Math.abs(currentSpeed));

        double forwardDir = currentSpeed >= 0.0f ? 1.0 : -1.0;
        float speedRatio = Mth.clamp(Math.abs(currentSpeed) / stats.maxSpeed, 0.0f, 1.0f);
        float directTurnStrength = 1.0f - Mth.clamp(speedRatio / stats.lowSpeedThreshold, 0.0f, 1.0f);
        float leanTurnStrength = Mth.clamp((speedRatio - 0.1f) / (stats.lowSpeedThreshold - 0.1f), 0.0f, 1.0f);
        float directTurn = -strafe * stats.directTurnRate * directTurnStrength;
        float targetLean = Mth.clamp(strafe * stats.maxLeanAngle * leanTurnStrength, -stats.maxLeanAngle, stats.maxLeanAngle);
        currentLean = approach(currentLean, targetLean, stats.leanSpeed);
        float leanRatio = currentLean / stats.maxLeanAngle;
        float leanTurn = -leanRatio * stats.turnRateDegrees * speedRatio;
        setYRot(getYRot() + directTurn + leanTurn);

        Vec3 inputDir = new Vec3(0.0, 0.0, forwardDir);
        if (inputDir.lengthSqr() > 1.0) inputDir = inputDir.normalize();
        if (inputDir.lengthSqr() == 0.0 && currentSpeed != 0.0f) inputDir = new Vec3(0.0, 0.0, forwardDir);
        float speedMagnitude = Math.abs(currentSpeed);
        Vec3 motion = inputDir.scale(speedMagnitude).yRot(-getYRot() * Mth.DEG_TO_RAD);
        setDeltaMovement(new Vec3(motion.x, getDeltaMovement().y, motion.z));
    }

    protected void consumeFuel(float speed) {
        VehicleStats stats = getStats();
        int current = getFuel();
        if (current > 0) {
            float speedRatio = speed / stats.maxSpeed;
            float consumption = stats.fuelConsumptionPerTick * (1.0f + stats.fuelConsumptionSpeedMultiplier * speedRatio);
            fuelAccumulator += consumption;
            int toConsume = (int) fuelAccumulator;
            if (toConsume > 0) {
                fuelAccumulator -= toConsume;
                setFuel(current - toConsume);
            }
        }
    }

    protected static float approach(float value, float target, float step) {
        return value < target ? Math.min(value + step, target) : Math.max(value - step, target);
    }

    protected void updateWheelRotation() {
        VehicleStats stats = getStats();
        float speed = (float) getDeltaMovement().horizontalDistance();
        wheelRotation += speed * stats.wheelRotationMultiplier;
        if (wheelRotation > Mth.TWO_PI) wheelRotation -= Mth.TWO_PI;
    }

    public float getCurrentLean() { return currentLean; }
    public float getWheelRotation() { return wheelRotation; }
    public float getMaxSpeed() { return getStats().maxSpeed; }

    protected Vec3 getRiderMoveVector(LivingEntity rider) {
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
        if (!hasPassenger(passenger)) return;
        VehicleStats stats = getStats();
        int passengerIndex = getPassengers().indexOf(passenger);
        double yOffset = MODEL_Y_OFFSET + stats.riderYOffset;
        double zOffset = passengerIndex == 0 ? stats.driverZOffset : (stats.driverZOffset + stats.passengerZOffset);
        float yawRad = -getYRot() * Mth.DEG_TO_RAD;
        double xPos = getX() + zOffset * Math.sin(yawRad);
        double zPos = getZ() + zOffset * Math.cos(yawRad);
        moveFunction.accept(passenger, xPos, getY() + yOffset, zPos);
        if (passenger instanceof LivingEntity living) {
            living.setYBodyRot(getYRot());
            living.setYHeadRot(living.getYHeadRot());
        }
    }

    protected double getPassengersRidingOffset() { return MODEL_Y_OFFSET + getStats().riderYOffset; }
    public @Nullable Entity getBackPassenger() { return getPassengers().size() > 1 ? getPassengers().get(1) : null; }

    @Override
    public LivingEntity getControllingPassenger() {
        Entity first = getFirstPassenger();
        return first instanceof LivingEntity living ? living : null;
    }

    @Override
    public boolean isPickable() { return true; }

    @Override
    public boolean canBeCollidedWith(@Nullable Entity entity) { return true; }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float amount) {
        if (level().isClientSide() || isRemoved()) return true;
        if (isInvulnerable()) return false;
        return applyDamage(source, amount);
    }

    protected boolean applyDamage(DamageSource source, float amount) {
        int damage = Math.max(1, Mth.ceil(amount));
        setHurtDir(-getHurtDir());
        setHurtTime(10);
        markHurt();
        setDurability(getDurability() - damage);
        if (getDurability() <= 0) {
            discard();
            dropStack(createDropStack());
        }
        return true;
    }

    /**
     * Creates the item stack to drop, saving current fuel.
     */
    protected ItemStack createDropStack() {
        ItemStack stack = new ItemStack(getDropItem());
        CompoundTag tag = new CompoundTag();
        tag.putInt("Fuel", getFuel());
        tag.putInt("MaxFuel", getStats().maxFuel);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
        return stack;
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        ItemStack held = player.getItemInHand(hand);
        if (player.isSecondaryUseActive()) return InteractionResult.PASS;
        if (isFuelItem(held)) {
            if (level().isClientSide()) return InteractionResult.SUCCESS;
            return handleFueling(player, held);
        }
        if (!player.isPassenger()) {
            if (level().isClientSide()) return InteractionResult.SUCCESS;
            return player.startRiding(this) ? InteractionResult.SUCCESS_SERVER : InteractionResult.PASS;
        }
        return InteractionResult.PASS;
    }

    protected InteractionResult handleFueling(Player player, ItemStack held) {
        if (getFuel() >= getStats().maxFuel) return InteractionResult.PASS;
        return InteractionResult.PASS;
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        output.putInt("Fuel", getFuel());
        output.putInt("Durability", getDurability());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        VehicleStats stats = getStats();
        setFuel(input.getIntOr("Fuel", 0));
        setDurability(input.getIntOr("Durability", stats.maxDurability));
    }

    protected void dropStack(ItemStack stack) {
        if (stack.isEmpty() || level().isClientSide()) return;
        ItemEntity itemEntity = new ItemEntity(level(), getX(), getY() + 0.5, getZ(), stack);
        level().addFreshEntity(itemEntity);
    }
}
