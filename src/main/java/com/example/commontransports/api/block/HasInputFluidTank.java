package com.example.commontransports.api.block;

import net.minecraft.world.level.material.Fluid;

/**
 * Block entity that has an input fluid tank (fill/drain).
 */
public interface HasInputFluidTank {

    int getInputAmount();
    int getInputCapacity();
    int fillInput(Fluid fluid, int amount, boolean execute);
    int drainInput(int amount, boolean execute);
    boolean hasInputFluid();
}
