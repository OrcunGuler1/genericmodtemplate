package com.example.commontransports.block;

import com.example.commontransports.block.entity.RefineryBlockEntity;
import com.example.commontransports.block.entity.ModBlockEntities;
import com.example.commontransports.fluid.ModFluids;
import com.example.commontransports.item.GasCanItem;

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
        
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        
        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof RefineryBlockEntity refinery)) {
            return InteractionResult.PASS;
        }
        
        // Handle crude oil bucket - fill input tank
        if (stack.is(ModFluids.CRUDE_OIL_BUCKET.get())) {
            int filled = refinery.fillInput(ModFluids.CRUDE_OIL_SOURCE.get(), FluidType.BUCKET_VOLUME, false);
            if (filled == FluidType.BUCKET_VOLUME) {
                refinery.fillInput(ModFluids.CRUDE_OIL_SOURCE.get(), FluidType.BUCKET_VOLUME, true);
                if (!player.getAbilities().instabuild) {
                    player.setItemInHand(hand, new ItemStack(Items.BUCKET));
                }
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }
        
        // Handle empty bucket - extract crude oil from input tank
        if (stack.is(Items.BUCKET)) {
            if (refinery.hasInputFluid()) {
                int drained = refinery.drainInput(FluidType.BUCKET_VOLUME, false);
                if (drained == FluidType.BUCKET_VOLUME) {
                    refinery.drainInput(FluidType.BUCKET_VOLUME, true);
                    if (!player.getAbilities().instabuild) {
                        stack.shrink(1);
                        player.addItem(new ItemStack(ModFluids.CRUDE_OIL_BUCKET.get()));
                    }
                    return InteractionResult.SUCCESS;
                }
            }
            return InteractionResult.PASS;
        }
        
        // Handle gas can - fill from output tank (petrol)
        // Requires at least 1 bucket (1000mB) and fills exactly 1 bucket at a time
        if (stack.getItem() instanceof GasCanItem) {
            int currentFuel = GasCanItem.getFluidAmount(stack);
            int spaceInCan = GasCanItem.CAPACITY - currentFuel;
            
            // Only fill if can has space for at least 1 bucket AND refinery has at least 1 bucket
            if (spaceInCan >= FluidType.BUCKET_VOLUME && refinery.getOutputAmount() >= FluidType.BUCKET_VOLUME) {
                // Drain exactly 1 bucket
                int drained = refinery.drainOutput(FluidType.BUCKET_VOLUME, true);
                if (drained > 0) {
                    GasCanItem.setFluidAmount(stack, currentFuel + drained);
                    return InteractionResult.SUCCESS;
                }
            }
            // If can is full or not enough petrol, open GUI instead
        }
        
        // Open GUI
        player.openMenu(refinery, refinery.getBlockPos());
        return InteractionResult.SUCCESS;
    }
    
    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return null;
        }
        return createTickerHelper(type, ModBlockEntities.REFINERY.get(), RefineryBlockEntity::serverTick);
    }
}
