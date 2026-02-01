package com.example.commontransports.api.entity;

import net.minecraft.world.item.ItemStack;

/**
 * Vehicle that has a fuel level and can be refueled with specific items.
 */
public interface FuelableVehicle {

    int getFuel();
    void setFuel(int value);
    boolean isFuelItem(ItemStack stack);
}
