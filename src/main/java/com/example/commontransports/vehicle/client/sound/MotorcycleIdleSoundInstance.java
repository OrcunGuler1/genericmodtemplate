package com.example.commontransports.vehicle.client.sound;

import com.example.commontransports.GenericMod;
import com.example.commontransports.vehicle.entity.MotorcycleEntity;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;

/**
 * Tickable sound that plays the motorcycle idle loop with smooth fade-in / fade-out
 * so the audio never cuts off abruptly.
 */
public class MotorcycleIdleSoundInstance extends AbstractTickableSoundInstance {
    private final MotorcycleEntity motorcycle;
    private final int motorcycleId;

    /** Target volume once fully faded in. */
    private static final float TARGET_VOLUME = 0.4f;
    /** Volume change per tick while fading in (~8 ticks to reach full volume). */
    private static final float FADE_IN_RATE = 0.05f;
    /** Volume change per tick while fading out (~8 ticks to silence). */
    private static final float FADE_OUT_RATE = 0.05f;

    private boolean fadingOut = false;

    public MotorcycleIdleSoundInstance(MotorcycleEntity motorcycle) {
        super(GenericMod.MOTORCYCLE_IDLE_SOUND.get(), SoundSource.NEUTRAL, RandomSource.create());
        this.motorcycle = motorcycle;
        this.motorcycleId = motorcycle.getId();
        this.looping = true;
        this.delay = 0;
        this.volume = 0.01f;          // start near-silent for a smooth fade-in
        this.pitch = 0.9f;
        this.attenuation = SoundInstance.Attenuation.LINEAR;
        this.relative = false;
        updatePosition();
    }

    public int getMotorcycleId() {
        return motorcycleId;
    }

    /** Begin a graceful fade-out. The instance will stop itself once volume reaches zero. */
    public void fadeOut() {
        fadingOut = true;
    }

    public boolean isFadingOut() {
        return fadingOut;
    }

    /** Hard-stop (no fade). Use only when the entity is being removed. */
    public void stopSound() {
        stop();
    }

    @Override
    public void tick() {
        // Hard-stop if the backing entity no longer exists
        if (motorcycle.isRemoved()) {
            stop();
            return;
        }

        if (fadingOut) {
            this.volume -= FADE_OUT_RATE;
            if (this.volume <= 0f) {
                this.volume = 0f;
                stop();
                return;
            }
        } else if (this.volume < TARGET_VOLUME) {
            // Fade in
            this.volume = Math.min(TARGET_VOLUME, this.volume + FADE_IN_RATE);
        }

        updatePosition();
    }

    private void updatePosition() {
        this.x = motorcycle.getX();
        this.y = motorcycle.getY();
        this.z = motorcycle.getZ();
    }
}
