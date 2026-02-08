package com.example.commontransports.client.screen;

import com.example.commontransports.api.menu.ProcessingMenu;
import com.example.commontransports.client.gui.GuiUtils;
import com.example.commontransports.network.ProcessingActionPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.material.Fluid;

import java.util.ArrayList;
import java.util.List;

/**
 * Shared machine screen layout/tooltip behavior for processing blocks.
 * Subclasses only provide fluid/process-specific details.
 */
public abstract class UnifiedProcessingScreen<M extends AbstractContainerMenu & ProcessingMenu>
        extends AbstractProcessingScreen<M> {

    protected static final int PANEL_BG = 0xFFC6C6C6;
    protected static final int BORDER_LIGHT = 0xFFFFFFFF;
    protected static final int BORDER_DARK = 0xFF555555;
    protected static final int INSET_DARK = 0xFF373737;
    protected static final int INSET_LIGHT = 0xFFFFFFFF;
    protected static final int SLOT_BG = 0xFF8B8B8B;

    protected static final int INPUT_TANK_X = 44;
    protected static final int INPUT_TANK_Y = 18;
    protected static final int OUTPUT_TANK_X = 116;
    protected static final int OUTPUT_TANK_Y = 18;
    protected static final int ARROW_X = 76;
    protected static final int ARROW_Y = 35;
    protected static final int ARROW_WIDTH = 24;
    protected static final int ARROW_HEIGHT = 17;
    protected static final int ENERGY_X = 150;
    protected static final int ENERGY_Y = 18;
    protected static final int ENERGY_WIDTH = 10;
    protected static final int ENERGY_HEIGHT = 52;

    protected static final int COLOR_AMOUNT = 0xD4A017;
    protected static final int COLOR_HINT = 0x555555;
    protected static final int COLOR_ENERGY = 0xFF4FC3F7;

    protected UnifiedProcessingScreen(M menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, 176, 166);
    }

    protected abstract Fluid getInputFluid();
    protected abstract Fluid getOutputFluid();
    protected abstract Component getInputFluidName();
    protected abstract Component getOutputFluidName();
    protected abstract String getProcessLabel();

    protected void appendOutputTooltipLines(List<Component> lines) {
        if (menu.getOutputAmount() > 0) {
            lines.add(Component.literal("Right-click to fill held fluid container")
                    .withStyle(style -> style.withColor(COLOR_HINT)));
        }
    }

    private void sendProcessingAction(int actionId) {
        var conn = Minecraft.getInstance().getConnection();
        if (conn != null) {
            conn.send(new ServerboundCustomPayloadPacket(
                    new ProcessingActionPayload(menu.getBlockPos(), actionId)));
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        int x = leftPos;
        int y = topPos;
        int w = imageWidth;
        int h = imageHeight;

        GuiUtils.fill(graphics, x, y, w, h, PANEL_BG);
        GuiUtils.fill(graphics, x, y, w, 2, BORDER_LIGHT);
        GuiUtils.fill(graphics, x, y, 2, h, BORDER_LIGHT);
        GuiUtils.fill(graphics, x, y + h - 2, w, 2, BORDER_DARK);
        GuiUtils.fill(graphics, x + w - 2, y, 2, h, BORDER_DARK);

        int contentX = x + 6;
        int contentY = y + 14;
        int contentW = w - 12;
        int contentH = 60;
        GuiUtils.fill(graphics, contentX, contentY, contentW, 1, INSET_DARK);
        GuiUtils.fill(graphics, contentX, contentY, 1, contentH, INSET_DARK);
        GuiUtils.fill(graphics, contentX, contentY + contentH - 1, contentW, 1, INSET_LIGHT);
        GuiUtils.fill(graphics, contentX + contentW - 1, contentY, 1, contentH, INSET_LIGHT);
        GuiUtils.fill(graphics, contentX + 1, contentY + 1, contentW - 2, contentH - 2, SLOT_BG);

        for (Slot slot : menu.slots) {
            int sx = x + slot.x - 1;
            int sy = y + slot.y - 1;
            GuiUtils.fill(graphics, sx, sy, 18, 1, INSET_DARK);
            GuiUtils.fill(graphics, sx, sy, 1, 18, INSET_DARK);
            GuiUtils.fill(graphics, sx + 1, sy + 17, 17, 1, INSET_LIGHT);
            GuiUtils.fill(graphics, sx + 17, sy + 1, 1, 17, INSET_LIGHT);
            GuiUtils.fill(graphics, sx + 1, sy + 1, 16, 16, SLOT_BG);
        }

        drawTank(graphics, leftPos + INPUT_TANK_X, topPos + INPUT_TANK_Y, TANK_WIDTH, TANK_HEIGHT,
                menu.getInputScaled(TANK_HEIGHT), getInputFluid());
        drawTank(graphics, leftPos + OUTPUT_TANK_X, topPos + OUTPUT_TANK_Y, TANK_WIDTH, TANK_HEIGHT,
                menu.getOutputScaled(TANK_HEIGHT), getOutputFluid());

        int progressPx = menu.getProgressScaled(ARROW_WIDTH - 2);
        drawProgressBar(graphics, leftPos + ARROW_X, topPos + ARROW_Y, ARROW_WIDTH, ARROW_HEIGHT, progressPx, 0xFF00AA00);

        int energyPx = menu.getEnergyScaled(ENERGY_HEIGHT - 2);
        drawVerticalBar(graphics, leftPos + ENERGY_X, topPos + ENERGY_Y, ENERGY_WIDTH, ENERGY_HEIGHT, energyPx, COLOR_ENERGY);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);

        List<Component> tooltipLines = null;
        if (isHovering(INPUT_TANK_X, INPUT_TANK_Y, TANK_WIDTH, TANK_HEIGHT, mouseX, mouseY)) {
            tooltipLines = List.of(
                    getInputFluidName(),
                    Component.literal(menu.getInputAmount() + " / " + menu.getInputCapacity() + " mB")
                            .withStyle(style -> style.withColor(COLOR_AMOUNT)));
        } else if (isHovering(OUTPUT_TANK_X, OUTPUT_TANK_Y, TANK_WIDTH, TANK_HEIGHT, mouseX, mouseY)) {
            var lines = new ArrayList<Component>();
            lines.add(getOutputFluidName());
            lines.add(Component.literal(menu.getOutputAmount() + " / " + menu.getOutputCapacity() + " mB")
                    .withStyle(style -> style.withColor(COLOR_AMOUNT)));
            appendOutputTooltipLines(lines);
            tooltipLines = lines;
        } else if (isHovering(ARROW_X, ARROW_Y, ARROW_WIDTH, ARROW_HEIGHT, mouseX, mouseY)) {
            int maxProgress = menu.getProcessTime();
            int percent = maxProgress > 0 ? menu.getProgress() * 100 / maxProgress : 0;
            tooltipLines = List.of(
                    Component.literal(getProcessLabel() + ": " + percent + "%")
                            .withStyle(style -> style.withColor(COLOR_AMOUNT)));
        } else if (isHovering(ENERGY_X, ENERGY_Y, ENERGY_WIDTH, ENERGY_HEIGHT, mouseX, mouseY)) {
            tooltipLines = List.of(
                    Component.literal("Energy"),
                    Component.literal(menu.getEnergyStored() + " / " + menu.getEnergyCapacity() + " FE")
                            .withStyle(style -> style.withColor(COLOR_ENERGY)));
        }

        if (tooltipLines != null && !tooltipLines.isEmpty()) {
            List<ClientTooltipComponent> components = new ArrayList<>();
            for (Component line : tooltipLines) {
                components.add(ClientTooltipComponent.create(line.getVisualOrderText()));
            }
            ClientTooltipPositioner positioner = (screenW, screenH, tipX, tipY, tipW, tipH) -> {
                int px = Math.min(tipX + 12, screenW - tipW - 4);
                int py = tipY - 12 < 0 ? tipY + 12 : tipY - 12;
                return new org.joml.Vector2i(px, py);
            };
            graphics.renderTooltip(font, components, mouseX, mouseY, positioner, null);
        } else {
            renderTooltip(graphics, mouseX, mouseY);
        }
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        // Right-click the output tank to move fluid into the held container.
        if (event.button() == 1 && isHovering(
                OUTPUT_TANK_X, OUTPUT_TANK_Y, TANK_WIDTH, TANK_HEIGHT, (int) event.x(), (int) event.y())) {
            sendProcessingAction(ProcessingActionPayload.ACTION_PICKUP_OUTPUT);
            return true;
        }
        return super.mouseClicked(event, doubleClick);
    }
}
