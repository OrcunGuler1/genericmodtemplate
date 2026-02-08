package com.example.commontransports.client.screen;

import com.example.commontransports.processing.entity.AbstractPoweredFluidProcessorBlockEntity;
import com.example.commontransports.api.menu.ProcessingMenu;
import com.example.commontransports.client.gui.GuiUtils;
import com.example.commontransports.network.ProcessingActionPayload;
import com.example.commontransports.network.ProcessingSideConfigPayload;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import org.lwjgl.glfw.GLFW;

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
    protected static final int COLOR_ARROW = 0xFF7FA4C7;
    protected static final int COLOR_EFFECT = 0x66D169;
    protected static final int COLOR_TEXT = 0xFF2E2E2E;
    protected static final int MODE_DISABLED = 0xFF8A8A8A;
    protected static final int MODE_INPUT = 0xFFB63A3A;
    protected static final int MODE_OUTPUT = 0xFF3A5FB6;

    private static final int UPGRADE_PANEL_X = 8;
    private static final int UPGRADE_PANEL_Y = 18;
    private static final int UPGRADE_PANEL_WIDTH = 160;
    private static final int UPGRADE_PANEL_HEIGHT = 72;
    private static final int SIDE_PANEL_X = UPGRADE_PANEL_X;
    private static final int SIDE_PANEL_Y = UPGRADE_PANEL_Y;
    private static final int SIDE_PANEL_WIDTH = UPGRADE_PANEL_WIDTH;
    private static final int SIDE_PANEL_HEIGHT = 124;
    private static final int OVERLAY_HEADER_HEIGHT = 12;
    private static final int UPGRADE_LIST_X = UPGRADE_PANEL_X + 5;
    private static final int UPGRADE_LIST_Y = UPGRADE_PANEL_Y + OVERLAY_HEADER_HEIGHT + 2;
    private static final int UPGRADE_LIST_WIDTH = 102;
    private static final int UPGRADE_ROW_X = UPGRADE_LIST_X;
    private static final int UPGRADE_ROW_WIDTH = UPGRADE_LIST_WIDTH;
    private static final int UPGRADE_ROW_HEIGHT = 16;
    private static final int SPEED_ROW_Y = UPGRADE_LIST_Y;
    private static final int EFFICIENCY_ROW_Y = SPEED_ROW_Y + 18;
    private static final int UPGRADE_DETAIL_X = UPGRADE_LIST_X + UPGRADE_LIST_WIDTH + 4;
    private static final int UPGRADE_DETAIL_Y = UPGRADE_LIST_Y;
    private static final int UPGRADE_DETAIL_WIDTH = 44;
    private static final int UPGRADE_DETAIL_HEIGHT = 34;
    private static final int EJECT_BUTTON_X = UPGRADE_DETAIL_X + 2;
    private static final int EJECT_BUTTON_Y = UPGRADE_PANEL_Y + UPGRADE_PANEL_HEIGHT - 14 - 4;
    private static final int EJECT_BUTTON_WIDTH = UPGRADE_DETAIL_WIDTH - 4;
    private static final int EJECT_BUTTON_HEIGHT = 14;
    private static final int TOGGLE_BUTTON_SIZE = 12;
    private static final int TOGGLE_BUTTON_RIGHT_MARGIN = 4;
    private static final int TOGGLE_BUTTON_TOP_MARGIN = 3;
    private static final int SIDE_BUTTON_GAP = 0;
    private static final int SIDE_CONFIG_W = 104;
    private static final int SIDE_CONFIG_H = 104;
    private static final int SIDE_CONFIG_X = SIDE_PANEL_X + (SIDE_PANEL_WIDTH - SIDE_CONFIG_W) / 2;
    private static final int SIDE_CONFIG_Y = SIDE_PANEL_Y + OVERLAY_HEADER_HEIGHT + 2;
    private static final int SIDE_BUTTON_SIZE = 18;
    private static final int SIDE_STEP = SIDE_BUTTON_SIZE + SIDE_BUTTON_GAP;
    private static final int GRID_CENTER_X = SIDE_CONFIG_X + (SIDE_CONFIG_W - SIDE_BUTTON_SIZE) / 2;
    private static final int GRID_CENTER_Y = SIDE_CONFIG_Y + (SIDE_CONFIG_H - SIDE_BUTTON_SIZE) / 2;
    private static final int SIDE_CLEAR_BUTTON_X = SIDE_CONFIG_X + SIDE_CONFIG_W + 4;
    private static final int SIDE_CLEAR_BUTTON_Y = SIDE_PANEL_Y + 4;
    private static final int SIDE_CLEAR_BUTTON_W = 12;
    private static final int SIDE_CLEAR_BUTTON_H = 12;
    private static final int OVERLAY_CLAMP_MARGIN = 4;
    private static final int UPGRADE_SPEED = 0;
    private static final int UPGRADE_EFFICIENCY = 1;

    private enum PanelMode {
        PROCESS,
        UPGRADES,
        SIDES
    }

    private PanelMode panelMode = PanelMode.PROCESS;
    private int selectedUpgradeType = UPGRADE_SPEED;
    private Button toggleUpgradePanelButton;
    private Button toggleSidePanelButton;
    private Button ejectUpgradeButton;
    private Button clearSidesButton;
    private boolean draggingOverlay = false;
    private PanelMode draggingPanel = PanelMode.PROCESS;
    private int dragOffsetX;
    private int dragOffsetY;
    private int upgradePanelOffsetX = 0;
    private int upgradePanelOffsetY = 0;
    private int sidePanelOffsetX = 0;
    private int sidePanelOffsetY = 0;

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

    private void sendProcessingSideAction(int sideOrdinal, int actionId) {
        var conn = Minecraft.getInstance().getConnection();
        if (conn != null) {
            conn.send(new ServerboundCustomPayloadPacket(
                    new ProcessingSideConfigPayload(menu.getBlockPos(), sideOrdinal, actionId)));
        }
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = this.imageHeight - 94;

        toggleUpgradePanelButton = addRenderableWidget(Button.builder(Component.literal("U"), b -> {
                    panelMode = panelMode == PanelMode.UPGRADES ? PanelMode.PROCESS : PanelMode.UPGRADES;
                    updateUpgradeButtons();
                }).bounds(
                        leftPos + imageWidth - TOGGLE_BUTTON_SIZE - TOGGLE_BUTTON_RIGHT_MARGIN,
                        topPos + TOGGLE_BUTTON_TOP_MARGIN,
                        TOGGLE_BUTTON_SIZE,
                        TOGGLE_BUTTON_SIZE
                )
                .build());
        toggleSidePanelButton = addRenderableWidget(Button.builder(Component.literal("S"), b -> {
                    panelMode = panelMode == PanelMode.SIDES ? PanelMode.PROCESS : PanelMode.SIDES;
                    updateUpgradeButtons();
                }).bounds(
                        leftPos + imageWidth - (TOGGLE_BUTTON_SIZE * 2) - TOGGLE_BUTTON_RIGHT_MARGIN - 2,
                        topPos + TOGGLE_BUTTON_TOP_MARGIN,
                        TOGGLE_BUTTON_SIZE,
                        TOGGLE_BUTTON_SIZE
                )
                .build());

        ejectUpgradeButton = addRenderableWidget(Button.builder(Component.literal("Eject"),
                        b -> sendProcessingAction(selectedUpgradeType == UPGRADE_SPEED
                                ? ProcessingActionPayload.ACTION_EJECT_SPEED_UPGRADE
                                : ProcessingActionPayload.ACTION_EJECT_EFFICIENCY_UPGRADE))
                .bounds(leftPos + EJECT_BUTTON_X, topPos + EJECT_BUTTON_Y, EJECT_BUTTON_WIDTH, EJECT_BUTTON_HEIGHT)
                .build());
        clearSidesButton = addRenderableWidget(Button.builder(Component.literal("x"),
                        b -> sendProcessingSideAction(
                                ProcessingSideConfigPayload.SIDE_ALL,
                                ProcessingSideConfigPayload.ACTION_CLEAR_ALL_SIDES))
                .bounds(leftPos + SIDE_CLEAR_BUTTON_X, topPos + SIDE_CLEAR_BUTTON_Y, SIDE_CLEAR_BUTTON_W, SIDE_CLEAR_BUTTON_H)
                .build());

        updateFloatingWidgetPositions();
        updateUpgradeButtons();
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        updateFloatingWidgetPositions();
        updateUpgradeButtons();
    }

    private void updateUpgradeButtons() {
        if (toggleUpgradePanelButton == null || toggleSidePanelButton == null || ejectUpgradeButton == null || clearSidesButton == null) {
            return;
        }
        toggleUpgradePanelButton.setMessage(panelMode == PanelMode.UPGRADES ? Component.literal("P") : Component.literal("U"));
        toggleSidePanelButton.setMessage(panelMode == PanelMode.SIDES ? Component.literal("P") : Component.literal("S"));
        ejectUpgradeButton.visible = panelMode == PanelMode.UPGRADES;
        ejectUpgradeButton.active = panelMode == PanelMode.UPGRADES && getSelectedUpgradeCount() > 0;
        clearSidesButton.visible = panelMode == PanelMode.SIDES;
        clearSidesButton.active = panelMode == PanelMode.SIDES;
    }

    private int getSelectedUpgradeCount() {
        return selectedUpgradeType == UPGRADE_SPEED ? menu.getSpeedUpgrades() : menu.getEfficiencyUpgrades();
    }

    protected final boolean isProcessPanelActive() {
        return panelMode == PanelMode.PROCESS;
    }

    private int ux(int x) {
        return leftPos + x + upgradePanelOffsetX;
    }

    private int uy(int y) {
        return topPos + y + upgradePanelOffsetY;
    }

    private int sx(int x) {
        return leftPos + x + sidePanelOffsetX;
    }

    private int sy(int y) {
        return topPos + y + sidePanelOffsetY;
    }

    private void updateFloatingWidgetPositions() {
        if (ejectUpgradeButton != null) {
            ejectUpgradeButton.setX(ux(EJECT_BUTTON_X));
            ejectUpgradeButton.setY(uy(EJECT_BUTTON_Y));
        }
        if (clearSidesButton != null) {
            clearSidesButton.setX(sx(SIDE_CLEAR_BUTTON_X));
            clearSidesButton.setY(sy(SIDE_CLEAR_BUTTON_Y));
        }
    }

    private void applyOverlayDrag(int mouseX, int mouseY) {
        if (draggingPanel == PanelMode.UPGRADES) {
            int targetX = mouseX - dragOffsetX;
            int targetY = mouseY - dragOffsetY;
            int minX = OVERLAY_CLAMP_MARGIN;
            int minY = OVERLAY_CLAMP_MARGIN;
            int maxX = this.width - UPGRADE_PANEL_WIDTH - OVERLAY_CLAMP_MARGIN;
            int maxY = this.height - UPGRADE_PANEL_HEIGHT - OVERLAY_CLAMP_MARGIN;
            int clampedX = Math.max(minX, Math.min(targetX, maxX));
            int clampedY = Math.max(minY, Math.min(targetY, maxY));
            upgradePanelOffsetX = clampedX - (leftPos + UPGRADE_PANEL_X);
            upgradePanelOffsetY = clampedY - (topPos + UPGRADE_PANEL_Y);
        } else if (draggingPanel == PanelMode.SIDES) {
            int targetX = mouseX - dragOffsetX;
            int targetY = mouseY - dragOffsetY;
            int minX = OVERLAY_CLAMP_MARGIN;
            int minY = OVERLAY_CLAMP_MARGIN;
            int maxX = this.width - SIDE_PANEL_WIDTH - OVERLAY_CLAMP_MARGIN;
            int maxY = this.height - SIDE_PANEL_HEIGHT - OVERLAY_CLAMP_MARGIN;
            int clampedX = Math.max(minX, Math.min(targetX, maxX));
            int clampedY = Math.max(minY, Math.min(targetY, maxY));
            sidePanelOffsetX = clampedX - (leftPos + SIDE_PANEL_X);
            sidePanelOffsetY = clampedY - (topPos + SIDE_PANEL_Y);
        }
        updateFloatingWidgetPositions();
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

        for (Slot slot : menu.slots) {
            int sx = x + slot.x - 1;
            int sy = y + slot.y - 1;
            GuiUtils.fill(graphics, sx, sy, 18, 1, INSET_DARK);
            GuiUtils.fill(graphics, sx, sy, 1, 18, INSET_DARK);
            GuiUtils.fill(graphics, sx + 1, sy + 17, 17, 1, INSET_LIGHT);
            GuiUtils.fill(graphics, sx + 17, sy + 1, 1, 17, INSET_LIGHT);
            GuiUtils.fill(graphics, sx + 1, sy + 1, 16, 16, SLOT_BG);
        }

        GuiUtils.fill(graphics, contentX, contentY, contentW, 1, INSET_DARK);
        GuiUtils.fill(graphics, contentX, contentY, 1, contentH, INSET_DARK);
        GuiUtils.fill(graphics, contentX, contentY + contentH - 1, contentW, 1, INSET_LIGHT);
        GuiUtils.fill(graphics, contentX + contentW - 1, contentY, 1, contentH, INSET_LIGHT);
        GuiUtils.fill(graphics, contentX + 1, contentY + 1, contentW - 2, contentH - 2, SLOT_BG);

        drawTank(graphics, leftPos + INPUT_TANK_X, topPos + INPUT_TANK_Y, TANK_WIDTH, TANK_HEIGHT,
                menu.getInputScaled(TANK_HEIGHT), getInputFluid());
        drawTank(graphics, leftPos + OUTPUT_TANK_X, topPos + OUTPUT_TANK_Y, TANK_WIDTH, TANK_HEIGHT,
                menu.getOutputScaled(TANK_HEIGHT), getOutputFluid());

        drawStaticArrow(graphics, leftPos + ARROW_X, topPos + ARROW_Y, ARROW_WIDTH, ARROW_HEIGHT);

        int energyPx = menu.getEnergyScaled(ENERGY_HEIGHT - 2);
        drawVerticalBar(graphics, leftPos + ENERGY_X, topPos + ENERGY_Y, ENERGY_WIDTH, ENERGY_HEIGHT, energyPx, COLOR_ENERGY);

    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        renderActiveOverlay(graphics, mouseX, mouseY, partialTick);

        List<Component> tooltipLines = null;
        if (panelMode == PanelMode.UPGRADES) {
            if (contains(mouseX, mouseY, ux(UPGRADE_ROW_X), uy(SPEED_ROW_Y), UPGRADE_ROW_WIDTH, UPGRADE_ROW_HEIGHT)) {
                int speedUpgrades = menu.getSpeedUpgrades();
                tooltipLines = List.of(
                        Component.literal("Speed upgrade"),
                        Component.literal("Installed: " + speedUpgrades + " / " + menu.getMaxUpgradesPerType()),
                        Component.literal("Rate bonus: +" + (speedUpgrades * 2) + " mB/t")
                                .withStyle(style -> style.withColor(COLOR_EFFECT)));
            } else if (contains(mouseX, mouseY, ux(UPGRADE_ROW_X), uy(EFFICIENCY_ROW_Y), UPGRADE_ROW_WIDTH, UPGRADE_ROW_HEIGHT)) {
                int efficiencyUpgrades = menu.getEfficiencyUpgrades();
                tooltipLines = List.of(
                        Component.literal("Efficiency upgrade"),
                        Component.literal("Installed: " + efficiencyUpgrades + " / " + menu.getMaxUpgradesPerType()),
                        Component.literal("Energy reduction: -" + (efficiencyUpgrades * 10) + "% FE/t")
                                .withStyle(style -> style.withColor(COLOR_EFFECT)));
            } else if (ejectUpgradeButton != null && ejectUpgradeButton.visible && ejectUpgradeButton.isHoveredOrFocused()) {
                tooltipLines = List.of(
                        Component.literal("Eject selected upgrade"),
                        Component.literal("Returns one upgrade item to your inventory")
                                .withStyle(style -> style.withColor(COLOR_HINT)));
            }
        } else if (panelMode == PanelMode.SIDES) {
            Direction hoveredSide = getHoveredSide(mouseX, mouseY);
            if (hoveredSide != null) {
                renderSideTooltip(graphics, mouseX, mouseY, hoveredSide);
                return;
            }
            if (contains(mouseX, mouseY,
                    sx(SIDE_CLEAR_BUTTON_X), sy(SIDE_CLEAR_BUTTON_Y), SIDE_CLEAR_BUTTON_W, SIDE_CLEAR_BUTTON_H)) {
                renderSingleTooltip(graphics, mouseX, mouseY,
                        Component.translatable("gui.common_transports.pipe.clear_all_sides"));
                return;
            }
        } else if (isHovering(INPUT_TANK_X, INPUT_TANK_Y, TANK_WIDTH, TANK_HEIGHT, mouseX, mouseY)) {
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

    private void renderActiveOverlay(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        if (panelMode == PanelMode.UPGRADES) {
            drawUpgradePanel(graphics);
        } else if (panelMode == PanelMode.SIDES) {
            drawSideConfigPanel(graphics);
        } else {
            return;
        }

        // Keep top-right panel toggles under the overlay (do not redraw them here).
        if (panelMode == PanelMode.UPGRADES && ejectUpgradeButton != null && ejectUpgradeButton.visible) {
            ejectUpgradeButton.render(graphics, mouseX, mouseY, partialTick);
        }
        if (panelMode == PanelMode.SIDES && clearSidesButton != null && clearSidesButton.visible) {
            clearSidesButton.render(graphics, mouseX, mouseY, partialTick);
        }
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        if (panelMode == PanelMode.UPGRADES && event.button() == 0) {
            int mouseX = (int) event.x();
            int mouseY = (int) event.y();
            if (contains(mouseX, mouseY, ux(UPGRADE_ROW_X), uy(SPEED_ROW_Y), UPGRADE_ROW_WIDTH, UPGRADE_ROW_HEIGHT)) {
                selectedUpgradeType = UPGRADE_SPEED;
                updateUpgradeButtons();
                return true;
            }
            if (contains(mouseX, mouseY, ux(UPGRADE_ROW_X), uy(EFFICIENCY_ROW_Y), UPGRADE_ROW_WIDTH, UPGRADE_ROW_HEIGHT)) {
                selectedUpgradeType = UPGRADE_EFFICIENCY;
                updateUpgradeButtons();
                return true;
            }
            if (contains(mouseX, mouseY, ux(UPGRADE_PANEL_X), uy(UPGRADE_PANEL_Y), UPGRADE_PANEL_WIDTH, UPGRADE_PANEL_HEIGHT)
                    && (ejectUpgradeButton == null
                    || !contains(mouseX, mouseY, ejectUpgradeButton.getX(), ejectUpgradeButton.getY(),
                    ejectUpgradeButton.getWidth(), ejectUpgradeButton.getHeight()))) {
                draggingOverlay = true;
                draggingPanel = PanelMode.UPGRADES;
                dragOffsetX = mouseX - ux(UPGRADE_PANEL_X);
                dragOffsetY = mouseY - uy(UPGRADE_PANEL_Y);
                return true;
            }
        }
        if (panelMode == PanelMode.SIDES) {
            int mouseX = (int) event.x();
            int mouseY = (int) event.y();
            Direction hoveredSide = getHoveredSide(mouseX, mouseY);
            if (hoveredSide != null) {
                if (event.button() == 1) {
                    sendProcessingSideAction(hoveredSide.ordinal(), ProcessingSideConfigPayload.ACTION_CYCLE_PREVIOUS);
                    return true;
                }
                if (event.button() == 0) {
                    if (isShiftDown()) {
                        sendProcessingSideAction(hoveredSide.ordinal(), ProcessingSideConfigPayload.ACTION_SET_DISABLED);
                    } else {
                        sendProcessingSideAction(hoveredSide.ordinal(), ProcessingSideConfigPayload.ACTION_CYCLE_NEXT);
                    }
                    return true;
                }
            }
            if (event.button() == 0
                    && contains(mouseX, mouseY, sx(SIDE_PANEL_X), sy(SIDE_PANEL_Y), SIDE_PANEL_WIDTH, SIDE_PANEL_HEIGHT)
                    && (clearSidesButton == null
                    || !contains(mouseX, mouseY, clearSidesButton.getX(), clearSidesButton.getY(),
                    clearSidesButton.getWidth(), clearSidesButton.getHeight()))) {
                draggingOverlay = true;
                draggingPanel = PanelMode.SIDES;
                dragOffsetX = mouseX - sx(SIDE_PANEL_X);
                dragOffsetY = mouseY - sy(SIDE_PANEL_Y);
                return true;
            }
        }

        // Right-click the output tank to move fluid into the held container.
        if (panelMode == PanelMode.PROCESS && event.button() == 1 && isHovering(
                OUTPUT_TANK_X, OUTPUT_TANK_Y, TANK_WIDTH, TANK_HEIGHT, (int) event.x(), (int) event.y())) {
            sendProcessingAction(ProcessingActionPayload.ACTION_PICKUP_OUTPUT);
            return true;
        }
        return super.mouseClicked(event, doubleClick);
    }

    @Override
    public boolean mouseDragged(MouseButtonEvent event, double dragX, double dragY) {
        if (draggingOverlay && event.button() == 0) {
            applyOverlayDrag((int) event.x(), (int) event.y());
            return true;
        }
        return super.mouseDragged(event, dragX, dragY);
    }

    @Override
    public boolean mouseReleased(MouseButtonEvent event) {
        boolean handled = super.mouseReleased(event);
        if (draggingOverlay && event.button() == 0) {
            draggingOverlay = false;
            draggingPanel = PanelMode.PROCESS;
            return true;
        }
        return handled;
    }

    private void drawUpgradePanel(GuiGraphics graphics) {
        int panelX = ux(UPGRADE_PANEL_X);
        int panelY = uy(UPGRADE_PANEL_Y);
        int listX = ux(UPGRADE_LIST_X);
        int listY = uy(UPGRADE_LIST_Y);
        int listHeight = UPGRADE_ROW_HEIGHT * 2 + 2;
        int detailX = ux(UPGRADE_DETAIL_X);
        int detailY = uy(UPGRADE_DETAIL_Y);

        drawGuiFrameBox(graphics, panelX, panelY, UPGRADE_PANEL_WIDTH, UPGRADE_PANEL_HEIGHT, PANEL_BG, Component.literal("Upgrades"));
        drawInsetBox(graphics, listX, listY, UPGRADE_LIST_WIDTH, listHeight, 0xFF9F9F9F);
        drawInsetBox(graphics, detailX, detailY, UPGRADE_DETAIL_WIDTH, UPGRADE_DETAIL_HEIGHT, 0xFF9F9F9F);

        drawUpgradeRow(graphics, UPGRADE_ROW_X, SPEED_ROW_Y, "Speed",
                menu.getSpeedUpgrades(), menu.getMaxUpgradesPerType(),
                selectedUpgradeType == UPGRADE_SPEED);
        drawUpgradeRow(graphics, UPGRADE_ROW_X, EFFICIENCY_ROW_Y, "Efficiency",
                menu.getEfficiencyUpgrades(), menu.getMaxUpgradesPerType(),
                selectedUpgradeType == UPGRADE_EFFICIENCY);

        boolean speedSelected = selectedUpgradeType == UPGRADE_SPEED;
        int selectedCount = speedSelected ? menu.getSpeedUpgrades() : menu.getEfficiencyUpgrades();
        String selectedLabel = speedSelected ? "Speed" : "Eff";
        String effectLine = speedSelected ? "Fx +" + (selectedCount * 2) : "Fx -" + (selectedCount * 10) + "%";
        String selectedAmount = "Amt " + selectedCount + "/" + menu.getMaxUpgradesPerType();

        graphics.drawString(font, selectedLabel, detailX + 5, detailY + 4, COLOR_TEXT, false);
        graphics.drawString(font, selectedAmount, detailX + 3, detailY + 14, COLOR_TEXT, false);
        graphics.drawString(font, effectLine, detailX + 3, detailY + 24, COLOR_EFFECT, false);

        String summary = menu.getEffectiveProcessRate() + " mB/t  |  " + menu.getEffectiveFePerTick() + " FE/t";
        graphics.drawString(font, summary,
                ux(UPGRADE_PANEL_X + 6), uy(UPGRADE_PANEL_Y + UPGRADE_PANEL_HEIGHT - 11),
                COLOR_TEXT, false);
    }

    private void drawUpgradeRow(
            GuiGraphics graphics, int rowX, int rowY, String label,
            int installed, int max, boolean selected
    ) {
        int x = ux(rowX);
        int y = uy(rowY);

        GuiUtils.drawOutline(graphics, x, y, UPGRADE_ROW_WIDTH, UPGRADE_ROW_HEIGHT, 0xFF4A4A4A);
        int rowFill = selected ? 0xFFB8C6D8 : 0xFFA3A3A3;
        GuiUtils.fill(graphics, x + 1, y + 1, UPGRADE_ROW_WIDTH - 2, UPGRADE_ROW_HEIGHT - 2, rowFill);

        String countText = installed + "/" + max;
        int countX = x + UPGRADE_ROW_WIDTH - 5 - font.width(countText);

        graphics.drawString(font, label, x + 4, y + 4, COLOR_TEXT, false);
        graphics.drawString(font, countText, countX, y + 4, COLOR_TEXT, false);
    }

    private void drawInsetBox(GuiGraphics graphics, int x, int y, int width, int height, int fillColor) {
        GuiUtils.fill(graphics, x, y, width, 1, INSET_DARK);
        GuiUtils.fill(graphics, x, y, 1, height, INSET_DARK);
        GuiUtils.fill(graphics, x, y + height - 1, width, 1, INSET_LIGHT);
        GuiUtils.fill(graphics, x + width - 1, y, 1, height, INSET_LIGHT);
        GuiUtils.fill(graphics, x + 1, y + 1, width - 2, height - 2, fillColor);
    }

    private void drawGuiFrameBox(
            GuiGraphics graphics, int x, int y, int width, int height, int fillColor, Component titleText
    ) {
        GuiUtils.fill(graphics, x, y, width, height, fillColor);
        GuiUtils.fill(graphics, x, y, width, 1, BORDER_LIGHT);
        GuiUtils.fill(graphics, x, y, 1, height, BORDER_LIGHT);
        GuiUtils.fill(graphics, x, y + height - 1, width, 1, BORDER_DARK);
        GuiUtils.fill(graphics, x + width - 1, y, 1, height, BORDER_DARK);
        graphics.drawString(font, titleText, x + 4, y + 3, COLOR_TEXT, false);
    }

    private void drawSideConfigPanel(GuiGraphics graphics) {
        drawGuiFrameBox(
                graphics, sx(SIDE_PANEL_X), sy(SIDE_PANEL_Y), SIDE_PANEL_WIDTH, SIDE_PANEL_HEIGHT,
                PANEL_BG, Component.literal("Side Config"));
        drawInsetBox(graphics, sx(SIDE_CONFIG_X), sy(SIDE_CONFIG_Y), SIDE_CONFIG_W, SIDE_CONFIG_H, SLOT_BG);

        for (Direction side : Direction.values()) {
            drawSideButton(graphics, side);
        }
    }

    private void drawSideButton(GuiGraphics graphics, Direction side) {
        int x = getSideButtonX(side);
        int y = getSideButtonY(side);
        AbstractPoweredFluidProcessorBlockEntity.SideMode mode = getSideMode(side);

        GuiUtils.drawOutline(graphics, x, y, SIDE_BUTTON_SIZE, SIDE_BUTTON_SIZE, 0xFF2F2F2F);
        if (mode == AbstractPoweredFluidProcessorBlockEntity.SideMode.PULL) {
            GuiUtils.fill(graphics, x + 1, y + 1, SIDE_BUTTON_SIZE - 2, SIDE_BUTTON_SIZE - 2, MODE_INPUT);
        } else if (mode == AbstractPoweredFluidProcessorBlockEntity.SideMode.PUSH) {
            GuiUtils.fill(graphics, x + 1, y + 1, SIDE_BUTTON_SIZE - 2, SIDE_BUTTON_SIZE - 2, MODE_OUTPUT);
        } else if (mode == AbstractPoweredFluidProcessorBlockEntity.SideMode.BOTH) {
            int inner = SIDE_BUTTON_SIZE - 2;
            int leftHalf = inner / 2;
            GuiUtils.fill(graphics, x + 1, y + 1, leftHalf, inner, MODE_INPUT);
            GuiUtils.fill(graphics, x + 1 + leftHalf, y + 1, inner - leftHalf, inner, MODE_OUTPUT);
        } else {
            GuiUtils.fill(graphics, x + 1, y + 1, SIDE_BUTTON_SIZE - 2, SIDE_BUTTON_SIZE - 2, MODE_DISABLED);
        }

        ItemStack stack = getNeighborStack(side);
        if (!stack.isEmpty()) {
            graphics.renderItem(stack, x + 1, y + 1);
        }
    }

    private int getSideButtonX(Direction side) {
        int col = switch (side) {
            case UP, NORTH, DOWN -> 1;
            case WEST, SOUTH -> 0;
            case EAST -> 2;
        };
        return sx(GRID_CENTER_X + (col - 1) * SIDE_STEP);
    }

    private int getSideButtonY(Direction side) {
        int row = switch (side) {
            case UP -> 0;
            case NORTH, WEST, EAST -> 1;
            case SOUTH, DOWN -> 2;
        };
        return sy(GRID_CENTER_Y + (row - 1) * SIDE_STEP);
    }

    private Direction getHoveredSide(int mouseX, int mouseY) {
        for (Direction side : Direction.values()) {
            int x = getSideButtonX(side);
            int y = getSideButtonY(side);
            if (contains(mouseX, mouseY, x, y, SIDE_BUTTON_SIZE, SIDE_BUTTON_SIZE)) {
                return side;
            }
        }
        return null;
    }

    private AbstractPoweredFluidProcessorBlockEntity.SideMode getSideMode(Direction side) {
        return AbstractPoweredFluidProcessorBlockEntity.SideMode.fromId(menu.getSideModeId(side));
    }

    private void renderSideTooltip(GuiGraphics graphics, int mouseX, int mouseY, Direction side) {
        String sideName = getDirectionLabel(side).getString();
        String modeName = Component.translatable(getModeTranslationKey(getSideMode(side))).getString();
        String adjacentName = getNeighborTooltipName(side);

        List<ClientTooltipComponent> components = new ArrayList<>();
        components.add(ClientTooltipComponent.create(Component.literal(sideName + ": " + modeName).getVisualOrderText()));
        components.add(ClientTooltipComponent.create(Component.literal(adjacentName).getVisualOrderText()));

        ClientTooltipPositioner positioner = (screenW, screenH, tipX, tipY, tipW, tipH) -> {
            int px = Math.min(tipX + 12, screenW - tipW - 4);
            int py = tipY - 12 < 0 ? tipY + 12 : tipY - 12;
            return new org.joml.Vector2i(px, py);
        };
        graphics.renderTooltip(font, components, mouseX, mouseY, positioner, null);
    }

    private void renderSingleTooltip(GuiGraphics graphics, int mouseX, int mouseY, Component text) {
        List<ClientTooltipComponent> components = new ArrayList<>();
        components.add(ClientTooltipComponent.create(text.getVisualOrderText()));
        ClientTooltipPositioner positioner = (screenW, screenH, tipX, tipY, tipW, tipH) -> {
            int px = Math.min(tipX + 12, screenW - tipW - 4);
            int py = tipY - 12 < 0 ? tipY + 12 : tipY - 12;
            return new org.joml.Vector2i(px, py);
        };
        graphics.renderTooltip(font, components, mouseX, mouseY, positioner, null);
    }

    private ItemStack getNeighborStack(Direction side) {
        BlockState state = getNeighborState(side);
        if (state == null || state.isAir()) {
            return ItemStack.EMPTY;
        }
        ItemStack stack = new ItemStack(state.getBlock());
        return stack.isEmpty() ? ItemStack.EMPTY : stack;
    }

    private String getNeighborTooltipName(Direction side) {
        BlockState state = getNeighborState(side);
        if (state == null || state.isAir()) {
            return Component.translatable("gui.common_transports.pipe.none").getString();
        }
        return state.getBlock().getName().getString();
    }

    private @org.jspecify.annotations.Nullable BlockState getNeighborState(Direction side) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null) return null;
        return minecraft.level.getBlockState(menu.getBlockPos().relative(side));
    }

    private static Component getDirectionLabel(Direction side) {
        return switch (side) {
            case DOWN -> Component.translatable("gui.common_transports.pipe.side.down");
            case UP -> Component.translatable("gui.common_transports.pipe.side.up");
            case NORTH -> Component.translatable("gui.common_transports.pipe.side.front");
            case SOUTH -> Component.translatable("gui.common_transports.pipe.side.back");
            case WEST -> Component.translatable("gui.common_transports.pipe.side.left");
            case EAST -> Component.translatable("gui.common_transports.pipe.side.right");
        };
    }

    private static String getModeTranslationKey(AbstractPoweredFluidProcessorBlockEntity.SideMode mode) {
        return switch (mode) {
            case DISABLED -> "gui.common_transports.pipe.mode.disabled";
            case PULL -> "gui.common_transports.pipe.mode.pull";
            case PUSH -> "gui.common_transports.pipe.mode.push";
            case BOTH -> "gui.common_transports.pipe.mode.both";
        };
    }

    private static boolean contains(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }

    private boolean isShiftDown() {
        var window = Minecraft.getInstance().getWindow();
        return InputConstants.isKeyDown(window, GLFW.GLFW_KEY_LEFT_SHIFT)
                || InputConstants.isKeyDown(window, GLFW.GLFW_KEY_RIGHT_SHIFT)
                || Minecraft.getInstance().options.keyShift.isDown();
    }

    private void drawStaticArrow(GuiGraphics graphics, int x, int y, int width, int height) {
        GuiUtils.drawOutline(graphics, x, y, width, height, 0xFF4A4A4A);
        GuiUtils.fill(graphics, x + 1, y + 1, width - 2, height - 2, 0xFF2A2A2A);

        int centerY = y + height / 2;
        int shaftX = x + 4;
        int shaftW = Math.max(2, width - 12);
        GuiUtils.fill(graphics, shaftX, centerY - 1, shaftW, 3, COLOR_ARROW);

        int tipX = shaftX + shaftW;
        GuiUtils.fill(graphics, tipX, centerY - 3, 1, 7, COLOR_ARROW);
        GuiUtils.fill(graphics, tipX + 1, centerY - 2, 1, 5, COLOR_ARROW);
        GuiUtils.fill(graphics, tipX + 2, centerY - 1, 1, 3, COLOR_ARROW);
    }
}
