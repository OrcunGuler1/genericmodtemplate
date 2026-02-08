package com.example.commontransports.vehicle.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

/**
 * Base for all mod vehicles. Applies gravity so vehicles fall when in the air.
 */
public abstract class BaseVehicleEntity extends VehicleEntity {

    protected static final double VEHICLE_GRAVITY = 0.08;

    public BaseVehicleEntity(EntityType<? extends VehicleEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected double getDefaultGravity() {
        return VEHICLE_GRAVITY;
    }

    /**
     * Suppressed so vehicles don't produce footstep sounds when moving across blocks.
     */
    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        // no-op: vehicles should not play walking/step sounds
    }

    protected void applyVehicleGravity() {
        if (!onGround()) {
            Vec3 d = getDeltaMovement();
            setDeltaMovement(d.x, d.y - getGravity(), d.z);
        }
    }
}
