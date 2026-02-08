package com.example.commontransports.client.screen;

import com.mojang.blaze3d.platform.InputConstants;
import com.example.commontransports.client.gui.GuiUtils;
import com.example.commontransports.network.PipeSideConfigPayload;
import com.example.commontransports.pipe.entity.BasicFluidPipeBlockEntity;
import com.example.commontransports.pipe.menu.BasicFluidPipeMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class BasicFluidPipeScreen extends net.minecraft.client.gui.screens.inventory.AbstractContainerScreen<BasicFluidPipeMenu> {

    private static final int PANEL_BG = 0xFFC6C6C6;
    private static final int BORDER_LIGHT = 0xFFFFFFFF;
    private static final int BORDER_DARK = 0xFF8A8A8A;

    private static final int MODE_DISABLED = 0xFF8A8A8A;
    private static final int MODE_INPUT = 0xFFB63A3A;
    private static final int MODE_OUTPUT = 0xFF3A5FB6;

    private static final int CONFIG_X = 36;
    private static final int CONFIG_Y = 26;
    private static final int CONFIG_W = 104;
    private static final int CONFIG_H = 104;

    private static final int BUTTON_SIZE = 18;
    private static final int SIDE_STEP = 18;

    private static final int CLEAR_BUTTON_X = CONFIG_X + CONFIG_W + 8;
    private static final int CLEAR_BUTTON_Y = CONFIG_Y;
    private static final int CLEAR_BUTTON_W = 12;
    private static final int CLEAR_BUTTON_H = 12;

    private static final int GRID_CENTER_X = CONFIG_X + (CONFIG_W - BUTTON_SIZE) / 2;
    private static final int GRID_CENTER_Y = CONFIG_Y + (CONFIG_H - BUTTON_SIZE) / 2;

    public BasicFluidPipeScreen(BasicFluidPipeMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 142;
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
        addRenderableWidget(Button.builder(
                        Component.literal("x"),
                        b -> sendPipeAction(PipeSideConfigPayload.SIDE_ALL, PipeSideConfigPayload.ACTION_CLEAR_ALL_SIDES))
                .bounds(leftPos + CLEAR_BUTTON_X, topPos + CLEAR_BUTTON_Y, CLEAR_BUTTON_W, CLEAR_BUTTON_H)
                .build());
    }

    private void sendPipeAction(int sideOrdinal, int actionId) {
        var conn = Minecraft.getInstance().getConnection();
        if (conn == null) return;
        conn.send(new ServerboundCustomPayloadPacket(
                new PipeSideConfigPayload(menu.getBlockPos(), sideOrdinal, actionId)));
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        int x = leftPos;
        int y = topPos;

        GuiUtils.fill(graphics, x, y, imageWidth, imageHeight, PANEL_BG);
        GuiUtils.fill(graphics, x, y, imageWidth, 2, BORDER_LIGHT);
        GuiUtils.fill(graphics, x, y, 2, imageHeight, BORDER_LIGHT);
        GuiUtils.fill(graphics, x, y + imageHeight - 2, imageWidth, 2, BORDER_DARK);
        GuiUtils.fill(graphics, x + imageWidth - 2, y, 2, imageHeight, BORDER_DARK);

        GuiUtils.drawOutline(graphics, x + CONFIG_X, y + CONFIG_Y, CONFIG_W, CONFIG_H, 0xFFECECEC);

        for (Direction side : Direction.values()) {
            drawSideButton(graphics, side);
        }
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        graphics.drawString(font, this.title, this.titleLabelX, this.titleLabelY, 0xFF202020, false);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);

        Direction hoveredSide = getHoveredSide(mouseX, mouseY);
        if (hoveredSide != null) {
            renderSideTooltip(graphics, mouseX, mouseY, hoveredSide);
        } else if (contains(mouseX, mouseY,
                leftPos + CLEAR_BUTTON_X, topPos + CLEAR_BUTTON_Y, CLEAR_BUTTON_W, CLEAR_BUTTON_H)) {
            renderSingleTooltip(graphics, mouseX, mouseY,
                    Component.translatable("gui.common_transports.pipe.clear_all_sides"));
        } else {
            renderTooltip(graphics, mouseX, mouseY);
        }
    }

    private void renderSideTooltip(GuiGraphics graphics, int mouseX, int mouseY, Direction side) {
        String sideName = getDirectionLabel(side).getString();
        String modeName = Component.translatable(getModeTranslationKey(menu.getSideMode(side))).getString();
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

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        int mouseX = (int) event.x();
        int mouseY = (int) event.y();

        for (Direction side : Direction.values()) {
            int buttonX = leftPos + getSideButtonX(side);
            int buttonY = topPos + getSideButtonY(side);
            if (!contains(mouseX, mouseY, buttonX, buttonY, BUTTON_SIZE, BUTTON_SIZE)) {
                continue;
            }

            if (event.button() == 1) {
                sendPipeAction(side.ordinal(), PipeSideConfigPayload.ACTION_CYCLE_PREVIOUS);
                return true;
            }
            if (event.button() == 0) {
                boolean shiftDown = isShiftDown();
                if (shiftDown) {
                    sendPipeAction(side.ordinal(), PipeSideConfigPayload.ACTION_SET_DISABLED);
                } else {
                    sendPipeAction(side.ordinal(), PipeSideConfigPayload.ACTION_CYCLE_NEXT);
                }
                return true;
            }
        }

        return super.mouseClicked(event, doubleClick);
    }

    private void drawSideButton(GuiGraphics graphics, Direction side) {
        int x = leftPos + getSideButtonX(side);
        int y = topPos + getSideButtonY(side);
        BasicFluidPipeBlockEntity.SideMode mode = menu.getSideMode(side);

        GuiUtils.drawOutline(graphics, x, y, BUTTON_SIZE, BUTTON_SIZE, 0xFF2F2F2F);
        if (mode == BasicFluidPipeBlockEntity.SideMode.PULL) {
            GuiUtils.fill(graphics, x + 1, y + 1, BUTTON_SIZE - 2, BUTTON_SIZE - 2, MODE_INPUT);
        } else if (mode == BasicFluidPipeBlockEntity.SideMode.PUSH) {
            GuiUtils.fill(graphics, x + 1, y + 1, BUTTON_SIZE - 2, BUTTON_SIZE - 2, MODE_OUTPUT);
        } else if (mode == BasicFluidPipeBlockEntity.SideMode.BOTH) {
            int inner = BUTTON_SIZE - 2;
            int leftHalf = inner / 2;
            GuiUtils.fill(graphics, x + 1, y + 1, leftHalf, inner, MODE_INPUT);
            GuiUtils.fill(graphics, x + 1 + leftHalf, y + 1, inner - leftHalf, inner, MODE_OUTPUT);
        } else {
            GuiUtils.fill(graphics, x + 1, y + 1, BUTTON_SIZE - 2, BUTTON_SIZE - 2, MODE_DISABLED);
        }

        ItemStack stack = getNeighborStack(side);
        if (!stack.isEmpty()) {
            // Draw the adjacent block icon directly inside the side config square.
            graphics.renderItem(stack, x + 1, y + 1);
        }
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

    // Side grid layout:
    // * U *
    // L F R
    // B D *
    // where '*' means intentionally empty.
    private static int getSideButtonX(Direction side) {
        int col = switch (side) {
            case UP, NORTH, DOWN -> 1;
            case WEST, SOUTH -> 0;
            case EAST -> 2;
        };
        return GRID_CENTER_X + (col - 1) * SIDE_STEP;
    }

    private static int getSideButtonY(Direction side) {
        int row = switch (side) {
            case UP -> 0;
            case NORTH, WEST, EAST -> 1;
            case SOUTH, DOWN -> 2;
        };
        return GRID_CENTER_Y + (row - 1) * SIDE_STEP;
    }

    private Direction getHoveredSide(int mouseX, int mouseY) {
        for (Direction side : Direction.values()) {
            int x = leftPos + getSideButtonX(side);
            int y = topPos + getSideButtonY(side);
            if (contains(mouseX, mouseY, x, y, BUTTON_SIZE, BUTTON_SIZE)) {
                return side;
            }
        }
        return null;
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

    private static String getModeTranslationKey(BasicFluidPipeBlockEntity.SideMode mode) {
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
}
