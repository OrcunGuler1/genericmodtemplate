package com.example.commontransports.api.menu;

/**
 * Container menu that exposes input/output fluid amounts and processing progress
 * for screens (e.g. refinery GUI).
 */
public interface ProcessingMenu {

    int getInputAmount();
    int getInputCapacity();
    int getOutputAmount();
    int getOutputCapacity();
    int getProgress();
    int getProcessTime();
    int getProgressScaled(int pixels);
    int getInputScaled(int pixels);
    int getOutputScaled(int pixels);
}
