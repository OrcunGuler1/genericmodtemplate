package com.example.commontransports.block;

import com.example.commontransports.GenericMod;
import com.example.commontransports.pipe.block.BasicFluidPipeBlock;
import com.example.commontransports.power.block.CreativeEnergyCellBlock;
import com.example.commontransports.processing.block.CatalyticReformerBlock;
import com.example.commontransports.processing.block.DistillationTowerBlock;
import com.example.commontransports.refinery.block.RefineryBlock;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(GenericMod.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GenericMod.MODID);

    public static final DeferredBlock<RefineryBlock> REFINERY = BLOCKS.registerBlock("refinery",
        RefineryBlock::new,
        props -> props
            .strength(3.5F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.METAL));

    public static final DeferredBlock<DistillationTowerBlock> DISTILLATION_TOWER = BLOCKS.registerBlock("distillation_tower",
        DistillationTowerBlock::new,
        props -> props
            .strength(4.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.METAL));

    public static final DeferredBlock<CatalyticReformerBlock> CATALYTIC_REFORMER = BLOCKS.registerBlock("catalytic_reformer",
        CatalyticReformerBlock::new,
        props -> props
            .strength(4.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.METAL));

    public static final DeferredBlock<CreativeEnergyCellBlock> CREATIVE_ENERGY_CELL = BLOCKS.registerBlock("creative_energy_cell",
        CreativeEnergyCellBlock::new,
        props -> props
            .strength(4.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.METAL));

    public static final DeferredBlock<BasicFluidPipeBlock> BASIC_FLUID_PIPE = BLOCKS.registerBlock("basic_fluid_pipe",
        BasicFluidPipeBlock::new,
        props -> props
            .strength(2.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.METAL));
    
    // Refinery Block Item
    public static final DeferredItem<BlockItem> REFINERY_ITEM = ITEMS.registerSimpleBlockItem(REFINERY);
    public static final DeferredItem<BlockItem> DISTILLATION_TOWER_ITEM = ITEMS.registerSimpleBlockItem(DISTILLATION_TOWER);
    public static final DeferredItem<BlockItem> CATALYTIC_REFORMER_ITEM = ITEMS.registerSimpleBlockItem(CATALYTIC_REFORMER);
    public static final DeferredItem<BlockItem> CREATIVE_ENERGY_CELL_ITEM = ITEMS.registerSimpleBlockItem(CREATIVE_ENERGY_CELL);
    public static final DeferredItem<BlockItem> BASIC_FLUID_PIPE_ITEM = ITEMS.registerSimpleBlockItem(BASIC_FLUID_PIPE);
    
    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
    }
}
