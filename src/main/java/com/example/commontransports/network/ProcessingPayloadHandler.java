package com.example.commontransports.network;

import com.example.commontransports.api.item.FluidContainerItem;
import com.example.commontransports.api.menu.ProcessingMenu;
import com.example.commontransports.processing.entity.AbstractPoweredFluidProcessorBlockEntity;
import com.example.commontransports.refinery.entity.RefineryBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.transfer.fluid.FluidUtil;

/**
 * Server-side handler for processing GUI actions.
 */
public final class ProcessingPayloadHandler {
    private static final double MAX_DISTANCE_SQ = 64; // 8 blocks

    private ProcessingPayloadHandler() {}

    public static void handleProcessingAction(ProcessingActionPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            var player = context.player();
            if (player == null || player.level().isClientSide()) return;
            if (!(player.containerMenu instanceof ProcessingMenu menu)) return;

            BlockPos pos = payload.blockPos();
            if (!menu.getBlockPos().equals(pos)) return;
            if (player.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) > MAX_DISTANCE_SQ) return;

            BlockEntity be = player.level().getBlockEntity(pos);
            if (!(be instanceof AbstractPoweredFluidProcessorBlockEntity processor)) return;
            if (payload.actionId() != ProcessingActionPayload.ACTION_PICKUP_OUTPUT) return;

            if (tryTransferOutputToHeldContainer(player, InteractionHand.MAIN_HAND, pos, processor)) return;
            tryTransferOutputToHeldContainer(player, InteractionHand.OFF_HAND, pos, processor);
        });
    }

    private static boolean tryTransferOutputToHeldContainer(
            net.minecraft.world.entity.player.Player player,
            InteractionHand hand,
            BlockPos pos,
            AbstractPoweredFluidProcessorBlockEntity processor
    ) {
        // Preferred path: any modded item exposing NeoForge fluid item capability.
        if (FluidUtil.interactWithFluidHandler(player, hand, pos, processor.getOutputFluidHandler(null))) {
            return true;
        }

        // Compatibility fallback for legacy custom containers (e.g. this mod's gas can).
        if (processor instanceof RefineryBlockEntity refinery) {
            ItemStack held = player.getItemInHand(hand);
            if (held.getItem() instanceof FluidContainerItem container) {
                int current = container.getFluidAmount(held);
                int space = container.getCapacity() - current;
                if (space <= 0 || refinery.getOutputAmount() <= 0) return false;

                int transfer = Math.min(space, refinery.getOutputAmount());
                int drained = refinery.drainOutput(transfer, true);
                if (drained <= 0) return false;

                container.setFluidAmount(held, current + drained);
                player.setItemInHand(hand, held);
                return true;
            }
        }

        return false;
    }
}
