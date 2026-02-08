package com.example.commontransports.api.menu;

import net.minecraft.core.BlockPos;

/**
 * Container menu that exposes input/output fluid amounts and processing progress
 * for screens (e.g. refinery GUI).
 */
public interface ProcessingMenu {

    BlockPos getBlockPos();
    int getInputAmount();
    int getInputCapacity();
    int getOutputAmount();
    int getOutputCapacity();
    int getProgress();
    int getProcessTime();
    int getEnergyStored();
    int getEnergyCapacity();
    default int getProgressScaled(int pixels) {
        int progress = getProgress();
        int maxProgress = getProcessTime();
        if (maxProgress == 0 || progress == 0) return 0;
        int clamped = Math.min(progress, maxProgress);
        return clamped * pixels / maxProgress;
    }

    default int getInputScaled(int pixels) {
        int amount = getInputAmount();
        int capacity = getInputCapacity();
        return capacity != 0 && amount != 0 ? amount * pixels / capacity : 0;
    }

    default int getOutputScaled(int pixels) {
        int amount = getOutputAmount();
        int capacity = getOutputCapacity();
        return capacity != 0 && amount != 0 ? amount * pixels / capacity : 0;
    }

    default int getEnergyScaled(int pixels) {
        int amount = getEnergyStored();
        int capacity = getEnergyCapacity();
        return capacity != 0 && amount != 0 ? amount * pixels / capacity : 0;
    }
}
