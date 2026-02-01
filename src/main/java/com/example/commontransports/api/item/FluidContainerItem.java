package com.example.commontransports.api.item;

import net.minecraft.world.item.ItemStack;

/**
 * Item that holds a fluid amount (e.g. gas can). Used for fueling vehicles or filling tanks.
 */
public interface FluidContainerItem {

    int getFluidAmount(ItemStack stack);
    void setFluidAmount(ItemStack stack, int amount);
    int getCapacity();
    default boolean hasFuel(ItemStack stack) {
        return getFluidAmount(stack) > 0;
    }
    default int consumeFuel(ItemStack stack, int amount) {
        int current = getFluidAmount(stack);
        int consumed = Math.min(current, amount);
        setFluidAmount(stack, current - consumed);
        return consumed;
    }
}
