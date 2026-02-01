package com.example.commontransports.client.screen;

import com.example.commontransports.GenericMod;
import com.example.commontransports.client.gui.FluidTankRenderer;
import com.example.commontransports.client.gui.GuiUtils;
import com.example.commontransports.fluid.ModFluids;
import com.example.commontransports.menu.RefineryMenu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.client.gui.screens.inventory.tooltip.MenuTooltipPositioner;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluid;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;

public class RefineryScreen extends AbstractContainerScreen<RefineryMenu> {

    /** Refinery panel background (assets/common_transports/textures/gui/base.png). */
    private static final Identifier BACKGROUND_TEXTURE = Identifier.fromNamespaceAndPath(GenericMod.MODID, "textures/gui/base.png");
    /** Same tooltip texture as item tooltips (gas can, etc.). */
    private static final Identifier TOOLTIP_BACKGROUND = Identifier.fromNamespaceAndPath("minecraft", "textures/gui/tooltip.png");

    // Tank layout - aligned with furnace-style slots
    private static final int TANK_WIDTH = 18;
    private static final int TANK_HEIGHT = 52;
    private static final int INPUT_TANK_X = 44;
    private static final int INPUT_TANK_Y = 18;
    private static final int OUTPUT_TANK_X = 116;
    private static final int OUTPUT_TANK_Y = 18;

    // Progress arrow - use furnace arrow area (56, 36 in 176x166 space; texture has it at 176, 14, 24x17)
    private static final int ARROW_X = 76;
    private static final int ARROW_Y = 35;
    private static final int ARROW_WIDTH = 24;
    private static final int ARROW_HEIGHT = 17;

    // Label Y above tanks
    private static final int LABEL_Y_OFFSET = 10;

