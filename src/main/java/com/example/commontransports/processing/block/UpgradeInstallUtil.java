package com.example.commontransports.processing.block;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;

import java.util.function.IntUnaryOperator;

public final class UpgradeInstallUtil {

    private UpgradeInstallUtil() {}

    public static boolean isBulkInstallRequested(Player player) {
        return player.isSecondaryUseActive() || player.isShiftKeyDown() || player.isCrouching();
    }

    public static InteractionResult tryInstallHeldUpgrade(
            Player player,
            ItemStack heldStack,
            Item upgradeItem,
            int maxUpgradesPerType,
            IntUnaryOperator installFunction
    ) {
        if (!heldStack.is(upgradeItem)) return InteractionResult.PASS;

        int requested = isBulkInstallRequested(player)
                ? (player.getAbilities().instabuild ? maxUpgradesPerType : heldStack.getCount())
                : 1;
        int installed = installFunction.applyAsInt(requested);
        if (installed <= 0) return InteractionResult.PASS;

        if (!player.getAbilities().instabuild) {
            heldStack.shrink(installed);
        }
        return InteractionResult.SUCCESS;
    }
}
