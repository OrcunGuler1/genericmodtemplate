package com.example.commontransports.network;

import com.example.commontransports.api.menu.ProcessingMenu;
import com.example.commontransports.processing.entity.AbstractPoweredFluidProcessorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * Server-side handler for processing machine side-mode actions.
 */
public final class ProcessingSideConfigPayloadHandler {

    private static final double MAX_DISTANCE_SQ = 64; // 8 blocks

    private ProcessingSideConfigPayloadHandler() {}

    public static void handleProcessingSideConfig(ProcessingSideConfigPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            var player = context.player();
            if (player == null || player.level().isClientSide()) return;
            if (!(player.containerMenu instanceof ProcessingMenu menu)) return;

            BlockPos pos = payload.blockPos();
            if (!menu.getBlockPos().equals(pos)) return;
            if (player.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) > MAX_DISTANCE_SQ) return;

            BlockEntity be = player.level().getBlockEntity(pos);
            if (!(be instanceof AbstractPoweredFluidProcessorBlockEntity processor)) return;

            switch (payload.actionId()) {
                case ProcessingSideConfigPayload.ACTION_CLEAR_ALL_SIDES -> processor.disableAllSides();
                case ProcessingSideConfigPayload.ACTION_CYCLE_NEXT,
                        ProcessingSideConfigPayload.ACTION_CYCLE_PREVIOUS,
                        ProcessingSideConfigPayload.ACTION_SET_DISABLED -> {
                    int sideOrdinal = payload.sideOrdinal();
                    if (sideOrdinal < 0 || sideOrdinal >= Direction.values().length) return;
                    Direction side = Direction.values()[sideOrdinal];

                    if (payload.actionId() == ProcessingSideConfigPayload.ACTION_CYCLE_NEXT) {
                        processor.cycleSideMode(side);
                    } else if (payload.actionId() == ProcessingSideConfigPayload.ACTION_CYCLE_PREVIOUS) {
                        processor.cycleSideModeReverse(side);
                    } else {
                        processor.setSideMode(side, AbstractPoweredFluidProcessorBlockEntity.SideMode.DISABLED);
                    }
                }
                default -> {
                }
            }
        });
    }
}