    public RefineryScreen(RefineryMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();
        updateScreenPosition();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    protected void repositionElements() {
        updateScreenPosition();
        super.repositionElements();
    }

    private void updateScreenPosition() {
        if (this.width > 0 && this.imageWidth > 0) {
            this.leftPos = (this.width - this.imageWidth) / 2;
        }
        if (this.height > 0 && this.imageHeight > 0) {
            this.topPos = (this.height - this.imageHeight) / 2;
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        // Full panel background (base.png)
        graphics.blit(BACKGROUND_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);

        // Labels above tanks (so purpose is clear at a glance)
        int labelColor = 0x404040;
        graphics.drawString(font, Component.translatable("fluid_type.common_transports.crude_oil").getString(),
                leftPos + INPUT_TANK_X, topPos + INPUT_TANK_Y - LABEL_Y_OFFSET, labelColor, false);
        graphics.drawString(font, Component.translatable("fluid_type.common_transports.petrol").getString(),
                leftPos + OUTPUT_TANK_X, topPos + OUTPUT_TANK_Y - LABEL_Y_OFFSET, labelColor, false);

        // Tanks with clear borders and fluid (Mekanism-style tiled sprite + tint)
        drawTank(graphics, leftPos + INPUT_TANK_X, topPos + INPUT_TANK_Y,
                menu.getInputScaled(TANK_HEIGHT), ModFluids.CRUDE_OIL_SOURCE.get());
        drawTank(graphics, leftPos + OUTPUT_TANK_X, topPos + OUTPUT_TANK_Y,
                menu.getOutputScaled(TANK_HEIGHT), ModFluids.PETROL_SOURCE.get());

        // Progress arrow: bordered rect + green fill (no furnace texture)
        int ax = leftPos + ARROW_X;
        int ay = topPos + ARROW_Y;
        GuiUtils.drawOutline(graphics, ax, ay, ARROW_WIDTH, ARROW_HEIGHT, 0xFF4a4a4a);
        GuiUtils.fill(graphics, ax + 1, ay + 1, ARROW_WIDTH - 2, ARROW_HEIGHT - 2, 0xFF2a2a2a);
        int progressPx = menu.getProgressScaled(ARROW_WIDTH - 2);
        if (progressPx > 0) {
            GuiUtils.fill(graphics, ax + 1, ay + 1, progressPx, ARROW_HEIGHT - 2, 0xFF00AA00);
        }
    }

    private void drawTank(GuiGraphics graphics, int x, int y, int fillHeight, Fluid fluid) {
        // Outer border (dark)
        GuiUtils.fill(graphics, x - 1, y - 1, TANK_WIDTH + 2, TANK_HEIGHT + 2, 0xFF2a2a2a);
        // Inner background
        GuiUtils.fill(graphics, x, y, TANK_WIDTH, TANK_HEIGHT, 0xFF3a3a3a);
        // Inner lighter edge
        GuiUtils.fill(graphics, x + 1, y + 1, TANK_WIDTH - 2, TANK_HEIGHT - 2, 0xFF454545);

        if (fillHeight > 0) {
            int fillTop = y + TANK_HEIGHT - fillHeight;
            int innerW = TANK_WIDTH - 4;
            int innerH = fillHeight - 2;
            int fillX = x + 2;
            var spriteOpt = FluidTankRenderer.getStillSprite(fluid);
            int tint = FluidTankRenderer.getTintColor(fluid);
            if (spriteOpt.isPresent()) {
                TextureAtlasSprite sprite = spriteOpt.get();
                int sw = sprite.contents().width();
                int sh = sprite.contents().height();
                if (sw <= 0) sw = 16;
                if (sh <= 0) sh = 16;
                GuiUtils.drawTiledSprite(graphics, fillX, fillTop, innerW, innerH, sprite, sw, sh, GuiUtils.TilingDirection.DOWN_RIGHT);
            } else {
                GuiUtils.fill(graphics, fillX, fillTop, innerW, innerH, tint);
            }
            GuiUtils.fill(graphics, fillX, fillTop, innerW, 1, 0x22FFFFFF);
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        updateScreenPosition();
        super.render(graphics, mouseX, mouseY, partialTick);

        // Tooltip styling matches GasCanItem: amber for amounts, gray for hints
        final int COLOR_AMOUNT = 0xD4A017;
        final int COLOR_HINT = 0x555555;

        java.util.List<Component> tooltipLines = null;
        if (isHovering(INPUT_TANK_X, INPUT_TANK_Y, TANK_WIDTH, TANK_HEIGHT, mouseX, mouseY)) {
            tooltipLines = java.util.List.of(
                    Component.translatable("fluid_type.common_transports.crude_oil"),
                    Component.literal(menu.getInputAmount() + " / " + menu.getInputCapacity() + " mB")
                            .withStyle(style -> style.withColor(COLOR_AMOUNT)));
        } else if (isHovering(OUTPUT_TANK_X, OUTPUT_TANK_Y, TANK_WIDTH, TANK_HEIGHT, mouseX, mouseY)) {
            var list = new java.util.ArrayList<Component>();
            list.add(Component.translatable("fluid_type.common_transports.petrol"));
            list.add(Component.literal(menu.getOutputAmount() + " / " + menu.getOutputCapacity() + " mB")
                    .withStyle(style -> style.withColor(COLOR_AMOUNT)));
            if (menu.getOutputAmount() >= 1000) {
                list.add(Component.literal("Right-click with Gas Can to fill")
                        .withStyle(style -> style.withColor(COLOR_HINT)));
            }
            tooltipLines = list;
        } else if (isHovering(ARROW_X, ARROW_Y, ARROW_WIDTH, ARROW_HEIGHT, mouseX, mouseY)) {
            int progress = menu.getProgress();
            int maxProgress = 200;
            int percent = maxProgress > 0 ? (progress * 100 / maxProgress) : 0;
            tooltipLines = java.util.List.of(
                    Component.literal("Refining: " + percent + "%")
                            .withStyle(style -> style.withColor(COLOR_AMOUNT)));
        }

        if (tooltipLines != null && !tooltipLines.isEmpty()) {
            // Same path as item tooltips (e.g. gas can): Component -> ClientTooltipComponent, MenuTooltipPositioner, vanilla texture
            java.util.List<ClientTooltipComponent> components = new java.util.ArrayList<>();
            for (Component line : tooltipLines) {
                components.add(ClientTooltipComponent.create(line.getVisualOrderText()));
            }
            ScreenRectangle menuRect = new ScreenRectangle(leftPos, topPos, imageWidth, imageHeight);
            ClientTooltipPositioner positioner = new MenuTooltipPositioner(menuRect);
            graphics.renderTooltip(font, components, mouseX, mouseY, positioner, TOOLTIP_BACKGROUND);
        } else {
            renderTooltip(graphics, mouseX, mouseY);
        }
    }

    private boolean isHovering(int x, int y, int width, int height, int mouseX, int mouseY) {
        int actualX = leftPos + x;
        int actualY = topPos + y;
        return mouseX >= actualX && mouseX < actualX + width && mouseY >= actualY && mouseY < actualY + height;
    }
}
