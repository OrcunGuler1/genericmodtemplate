package com.example.commontransports.fluid;

import com.example.commontransports.GenericMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModFluids {
    
    public static final DeferredRegister<FluidType> FLUID_TYPES = 
        DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, GenericMod.MODID);
    
    public static final DeferredRegister<Fluid> FLUIDS = 
        DeferredRegister.create(Registries.FLUID, GenericMod.MODID);
    
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(GenericMod.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GenericMod.MODID);

    // ==================== CRUDE OIL ====================
    public static final DeferredHolder<FluidType, FluidType> CRUDE_OIL_TYPE = FLUID_TYPES.register("crude_oil",
        () -> new FluidType(FluidType.Properties.create()
            .density(900)
            .viscosity(2000)
            .canSwim(false)
            .canDrown(true)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)));

    public static final DeferredHolder<Fluid, FlowingFluid> CRUDE_OIL_SOURCE = FLUIDS.register("crude_oil",
        () -> new BaseFlowingFluid.Source(ModFluids.CRUDE_OIL_PROPERTIES));
    
    public static final DeferredHolder<Fluid, FlowingFluid> CRUDE_OIL_FLOWING = FLUIDS.register("crude_oil_flowing",
        () -> new BaseFlowingFluid.Flowing(ModFluids.CRUDE_OIL_PROPERTIES));
    
    public static final DeferredBlock<LiquidBlock> CRUDE_OIL_BLOCK = BLOCKS.registerBlock("crude_oil",
        props -> new LiquidBlock(CRUDE_OIL_SOURCE.get(), props),
        props -> props
            .replaceable()
            .noCollision()
            .strength(100.0F)
            .pushReaction(net.minecraft.world.level.material.PushReaction.DESTROY)
            .liquid());
    
    public static final DeferredItem<BucketItem> CRUDE_OIL_BUCKET = ITEMS.registerItem("crude_oil_bucket",
        props -> new BucketItem(CRUDE_OIL_SOURCE.get(), props.craftRemainder(Items.BUCKET).stacksTo(1)));

    // Crude oil properties - NO infinite source (key difference from water)
    public static final BaseFlowingFluid.Properties CRUDE_OIL_PROPERTIES = new BaseFlowingFluid.Properties(
        CRUDE_OIL_TYPE, CRUDE_OIL_SOURCE, CRUDE_OIL_FLOWING)
        .slopeFindDistance(2)
        .levelDecreasePerBlock(2)
        .block(CRUDE_OIL_BLOCK)
        .bucket(CRUDE_OIL_BUCKET);

    // ==================== NAPHTHA (Intermediate feedstock from distillation) ====================
    public static final DeferredHolder<FluidType, FluidType> NAPHTHA_TYPE = FLUID_TYPES.register("naphtha",
        () -> new FluidType(FluidType.Properties.create()
            .density(780)
            .viscosity(1000)
            .canSwim(false)
            .canDrown(true)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)));

    public static final DeferredHolder<Fluid, FlowingFluid> NAPHTHA_SOURCE = FLUIDS.register("naphtha",
        () -> new BaseFlowingFluid.Source(ModFluids.NAPHTHA_PROPERTIES));

    public static final DeferredHolder<Fluid, FlowingFluid> NAPHTHA_FLOWING = FLUIDS.register("naphtha_flowing",
        () -> new BaseFlowingFluid.Flowing(ModFluids.NAPHTHA_PROPERTIES));

    public static final DeferredBlock<LiquidBlock> NAPHTHA_BLOCK = BLOCKS.registerBlock("naphtha",
        props -> new LiquidBlock(NAPHTHA_SOURCE.get(), props),
        props -> props
            .replaceable()
            .noCollision()
            .strength(100.0F)
            .pushReaction(net.minecraft.world.level.material.PushReaction.DESTROY)
            .liquid());

    public static final DeferredItem<BucketItem> NAPHTHA_BUCKET = ITEMS.registerItem("naphtha_bucket",
        props -> new BucketItem(NAPHTHA_SOURCE.get(), props.craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final BaseFlowingFluid.Properties NAPHTHA_PROPERTIES = new BaseFlowingFluid.Properties(
        NAPHTHA_TYPE, NAPHTHA_SOURCE, NAPHTHA_FLOWING)
        .slopeFindDistance(3)
        .levelDecreasePerBlock(1)
        .block(NAPHTHA_BLOCK)
        .bucket(NAPHTHA_BUCKET);

    // ==================== REFORMATE (Intermediate from catalytic reforming) ====================
    public static final DeferredHolder<FluidType, FluidType> REFORMATE_TYPE = FLUID_TYPES.register("reformate",
        () -> new FluidType(FluidType.Properties.create()
            .density(760)
            .viscosity(900)
            .canSwim(false)
            .canDrown(true)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)));

    public static final DeferredHolder<Fluid, FlowingFluid> REFORMATE_SOURCE = FLUIDS.register("reformate",
        () -> new BaseFlowingFluid.Source(ModFluids.REFORMATE_PROPERTIES));

    public static final DeferredHolder<Fluid, FlowingFluid> REFORMATE_FLOWING = FLUIDS.register("reformate_flowing",
        () -> new BaseFlowingFluid.Flowing(ModFluids.REFORMATE_PROPERTIES));

    public static final DeferredBlock<LiquidBlock> REFORMATE_BLOCK = BLOCKS.registerBlock("reformate",
        props -> new LiquidBlock(REFORMATE_SOURCE.get(), props),
        props -> props
            .replaceable()
            .noCollision()
            .strength(100.0F)
            .pushReaction(net.minecraft.world.level.material.PushReaction.DESTROY)
            .liquid());

    public static final DeferredItem<BucketItem> REFORMATE_BUCKET = ITEMS.registerItem("reformate_bucket",
        props -> new BucketItem(REFORMATE_SOURCE.get(), props.craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final BaseFlowingFluid.Properties REFORMATE_PROPERTIES = new BaseFlowingFluid.Properties(
        REFORMATE_TYPE, REFORMATE_SOURCE, REFORMATE_FLOWING)
        .slopeFindDistance(3)
        .levelDecreasePerBlock(1)
        .block(REFORMATE_BLOCK)
        .bucket(REFORMATE_BUCKET);

    // ==================== PETROL (Final fuel, no bucket) ====================
    public static final DeferredHolder<FluidType, FluidType> PETROL_TYPE = FLUID_TYPES.register("petrol",
        () -> new FluidType(FluidType.Properties.create()
            .density(750)
            .viscosity(800)
            .canSwim(false)
            .canDrown(true)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)));

    public static final DeferredHolder<Fluid, FlowingFluid> PETROL_SOURCE = FLUIDS.register("petrol",
        () -> new BaseFlowingFluid.Source(ModFluids.PETROL_PROPERTIES));
    
    public static final DeferredHolder<Fluid, FlowingFluid> PETROL_FLOWING = FLUIDS.register("petrol_flowing",
        () -> new BaseFlowingFluid.Flowing(ModFluids.PETROL_PROPERTIES));
    
    // Petrol block exists for internal use but no bucket - petrol can only be extracted via gas can
    public static final DeferredBlock<LiquidBlock> PETROL_BLOCK = BLOCKS.registerBlock("petrol",
        props -> new LiquidBlock(PETROL_SOURCE.get(), props),
        props -> props
            .replaceable()
            .noCollision()
            .strength(100.0F)
            .pushReaction(net.minecraft.world.level.material.PushReaction.DESTROY)
            .liquid());

    // Petrol properties - NO bucket, NO infinite source
    public static final BaseFlowingFluid.Properties PETROL_PROPERTIES = new BaseFlowingFluid.Properties(
        PETROL_TYPE, PETROL_SOURCE, PETROL_FLOWING)
        .slopeFindDistance(3)
        .levelDecreasePerBlock(1)
        .block(PETROL_BLOCK);

    public static void register(IEventBus modEventBus) {
        FLUID_TYPES.register(modEventBus);
        FLUIDS.register(modEventBus);
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
    }
}
