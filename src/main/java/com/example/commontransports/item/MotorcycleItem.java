package com.example.commontransports.item;

import com.example.commontransports.GenericMod;
import com.example.commontransports.entity.MotorcycleEntity;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class MotorcycleItem extends Item {
    public MotorcycleItem(Item.Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        HitResult hit = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
        if (hit.getType() != HitResult.Type.BLOCK) {
            GenericMod.LOGGER.info("MotorcycleItem: no block hit (type={})", hit.getType());
            return InteractionResult.PASS;
        }

        BlockHitResult blockHit = (BlockHitResult) hit;
        BlockPos placePos = blockHit.getBlockPos().relative(blockHit.getDirection());
        Vec3 spawnPos = Vec3.atCenterOf(placePos);
        MotorcycleEntity motorcycle = new MotorcycleEntity(GenericMod.MOTORCYCLE.get(), level);
        motorcycle.setPos(spawnPos.x, spawnPos.y + 0.1, spawnPos.z);
        motorcycle.setYRot(player.getYRot());

        if (!level.noCollision(motorcycle)) {
            GenericMod.LOGGER.info("MotorcycleItem: placement blocked at {} (face={}, hit={}, pos={})",
                    placePos, blockHit.getDirection(), blockHit.getType(), spawnPos);
            return InteractionResult.FAIL;
        }

        if (!level.isClientSide()) {
            level.addFreshEntity(motorcycle);
            GenericMod.LOGGER.info("MotorcycleItem: spawned motorcycle at {} (player={}, creative={})",
                    spawnPos, player.getName().getString(), player.getAbilities().instabuild);
        }

        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.CONSUME;
    }
}
