package com.example.commontransports.network;

import com.example.commontransports.block.ModBlocks;
import com.example.commontransports.refinery.entity.RefineryBlockEntity;
import com.example.commontransports.refinery.menu.RefineryMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;

import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * Server-side handler for refinery GUI actions (flush tanks).
 * Inspired by AdvancedAE ReactionChamberMenu client actions.
 */
public final class RefineryPayloadHandler {

    private static final double MAX_DISTANCE_SQ = 64; // 8 blocks

    private RefineryPayloadHandler() {}

    public static void handleRefineryAction(RefineryActionPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            var player = context.player();
            if (player == null || player.level().isClientSide()) return;

            // Only allow actions for the refinery menu the player currently has open.
            if (!(player.containerMenu instanceof RefineryMenu menu)) return;
            BlockPos pos = payload.blockPos();
            if (!menu.getBlockPos().equals(pos)) return;

            BlockEntity be = player.level().getBlockEntity(pos);
            if (!(be instanceof RefineryBlockEntity refinery)) return;
            if (!player.level().getBlockState(pos).is(ModBlocks.REFINERY.get())) return;
            if (player.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) > MAX_DISTANCE_SQ) return;

            switch (payload.actionId()) {
                case RefineryMenu.ACTION_FLUSH_INPUT -> refinery.clearInputFluid();
                case RefineryMenu.ACTION_FLUSH_OUTPUT -> refinery.clearOutputFluid();
                default -> {}
            }
        });
    }
}
