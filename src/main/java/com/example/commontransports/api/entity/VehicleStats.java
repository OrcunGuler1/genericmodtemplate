package com.example.commontransports.api.entity;

/**
 * Configuration class for vehicle statistics.
 * Create different instances for different vehicle types (motorcycle, scooter,
 * chopper, etc.)
 */
public class VehicleStats {

    public final int maxFuel;
    public final int fuelConsumptionPerTick;
    public final float fuelConsumptionSpeedMultiplier;
    public final int maxDurability;
    public final int inventorySize;
    public final float maxSpeed;
    public final float maxReverseSpeed;
    public final float acceleration;
    public final float deceleration;
    public final float drag;
    public final float idleFriction;
    public final float movementFriction;
    public final float turnRateDegrees;
    public final float directTurnRate;
    public final float lowSpeedThreshold;
    public final float maxLeanAngle;
    public final float leanSpeed;
    public final float wheelRotationMultiplier;
    public final int maxPassengers;
    public final double riderYOffset;
    public final double driverZOffset;
    public final double passengerZOffset;

    private VehicleStats(Builder builder) {
        this.maxFuel = builder.maxFuel;
        this.fuelConsumptionPerTick = builder.fuelConsumptionPerTick;
        this.fuelConsumptionSpeedMultiplier = builder.fuelConsumptionSpeedMultiplier;
        this.maxDurability = builder.maxDurability;
        this.inventorySize = builder.inventorySize;
        this.maxSpeed = builder.maxSpeed;
        this.maxReverseSpeed = builder.maxReverseSpeed;
        this.acceleration = builder.acceleration;
        this.deceleration = builder.deceleration;
        this.drag = builder.drag;
        this.idleFriction = builder.idleFriction;
        this.movementFriction = builder.movementFriction;
        this.turnRateDegrees = builder.turnRateDegrees;
        this.directTurnRate = builder.directTurnRate;
        this.lowSpeedThreshold = builder.lowSpeedThreshold;
        this.maxLeanAngle = builder.maxLeanAngle;
        this.leanSpeed = builder.leanSpeed;
        this.wheelRotationMultiplier = builder.wheelRotationMultiplier;
        this.maxPassengers = builder.maxPassengers;
        this.riderYOffset = builder.riderYOffset;
        this.driverZOffset = builder.driverZOffset;
        this.passengerZOffset = builder.passengerZOffset;
    }

    public static final VehicleStats MOTORCYCLE = new Builder()
            .maxFuel(13000).fuelConsumptionPerTick(1).fuelConsumptionSpeedMultiplier(4.0f)
            .maxDurability(256).inventorySize(9)
            .maxSpeed(2.5f).maxReverseSpeed(0.05f).acceleration(0.08f).deceleration(0.12f).drag(0.03f)
            .idleFriction(0.6f).movementFriction(0.9f)
            .turnRateDegrees(4.0f).directTurnRate(3.5f).lowSpeedThreshold(0.3f).maxLeanAngle(65.0f).leanSpeed(12.0f)
            .wheelRotationMultiplier(20.0f).maxPassengers(2).riderYOffset(-0.15).driverZOffset(-0.5).passengerZOffset(-0.6)
            .build();

    public static final VehicleStats SPORT_BIKE = new Builder()
            .maxFuel(8000).fuelConsumptionPerTick(2).fuelConsumptionSpeedMultiplier(5.0f)
            .maxDurability(200).inventorySize(4)
            .maxSpeed(3.5f).maxReverseSpeed(0.04f).acceleration(0.12f).deceleration(0.15f).drag(0.02f)
            .idleFriction(0.6f).movementFriction(0.92f)
            .turnRateDegrees(5.0f).directTurnRate(3.0f).lowSpeedThreshold(0.25f).maxLeanAngle(70.0f).leanSpeed(15.0f)
            .wheelRotationMultiplier(25.0f).maxPassengers(2).riderYOffset(0.25).driverZOffset(0.5).passengerZOffset(-0.3)
            .build();

