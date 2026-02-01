package com.example.commontransports.api.entity;

/**
 * Vehicle that has durability and can take damage.
 */
public interface DurableVehicle {

    int getDurability();
    void setDurability(int value);
}
