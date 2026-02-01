package com.example.commontransports.block.entity;

import com.example.commontransports.GenericMod;
import com.example.commontransports.block.ModBlocks;
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
    
    public static void register(IEventBus modEventBus) {
        BLOCK_ENTITIES.register(modEventBus);
    }
}
