package com.example.commontransports.menu;

import com.example.commontransports.GenericMod;
import com.example.commontransports.pipe.menu.BasicFluidPipeMenu;
import com.example.commontransports.processing.menu.CatalyticReformerMenu;
import com.example.commontransports.processing.menu.DistillationTowerMenu;
import com.example.commontransports.refinery.menu.RefineryMenu;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS =
        DeferredRegister.create(Registries.MENU, GenericMod.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<RefineryMenu>> REFINERY =
        MENUS.register("refinery", () -> IMenuTypeExtension.create(RefineryMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<DistillationTowerMenu>> DISTILLATION_TOWER =
        MENUS.register("distillation_tower", () -> IMenuTypeExtension.create(DistillationTowerMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<CatalyticReformerMenu>> CATALYTIC_REFORMER =
        MENUS.register("catalytic_reformer", () -> IMenuTypeExtension.create(CatalyticReformerMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<BasicFluidPipeMenu>> BASIC_FLUID_PIPE =
        MENUS.register("basic_fluid_pipe", () -> IMenuTypeExtension.create(BasicFluidPipeMenu::new));
    
    public static void register(IEventBus modEventBus) {
        MENUS.register(modEventBus);
    }
}
