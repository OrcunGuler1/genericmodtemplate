package com.example.commontransports.api.block;

/**
 * Block entity that has an output fluid tank (drain only).
 */
public interface HasOutputFluidTank {

    int getOutputAmount();
    int getOutputCapacity();
    int drainOutput(int amount, boolean execute);
    boolean hasOutputFluid();
}