    public static final VehicleStats CHOPPER = new Builder()
            .maxFuel(15000).fuelConsumptionPerTick(1).fuelConsumptionSpeedMultiplier(3.0f)
            .maxDurability(350).inventorySize(18)
            .maxSpeed(1.8f).maxReverseSpeed(0.03f).acceleration(0.05f).deceleration(0.08f).drag(0.04f)
            .idleFriction(0.5f).movementFriction(0.88f)
            .turnRateDegrees(3.0f).directTurnRate(2.5f).lowSpeedThreshold(0.35f).maxLeanAngle(45.0f).leanSpeed(8.0f)
            .wheelRotationMultiplier(15.0f).maxPassengers(1).riderYOffset(-0.3).driverZOffset(-0.4).passengerZOffset(-0.8)
            .build();

    public static final VehicleStats SCOOTER = new Builder()
            .maxFuel(5000).fuelConsumptionPerTick(1).fuelConsumptionSpeedMultiplier(2.0f)
            .maxDurability(150).inventorySize(6)
            .maxSpeed(1.5f).maxReverseSpeed(0.06f).acceleration(0.10f).deceleration(0.14f).drag(0.025f)
            .idleFriction(0.65f).movementFriction(0.91f)
            .turnRateDegrees(5.5f).directTurnRate(4.5f).lowSpeedThreshold(0.4f).maxLeanAngle(40.0f).leanSpeed(10.0f)
            .wheelRotationMultiplier(18.0f).maxPassengers(2).riderYOffset(0.35).driverZOffset(-0.4).passengerZOffset(-0.5)
            .build();

    public static class Builder {
        private int maxFuel = 10000;
        private int fuelConsumptionPerTick = 1;
        private float fuelConsumptionSpeedMultiplier = 4.0f;
        private int maxDurability = 256;
        private int inventorySize = 9;
        private float maxSpeed = 2.5f;
        private float maxReverseSpeed = 0.05f;
        private float acceleration = 0.08f;
        private float deceleration = 0.12f;
        private float drag = 0.03f;
        private float idleFriction = 0.6f;
        private float movementFriction = 0.9f;
        private float turnRateDegrees = 4.0f;
        private float directTurnRate = 3.5f;
        private float lowSpeedThreshold = 0.3f;
        private float maxLeanAngle = 65.0f;
        private float leanSpeed = 12.0f;
        private float wheelRotationMultiplier = 20.0f;
        private int maxPassengers = 2;
        private double riderYOffset = 0.35;
        private double driverZOffset = -0.5;
        private double passengerZOffset = -0.6;

        public Builder maxFuel(int value) { this.maxFuel = value; return this; }
        public Builder fuelConsumptionPerTick(int value) { this.fuelConsumptionPerTick = value; return this; }
        public Builder fuelConsumptionSpeedMultiplier(float value) { this.fuelConsumptionSpeedMultiplier = value; return this; }
        public Builder maxDurability(int value) { this.maxDurability = value; return this; }
        public Builder inventorySize(int value) { this.inventorySize = value; return this; }
        public Builder maxSpeed(float value) { this.maxSpeed = value; return this; }
        public Builder maxReverseSpeed(float value) { this.maxReverseSpeed = value; return this; }
        public Builder acceleration(float value) { this.acceleration = value; return this; }
        public Builder deceleration(float value) { this.deceleration = value; return this; }
        public Builder drag(float value) { this.drag = value; return this; }
        public Builder idleFriction(float value) { this.idleFriction = value; return this; }
        public Builder movementFriction(float value) { this.movementFriction = value; return this; }
        public Builder turnRateDegrees(float value) { this.turnRateDegrees = value; return this; }
        public Builder directTurnRate(float value) { this.directTurnRate = value; return this; }
        public Builder lowSpeedThreshold(float value) { this.lowSpeedThreshold = value; return this; }
        public Builder maxLeanAngle(float value) { this.maxLeanAngle = value; return this; }
        public Builder leanSpeed(float value) { this.leanSpeed = value; return this; }
        public Builder wheelRotationMultiplier(float value) { this.wheelRotationMultiplier = value; return this; }
        public Builder maxPassengers(int value) { this.maxPassengers = value; return this; }
        public Builder riderYOffset(double value) { this.riderYOffset = value; return this; }
        public Builder driverZOffset(double value) { this.driverZOffset = value; return this; }
        public Builder passengerZOffset(double value) { this.passengerZOffset = value; return this; }
        public VehicleStats build() { return new VehicleStats(this); }
    }
}
