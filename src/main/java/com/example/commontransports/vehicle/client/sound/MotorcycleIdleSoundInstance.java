package com.example.commontransports.vehicle.client.sound;

import com.example.commontransports.GenericMod;
import com.example.commontransports.vehicle.entity.MotorcycleEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
public class MotorcycleIdleSoundInstance extends AbstractTickableSoundInstance {
    private final MotorcycleEntity motorcycle;
    private final int motorcycleId;

    public MotorcycleIdleSoundInstance(MotorcycleEntity motorcycle) {
        super(GenericMod.MOTORCYCLE_IDLE_SOUND.get(), SoundSource.NEUTRAL, RandomSource.create());
        this.motorcycle = motorcycle;
        this.motorcycleId = motorcycle.getId();
        this.looping = true;
        this.delay = 0;
        this.volume = 0.4f;
        this.pitch = 0.9f;
        this.attenuation = SoundInstance.Attenuation.LINEAR;
        this.relative = false;
        updatePosition();
    }

    public int getMotorcycleId() {
        return motorcycleId;
    }

    public void stopSound() {
        stop();
    }

    @Override
    public void tick() {
        if (shouldStop()) {
            stop();
            return;
        }
        updatePosition();
    }

    private void updatePosition() {
        this.x = motorcycle.getX();
        this.y = motorcycle.getY();
        this.z = motorcycle.getZ();
    }

    private boolean shouldStop() {
        if (motorcycle.isRemoved()) {
            return true;
        }
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) {
            return true;
        }
        if (minecraft.player.getVehicle() != motorcycle) {
            return true;
        }
        return motorcycle.getDeltaMovement().horizontalDistanceSqr() > 0.001;
    }
}
