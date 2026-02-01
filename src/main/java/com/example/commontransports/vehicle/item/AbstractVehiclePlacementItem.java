package com.example.commontransports.vehicle.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

/**
 * Item that places a vehicle entity when used on a block. Subclasses implement createEntity.
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

        if (!level.noCollision(entity)) return InteractionResult.FAIL;

        if (!level.isClientSide()) level.addFreshEntity(entity);
        if (!player.getAbilities().instabuild) stack.shrink(1);

        return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.CONSUME;
    }
}
