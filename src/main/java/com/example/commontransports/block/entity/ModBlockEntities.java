package com.example.commontransports.block.entity;

import com.example.commontransports.GenericMod;
import com.example.commontransports.block.ModBlocks;
import com.example.commontransports.pipe.entity.BasicFluidPipeBlockEntity;
import com.example.commontransports.power.entity.CreativeEnergyCellBlockEntity;
import com.example.commontransports.processing.entity.CatalyticReformerBlockEntity;
import com.example.commontransports.processing.entity.DistillationTowerBlockEntity;
import com.example.commontransports.refinery.entity.RefineryBlockEntity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;
import java.util.function.Supplier;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
        DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, GenericMod.MODID);

    public static final Supplier<BlockEntityType<RefineryBlockEntity>> REFINERY =
        BLOCK_ENTITIES.register("refinery", () ->
            new BlockEntityType<>(RefineryBlockEntity::new, Set.of(ModBlocks.REFINERY.get())));

    public static final Supplier<BlockEntityType<DistillationTowerBlockEntity>> DISTILLATION_TOWER =
        BLOCK_ENTITIES.register("distillation_tower", () ->
            new BlockEntityType<>(DistillationTowerBlockEntity::new, Set.of(ModBlocks.DISTILLATION_TOWER.get())));

    public static final Supplier<BlockEntityType<CatalyticReformerBlockEntity>> CATALYTIC_REFORMER =
        BLOCK_ENTITIES.register("catalytic_reformer", () ->
            new BlockEntityType<>(CatalyticReformerBlockEntity::new, Set.of(ModBlocks.CATALYTIC_REFORMER.get())));

    public static final Supplier<BlockEntityType<CreativeEnergyCellBlockEntity>> CREATIVE_ENERGY_CELL =
        BLOCK_ENTITIES.register("creative_energy_cell", () ->
            new BlockEntityType<>(CreativeEnergyCellBlockEntity::new, Set.of(ModBlocks.CREATIVE_ENERGY_CELL.get())));

    public static final Supplier<BlockEntityType<BasicFluidPipeBlockEntity>> BASIC_FLUID_PIPE =
        BLOCK_ENTITIES.register("basic_fluid_pipe", () ->
            new BlockEntityType<>(BasicFluidPipeBlockEntity::new, Set.of(ModBlocks.BASIC_FLUID_PIPE.get())));
    
    public static void register(IEventBus modEventBus) {
        BLOCK_ENTITIES.register(modEventBus);
    }
}
