package com.example.commontransports.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * Base for all mod vehicles. Applies gravity so vehicles fall when in the air.
 */
public abstract class BaseVehicleEntity extends VehicleEntity {

    /** Gravity used by all vehicles (same as players). */
    protected static final double VEHICLE_GRAVITY = 0.08;

    public BaseVehicleEntity(EntityType<? extends VehicleEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected double getDefaultGravity() {
        return VEHICLE_GRAVITY;
    }

    /** Apply gravity to delta when in the air (used by all vehicles). */
    protected void applyVehicleGravity() {
        if (!onGround()) {
            Vec3 d = getDeltaMovement();
            setDeltaMovement(d.x, d.y - getGravity(), d.z);
        }
    }
}
