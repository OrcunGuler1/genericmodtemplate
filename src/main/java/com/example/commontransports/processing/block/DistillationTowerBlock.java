package com.example.commontransports.processing.block;

import com.example.commontransports.GenericMod;
import com.example.commontransports.block.entity.ModBlockEntities;
import com.example.commontransports.fluid.ModFluids;
import com.example.commontransports.processing.entity.DistillationTowerBlockEntity;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.fluids.FluidType;
import org.jspecify.annotations.Nullable;

public class DistillationTowerBlock extends BaseEntityBlock {

    public static final MapCodec<DistillationTowerBlock> CODEC = simpleCodec(DistillationTowerBlock::new);

    public DistillationTowerBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DistillationTowerBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                          Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide()) return InteractionResult.SUCCESS;

        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof DistillationTowerBlockEntity tower)) return InteractionResult.PASS;

        if (stack.is(GenericMod.SPEED_UPGRADE.get())) {
            if (tower.installSpeedUpgrade()) {
                if (!player.getAbilities().instabuild) stack.shrink(1);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }
        if (stack.is(GenericMod.EFFICIENCY_UPGRADE.get())) {
            if (tower.installEfficiencyUpgrade()) {
                if (!player.getAbilities().instabuild) stack.shrink(1);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }

        // Generic fluid-container compatibility via NeoForge fluid item capability.
        if (net.neoforged.neoforge.transfer.fluid.FluidUtil.interactWithFluidHandler(
                player, hand, pos, tower.getFluidHandler(null))) {
            return InteractionResult.SUCCESS;
        }

        if (stack.is(ModFluids.CRUDE_OIL_BUCKET.get())) {
            int filled = tower.fillInput(ModFluids.CRUDE_OIL_SOURCE.get(), FluidType.BUCKET_VOLUME, false);
            if (filled == FluidType.BUCKET_VOLUME) {
                tower.fillInput(ModFluids.CRUDE_OIL_SOURCE.get(), FluidType.BUCKET_VOLUME, true);
                if (!player.getAbilities().instabuild) player.setItemInHand(hand, new ItemStack(Items.BUCKET));
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }

        if (stack.is(Items.BUCKET)) {
            int drainedOutput = tower.drainOutput(FluidType.BUCKET_VOLUME, false);
            if (drainedOutput == FluidType.BUCKET_VOLUME) {
                tower.drainOutput(FluidType.BUCKET_VOLUME, true);
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                    player.addItem(new ItemStack(ModFluids.NAPHTHA_BUCKET.get()));
                }
                return InteractionResult.SUCCESS;
            }

            int drainedInput = tower.drainInput(FluidType.BUCKET_VOLUME, false);
            if (drainedInput == FluidType.BUCKET_VOLUME) {
                tower.drainInput(FluidType.BUCKET_VOLUME, true);
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                    player.addItem(new ItemStack(ModFluids.CRUDE_OIL_BUCKET.get()));
                }
                return InteractionResult.SUCCESS;
            }
        }

        player.openMenu(tower, tower.getBlockPos());
        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;
        return createTickerHelper(type, ModBlockEntities.DISTILLATION_TOWER.get(), DistillationTowerBlockEntity::serverTick);
    }
}
