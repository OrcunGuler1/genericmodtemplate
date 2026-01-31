package com.example.commontransports.block;

import com.example.commontransports.GenericMod;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(GenericMod.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GenericMod.MODID);
    
    // Refinery Block
    public static final DeferredBlock<RefineryBlock> REFINERY = BLOCKS.registerBlock("refinery",
        RefineryBlock::new,
        BlockBehaviour.Properties.of()
            .strength(3.5F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.METAL));
    
    // Refinery Block Item
    public static final DeferredItem<BlockItem> REFINERY_ITEM = ITEMS.registerSimpleBlockItem(REFINERY);
    
    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
    }
}
