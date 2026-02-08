package com.example.commontransports.client.screen;

import com.example.commontransports.fluid.ModFluids;
import com.example.commontransports.network.RefineryActionPayload;
import com.example.commontransports.refinery.menu.RefineryMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluid;

import java.util.List;

public class RefineryScreen extends UnifiedProcessingScreen<RefineryMenu> {

    /** Flush buttons sit beside each tank, near the bottom. */
    private static final int FLUSH_BUTTON_SIZE = 8;
    private Button flushInputButton;
    private Button flushOutputButton;

    public RefineryScreen(RefineryMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    private void sendRefineryAction(int actionId) {
        var conn = Minecraft.getInstance().getConnection();
        if (conn != null) {
            conn.send(new ServerboundCustomPayloadPacket(
                    new RefineryActionPayload(menu.getBlockPos(), actionId)));
        }
    }

    @Override
    protected void init() {
        super.init();
        int flushY = topPos + INPUT_TANK_Y + TANK_HEIGHT - FLUSH_BUTTON_SIZE;
        int inputBtnX = leftPos + INPUT_TANK_X + TANK_WIDTH + 1;
        int outputBtnX = leftPos + OUTPUT_TANK_X - FLUSH_BUTTON_SIZE - 1;

        flushInputButton = addRenderableWidget(Button.builder(Component.literal("x"),
                b -> sendRefineryAction(RefineryMenu.ACTION_FLUSH_INPUT))
                .bounds(inputBtnX, flushY, FLUSH_BUTTON_SIZE, FLUSH_BUTTON_SIZE)
                .build());
        flushOutputButton = addRenderableWidget(Button.builder(Component.literal("x"),
                b -> sendRefineryAction(RefineryMenu.ACTION_FLUSH_OUTPUT))
                .bounds(outputBtnX, flushY, FLUSH_BUTTON_SIZE, FLUSH_BUTTON_SIZE)
                .build());
        updateFlushButtonVisibility();
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        updateFlushButtonVisibility();
    }

    private void updateFlushButtonVisibility() {
        boolean visible = isProcessPanelActive();
        if (flushInputButton != null) {
            flushInputButton.visible = visible;
            flushInputButton.active = visible;
        }
        if (flushOutputButton != null) {
            flushOutputButton.visible = visible;
            flushOutputButton.active = visible;
        }
    }

    @Override
    protected Fluid getInputFluid() {
        return ModFluids.REFORMATE_SOURCE.get();
    }

    @Override
    protected Fluid getOutputFluid() {
        return ModFluids.PETROL_SOURCE.get();
    }

    @Override
    protected Component getInputFluidName() {
        return Component.translatable("fluid_type.common_transports.reformate");
    }

    @Override
    protected Component getOutputFluidName() {
        return Component.translatable("fluid_type.common_transports.petrol");
    }

    @Override
    protected String getProcessLabel() {
        return "Refining";
    }

    @Override
    protected void appendOutputTooltipLines(List<Component> lines) {
        if (menu.getOutputAmount() > 0) {
            lines.add(Component.literal("Right-click to fill held fluid container")
                    .withStyle(style -> style.withColor(COLOR_HINT)));
            lines.add(Component.literal("Vanilla bucket pickup is disabled for petrol")
                    .withStyle(style -> style.withColor(COLOR_HINT)));
        }
    }
}
