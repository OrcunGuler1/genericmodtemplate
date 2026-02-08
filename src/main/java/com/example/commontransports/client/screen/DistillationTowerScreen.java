package com.example.commontransports.client.screen;

import com.example.commontransports.fluid.ModFluids;
import com.example.commontransports.processing.menu.DistillationTowerMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluid;

public class DistillationTowerScreen extends UnifiedProcessingScreen<DistillationTowerMenu> {

    public DistillationTowerScreen(DistillationTowerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected Fluid getInputFluid() {
        return ModFluids.CRUDE_OIL_SOURCE.get();
    }

    @Override
    protected Fluid getOutputFluid() {
        return ModFluids.NAPHTHA_SOURCE.get();
    }

    @Override
    protected Component getInputFluidName() {
        return Component.translatable("fluid_type.common_transports.crude_oil");
    }

    @Override
    protected Component getOutputFluidName() {
        return Component.translatable("fluid_type.common_transports.naphtha");
    }

    @Override
    protected String getProcessLabel() {
        return "Distillation";
    }
}
