package com.example.commontransports.client.screen;

import com.example.commontransports.client.gui.FluidTankRenderer;
import com.example.commontransports.client.gui.GuiUtils;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.material.Fluid;

import net.minecraft.network.chat.Component;

import com.example.commontransports.api.menu.ProcessingMenu;

/**
 * Screen for container menus that have input/output fluid tanks and processing progress.
 * Provides shared tank and progress bar drawing. Subclasses provide layout and labels.
 */
public abstract class AbstractProcessingScreen<M extends AbstractContainerMenu & ProcessingMenu>
        extends AbstractContainerScreen<M> {

    protected static final int TANK_WIDTH = 18;
    protected static final int TANK_HEIGHT = 52;

    public AbstractProcessingScreen(M menu, Inventory playerInventory, Component title, int imageWidth, int imageHeight) {
        super(menu, playerInventory, title);
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    @Override
    protected void init() {
        super.init();  // vanilla centres the screen (sets leftPos/topPos)
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    /**
     * Draw a fluid tank at (x, y) with the given fill height and fluid.
     */
    protected void drawTank(GuiGraphics graphics, int x, int y, int tankWidth, int tankHeight, int fillHeight, Fluid fluid) {
        GuiUtils.fill(graphics, x - 1, y - 1, tankWidth + 2, tankHeight + 2, 0xFF2a2a2a);
        GuiUtils.fill(graphics, x, y, tankWidth, tankHeight, 0xFF3a3a3a);
        GuiUtils.fill(graphics, x + 1, y + 1, tankWidth - 2, tankHeight - 2, 0xFF454545);

        if (fillHeight > 0) {
            int fillTop = y + tankHeight - fillHeight;
            int innerW = tankWidth - 4;
            int innerH = fillHeight - 2;
            int fillX = x + 2;
            var spriteOpt = FluidTankRenderer.getStillSprite(fluid);
            int tint = FluidTankRenderer.getTintColor(fluid);
            if (spriteOpt.isPresent()) {
                TextureAtlasSprite sprite = spriteOpt.get();
                int sw = Math.max(1, sprite.contents().width());
                int sh = Math.max(1, sprite.contents().height());
                GuiUtils.drawTiledSprite(graphics, fillX, fillTop, innerW, innerH, sprite, sw, sh, GuiUtils.TilingDirection.DOWN_RIGHT);
            } else {
                GuiUtils.fill(graphics, fillX, fillTop, innerW, innerH, tint);
            }
            GuiUtils.fill(graphics, fillX, fillTop, innerW, 1, 0x22FFFFFF);
        }
    }

    /**
     * Draw a progress bar: outline, dark background, then filled portion in fillColor.
     */
    protected void drawProgressBar(GuiGraphics graphics, int x, int y, int width, int height, int progressPx, int fillColor) {
        GuiUtils.drawOutline(graphics, x, y, width, height, 0xFF4a4a4a);
        GuiUtils.fill(graphics, x + 1, y + 1, width - 2, height - 2, 0xFF2a2a2a);
        if (progressPx > 0) {
            GuiUtils.fill(graphics, x + 1, y + 1, progressPx, height - 2, fillColor);
        }
    }

    /**
     * Draw a vertical meter filled from bottom to top.
     */
    protected void drawVerticalBar(GuiGraphics graphics, int x, int y, int width, int height, int fillPx, int fillColor) {
        GuiUtils.drawOutline(graphics, x, y, width, height, 0xFF4a4a4a);
        GuiUtils.fill(graphics, x + 1, y + 1, width - 2, height - 2, 0xFF2a2a2a);
        if (fillPx > 0) {
            int clampedFill = Math.min(fillPx, height - 2);
            int fy = y + height - 1 - clampedFill;
            GuiUtils.fill(graphics, x + 1, fy, width - 2, clampedFill, fillColor);
        }
    }

    protected boolean isHovering(int x, int y, int width, int height, int mouseX, int mouseY) {
        int actualX = leftPos + x;
        int actualY = topPos + y;
        return mouseX >= actualX && mouseX < actualX + width && mouseY >= actualY && mouseY < actualY + height;
    }
}
