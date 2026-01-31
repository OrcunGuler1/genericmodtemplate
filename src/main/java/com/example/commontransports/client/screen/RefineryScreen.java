package com.example.commontransports.client.screen;

import com.example.commontransports.menu.RefineryMenu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class RefineryScreen extends AbstractContainerScreen<RefineryMenu> {
    
    // Use vanilla furnace texture as base
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("minecraft", "textures/gui/container/furnace.png");
    
    // Tank dimensions - smaller, cleaner look
    private static final int TANK_WIDTH = 16;
    private static final int TANK_HEIGHT = 50;
    private static final int INPUT_TANK_X = 44;
    private static final int INPUT_TANK_Y = 18;
    private static final int OUTPUT_TANK_X = 116;
    private static final int OUTPUT_TANK_Y = 18;
    
    // Progress arrow position (center)
    private static final int ARROW_X = 76;
    private static final int ARROW_Y = 34;
    private static final int ARROW_WIDTH = 22;
    private static final int ARROW_HEIGHT = 16;
    
    public RefineryScreen(RefineryMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }
    
    @Override
    protected void init() {
        super.init();
        // Center the title
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }
    
    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        // Draw background texture (vanilla furnace)
        graphics.blit(TEXTURE, leftPos, topPos, imageWidth, imageHeight, 0, 0, 256, 256);
        
        // Draw input tank (crude oil)
        drawTank(graphics, leftPos + INPUT_TANK_X, topPos + INPUT_TANK_Y, 
                 menu.getInputScaled(TANK_HEIGHT), 0xFF1a1a1a); // Dark crude oil
        
        // Draw output tank (petrol)
        drawTank(graphics, leftPos + OUTPUT_TANK_X, topPos + OUTPUT_TANK_Y, 
                 menu.getOutputScaled(TANK_HEIGHT), 0xFFD4A017); // Golden petrol
        
        // Draw progress arrow
        drawProgressArrow(graphics, leftPos + ARROW_X, topPos + ARROW_Y, 
                         menu.getProgressScaled(ARROW_WIDTH));
    }
    
    private void drawTank(GuiGraphics graphics, int x, int y, int fillHeight, int fluidColor) {
        // Tank background (dark)
        graphics.fill(x, y, x + TANK_WIDTH, y + TANK_HEIGHT, 0xFF373737);
        
        // Inner background (slightly lighter)
        graphics.fill(x + 1, y + 1, x + TANK_WIDTH - 1, y + TANK_HEIGHT - 1, 0xFF4a4a4a);
        
        // Fluid fill
        if (fillHeight > 0) {
            graphics.fill(x + 2, y + TANK_HEIGHT - fillHeight - 1, 
                         x + TANK_WIDTH - 2, y + TANK_HEIGHT - 1, fluidColor);
        }
        
        // 3D border effect
        // Top highlight
        graphics.fill(x, y, x + TANK_WIDTH, y + 1, 0xFF5a5a5a);
        // Left highlight
        graphics.fill(x, y, x + 1, y + TANK_HEIGHT, 0xFF5a5a5a);
        // Bottom shadow
        graphics.fill(x, y + TANK_HEIGHT - 1, x + TANK_WIDTH, y + TANK_HEIGHT, 0xFF2a2a2a);
        // Right shadow
        graphics.fill(x + TANK_WIDTH - 1, y, x + TANK_WIDTH, y + TANK_HEIGHT, 0xFF2a2a2a);
    }
    
    private void drawProgressArrow(GuiGraphics graphics, int x, int y, int progress) {
        // Arrow background
        graphics.fill(x, y, x + ARROW_WIDTH, y + ARROW_HEIGHT, 0xFFC6C6C6);
        
        // Arrow border (3D effect)
        graphics.fill(x, y, x + ARROW_WIDTH, y + 1, 0xFFFFFFFF); // Top
        graphics.fill(x, y, x + 1, y + ARROW_HEIGHT, 0xFFFFFFFF); // Left
        graphics.fill(x, y + ARROW_HEIGHT - 1, x + ARROW_WIDTH, y + ARROW_HEIGHT, 0xFF555555); // Bottom
        graphics.fill(x + ARROW_WIDTH - 1, y, x + ARROW_WIDTH, y + ARROW_HEIGHT, 0xFF555555); // Right
        
        // Progress fill (green)
        if (progress > 0) {
            graphics.fill(x + 1, y + 1, x + 1 + progress, y + ARROW_HEIGHT - 1, 0xFF00AA00);
        }
        
        // Draw arrow symbol in center
        int centerX = x + ARROW_WIDTH / 2;
        int centerY = y + ARROW_HEIGHT / 2;
        // Simple arrow: >
        graphics.fill(centerX - 3, centerY - 3, centerX - 1, centerY + 3, 0xFF373737);
        graphics.fill(centerX - 1, centerY - 2, centerX + 1, centerY + 2, 0xFF373737);
        graphics.fill(centerX + 1, centerY - 1, centerX + 3, centerY + 1, 0xFF373737);
    }
    
    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        renderTooltip(graphics, mouseX, mouseY);
        
        // Render custom tooltips for tanks using simple text rendering
        if (isHovering(INPUT_TANK_X, INPUT_TANK_Y, TANK_WIDTH, TANK_HEIGHT, mouseX, mouseY)) {
            String line1 = "Crude Oil";
            String line2 = menu.getInputAmount() + " / " + menu.getInputCapacity() + " mB";
            renderSimpleTooltip(graphics, mouseX, mouseY, line1, line2, null);
        }
        
        if (isHovering(OUTPUT_TANK_X, OUTPUT_TANK_Y, TANK_WIDTH, TANK_HEIGHT, mouseX, mouseY)) {
            String line1 = "Petrol";
            String line2 = menu.getOutputAmount() + " / " + menu.getOutputCapacity() + " mB";
            String line3 = menu.getOutputAmount() >= 1000 ? "Right-click with Gas Can to fill" : null;
            renderSimpleTooltip(graphics, mouseX, mouseY, line1, line2, line3);
        }
        
        // Progress tooltip
        if (isHovering(ARROW_X, ARROW_Y, ARROW_WIDTH, ARROW_HEIGHT, mouseX, mouseY)) {
            int progress = menu.getProgress();
            int maxProgress = 200;
            int percent = maxProgress > 0 ? (progress * 100 / maxProgress) : 0;
            String line1 = "Refining: " + percent + "%";
            renderSimpleTooltip(graphics, mouseX, mouseY, line1, null, null);
        }
    }
    
    private void renderSimpleTooltip(GuiGraphics graphics, int mouseX, int mouseY, String line1, String line2, String line3) {
        int lines = 1;
        int maxWidth = font.width(line1);
        if (line2 != null) {
            lines++;
            maxWidth = Math.max(maxWidth, font.width(line2));
        }
        if (line3 != null) {
            lines++;
            maxWidth = Math.max(maxWidth, font.width(line3));
        }
        
        int boxWidth = maxWidth + 8;
        int boxHeight = lines * 10 + 6;
        int x = mouseX + 8;
        int y = mouseY - boxHeight - 2;
        
        // Background
        graphics.fill(x, y, x + boxWidth, y + boxHeight, 0xF0100010);
        // Border
        graphics.fill(x, y, x + boxWidth, y + 1, 0xFF5000B0);
        graphics.fill(x, y + boxHeight - 1, x + boxWidth, y + boxHeight, 0xFF28007F);
        graphics.fill(x, y, x + 1, y + boxHeight, 0xFF5000B0);
        graphics.fill(x + boxWidth - 1, y, x + boxWidth, y + boxHeight, 0xFF28007F);
        
        // Text
        int textY = y + 4;
        graphics.drawString(font, line1, x + 4, textY, 0xFFFFFF, false);
        if (line2 != null) {
            graphics.drawString(font, line2, x + 4, textY + 10, 0xAAAAAA, false);
        }
        if (line3 != null) {
            graphics.drawString(font, line3, x + 4, textY + 20, 0x55FF55, false);
        }
    }
    
    private boolean isHovering(int x, int y, int width, int height, int mouseX, int mouseY) {
        int actualX = leftPos + x;
        int actualY = topPos + y;
        return mouseX >= actualX && mouseX < actualX + width && mouseY >= actualY && mouseY < actualY + height;
    }
}
