package com.example.commontransports.api.block;

/**
 * Block entity that has processing progress (e.g. refinery refining).
 */
public interface HasProcessingProgress {

    int getProcessProgress();
    int getProcessTime();
}
