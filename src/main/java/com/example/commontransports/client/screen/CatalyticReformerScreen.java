package com.example.commontransports.client.screen;

import com.example.commontransports.fluid.ModFluids;
import com.example.commontransports.processing.menu.CatalyticReformerMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluid;

public class CatalyticReformerScreen extends UnifiedProcessingScreen<CatalyticReformerMenu> {

    public CatalyticReformerScreen(CatalyticReformerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected Fluid getInputFluid() {
        return ModFluids.NAPHTHA_SOURCE.get();
    }

    @Override
    protected Fluid getOutputFluid() {
        return ModFluids.REFORMATE_SOURCE.get();
    }

    @Override
    protected Component getInputFluidName() {
        return Component.translatable("fluid_type.common_transports.naphtha");
    }

    @Override
    protected Component getOutputFluidName() {
        return Component.translatable("fluid_type.common_transports.reformate");
    }

    @Override
    protected String getProcessLabel() {
        return "Reforming";
    }
}
