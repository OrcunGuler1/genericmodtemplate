package com.example.commontransports.refinery.block;

import com.example.commontransports.GenericMod;
import com.example.commontransports.api.item.FluidContainerItem;
import com.example.commontransports.block.entity.ModBlockEntities;
import com.example.commontransports.fluid.ModFluids;
import com.example.commontransports.refinery.entity.RefineryBlockEntity;

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

public class RefineryBlock extends BaseEntityBlock {

    public static final MapCodec<RefineryBlock> CODEC = simpleCodec(RefineryBlock::new);

    public RefineryBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new RefineryBlockEntity(pos, state);
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
        if (!(be instanceof RefineryBlockEntity refinery)) return InteractionResult.PASS;

        if (stack.is(GenericMod.SPEED_UPGRADE.get())) {
            if (refinery.installSpeedUpgrade()) {
                if (!player.getAbilities().instabuild) stack.shrink(1);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }
        if (stack.is(GenericMod.EFFICIENCY_UPGRADE.get())) {
            if (refinery.installEfficiencyUpgrade()) {
                if (!player.getAbilities().instabuild) stack.shrink(1);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }

        // Generic fluid-container compatibility via NeoForge fluid item capability.
        if (net.neoforged.neoforge.transfer.fluid.FluidUtil.interactWithFluidHandler(
                player, hand, pos, refinery.getFluidHandler(null))) {
            return InteractionResult.SUCCESS;
        }

        if (stack.is(ModFluids.REFORMATE_BUCKET.get())) {
            int filled = refinery.fillInput(ModFluids.REFORMATE_SOURCE.get(), FluidType.BUCKET_VOLUME, false);
            if (filled == FluidType.BUCKET_VOLUME) {
                refinery.fillInput(ModFluids.REFORMATE_SOURCE.get(), FluidType.BUCKET_VOLUME, true);
                if (!player.getAbilities().instabuild) player.setItemInHand(hand, new ItemStack(Items.BUCKET));
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }

        if (stack.is(Items.BUCKET)) {
            if (refinery.hasInputFluid()) {
                int drained = refinery.drainInput(FluidType.BUCKET_VOLUME, false);
                if (drained == FluidType.BUCKET_VOLUME) {
                    refinery.drainInput(FluidType.BUCKET_VOLUME, true);
                    if (!player.getAbilities().instabuild) {
                        stack.shrink(1);
                        player.addItem(new ItemStack(ModFluids.REFORMATE_BUCKET.get()));
                    }
                    return InteractionResult.SUCCESS;
                }
            }
            return InteractionResult.PASS;
        }

        if (stack.getItem() instanceof FluidContainerItem fc) {
            int currentFuel = fc.getFluidAmount(stack);
            int spaceInCan = fc.getCapacity() - currentFuel;
            if (spaceInCan > 0 && refinery.getOutputAmount() > 0) {
                int drained = refinery.drainOutput(Math.min(spaceInCan, refinery.getOutputAmount()), true);
                if (drained > 0) {
                    fc.setFluidAmount(stack, currentFuel + drained);
                    return InteractionResult.SUCCESS;
                }
            }
        }

        player.openMenu(refinery, refinery.getBlockPos());
        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;
        return createTickerHelper(type, ModBlockEntities.REFINERY.get(), RefineryBlockEntity::serverTick);
    }
}
