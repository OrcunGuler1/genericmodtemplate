package com.example.commontransports.vehicle.item;

import com.example.commontransports.vehicle.entity.AbstractStatsVehicleEntity;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.function.Consumer;

/**
 * Item that places a vehicle entity when used on a block. Subclasses implement createEntity.
 * Saves and restores fuel via CUSTOM_DATA when picked up and placed.
 */
public abstract class AbstractVehiclePlacementItem extends Item {

    public AbstractVehiclePlacementItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    protected abstract Entity createEntity(Level level, Vec3 spawnPos, float yRot, Player player);

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        HitResult hit = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
        if (hit.getType() != HitResult.Type.BLOCK) return InteractionResult.PASS;

        BlockHitResult blockHit = (BlockHitResult) hit;
        Vec3 spawnPos = Vec3.atCenterOf(blockHit.getBlockPos().relative(blockHit.getDirection()));
        spawnPos = new Vec3(spawnPos.x, spawnPos.y + 0.1, spawnPos.z);

        Entity entity = createEntity(level, spawnPos, player.getYRot(), player);
        entity.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
        entity.setYRot(player.getYRot());

        // Restore saved fuel from the item stack
        if (entity instanceof AbstractStatsVehicleEntity vehicle && stack.has(DataComponents.CUSTOM_DATA)) {
            CustomData data = stack.get(DataComponents.CUSTOM_DATA);
            if (data != null) {
                var tag = data.copyTag();
                vehicle.setFuel(tag.getIntOr("Fuel", 0));
            }
        }

        if (!level.noCollision(entity)) return InteractionResult.FAIL;

        if (!level.isClientSide()) level.addFreshEntity(entity);
        if (!player.getAbilities().instabuild) stack.shrink(1);

        return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.CONSUME;
    }

    // --- Tooltip: show fuel ---

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display,
                                Consumer<Component> tooltipAdder, TooltipFlag flag) {
        if (stack.has(DataComponents.CUSTOM_DATA)) {
            CustomData data = stack.get(DataComponents.CUSTOM_DATA);
            if (data != null) {
                var tag = data.copyTag();
                int fuel = tag.getIntOr("Fuel", 0);
                int maxFuel = tag.getIntOr("MaxFuel", 0);
                if (maxFuel > 0) {
                    tooltipAdder.accept(Component.literal("Fuel: " + fuel + " / " + maxFuel + " mB")
                            .withStyle(style -> style.withColor(0xD4A017)));
                }
            }
        }
    }

    // --- Fuel bar indicator (like GasCanItem) ---

    @Override
    public boolean isBarVisible(ItemStack stack) {
        if (stack.has(DataComponents.CUSTOM_DATA)) {
            CustomData data = stack.get(DataComponents.CUSTOM_DATA);
            if (data != null) {
                int fuel = data.copyTag().getIntOr("Fuel", 0);
                int maxFuel = data.copyTag().getIntOr("MaxFuel", 0);
                return maxFuel > 0 && fuel < maxFuel;
            }
        }
        return false;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        if (stack.has(DataComponents.CUSTOM_DATA)) {
            CustomData data = stack.get(DataComponents.CUSTOM_DATA);
            if (data != null) {
                int fuel = data.copyTag().getIntOr("Fuel", 0);
                int maxFuel = data.copyTag().getIntOr("MaxFuel", 1);
                return Math.round(fuel * 13.0f / maxFuel);
            }
        }
        return 0;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return 0xD4A017; // Gold, matching fuel color
    }
}
