package com.example.commontransports.network;

import com.example.commontransports.block.ModBlocks;
import com.example.commontransports.pipe.entity.BasicFluidPipeBlockEntity;
import com.example.commontransports.pipe.menu.BasicFluidPipeMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * Server-side handler for basic fluid pipe side-mode actions.
 */
public final class PipeSideConfigPayloadHandler {

    private static final double MAX_DISTANCE_SQ = 64; // 8 blocks

    private PipeSideConfigPayloadHandler() {}

    public static void handlePipeSideConfig(PipeSideConfigPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            var player = context.player();
            if (player == null || player.level().isClientSide()) return;

            if (!(player.containerMenu instanceof BasicFluidPipeMenu menu)) return;

            BlockPos pos = payload.blockPos();
            if (!menu.getBlockPos().equals(pos)) return;
            if (player.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) > MAX_DISTANCE_SQ) return;

            BlockEntity be = player.level().getBlockEntity(pos);
            if (!(be instanceof BasicFluidPipeBlockEntity pipe)) return;
            if (!player.level().getBlockState(pos).is(ModBlocks.BASIC_FLUID_PIPE.get())) return;

            switch (payload.actionId()) {
                case PipeSideConfigPayload.ACTION_CLEAR_ALL_SIDES -> pipe.disableAllSides();
                case PipeSideConfigPayload.ACTION_CYCLE_NEXT,
                     PipeSideConfigPayload.ACTION_CYCLE_PREVIOUS,
                     PipeSideConfigPayload.ACTION_SET_DISABLED -> {
                    int sideOrdinal = payload.sideOrdinal();
                    if (sideOrdinal < 0 || sideOrdinal >= Direction.values().length) return;
                    Direction side = Direction.values()[sideOrdinal];

                    if (payload.actionId() == PipeSideConfigPayload.ACTION_CYCLE_NEXT) {
                        pipe.cycleSideMode(side);
                    } else if (payload.actionId() == PipeSideConfigPayload.ACTION_CYCLE_PREVIOUS) {
                        pipe.cycleSideModeReverse(side);
                    } else {
                        pipe.setSideMode(side, BasicFluidPipeBlockEntity.SideMode.DISABLED);
                    }
                }
                default -> {
                }
            }
        });
    }
}
