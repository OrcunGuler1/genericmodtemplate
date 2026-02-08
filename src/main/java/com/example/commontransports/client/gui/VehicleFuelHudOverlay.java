package com.example.commontransports.client.gui;

import com.example.commontransports.fluid.ModFluids;
import com.example.commontransports.vehicle.entity.AbstractStatsVehicleEntity;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

/**
 * HUD overlay that renders a fuel gauge in the bottom-left corner of the screen
 * when the player is riding a vehicle.
 */
public final class VehicleFuelHudOverlay {

    private VehicleFuelHudOverlay() {}

    // --- Layout constants ---
    private static final int MARGIN = 8;
    private static final int TANK_WIDTH = 16;
    private static final int TANK_HEIGHT = 52;
    private static final int TEXT_GAP = 4;

    // --- Colours (matching refinery tank style) ---
    private static final int BORDER_DARK  = 0xFF2a2a2a;
    private static final int BORDER_MID   = 0xFF3a3a3a;
    private static final int BORDER_LIGHT = 0xFF454545;
    private static final int HIGHLIGHT    = 0x22FFFFFF;
    private static final int TEXT_COLOR   = 0xFFFFFF;
    private static final int LABEL_COLOR  = 0xAAAAAA;

    /**
     * Called every frame by the GUI layer system. Renders nothing unless the
     * local player is riding an {@link AbstractStatsVehicleEntity}.
     */
    public static void render(GuiGraphics graphics, DeltaTracker deltaTracker) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        if (!(mc.player.getVehicle() instanceof AbstractStatsVehicleEntity vehicle)) return;
        // Don't draw over screens (inventory, chat, etc.)
        if (mc.screen != null) return;

        int fuel = vehicle.getFuel();
        int maxFuel = vehicle.getStats().maxFuel;
        int percent = maxFuel > 0 ? fuel * 100 / maxFuel : 0;
        int fillHeight = maxFuel > 0 ? fuel * (TANK_HEIGHT - 4) / maxFuel : 0;

        int screenHeight = graphics.guiHeight();

        // Position: bottom-left corner (leave room for amount text below tank)
        int x = MARGIN;
        int y = screenHeight - MARGIN - TANK_HEIGHT - mc.font.lineHeight - 3;

        // --- Tank frame (same style as refinery screen tanks) ---
        GuiUtils.fill(graphics, x - 1, y - 1, TANK_WIDTH + 2, TANK_HEIGHT + 2, BORDER_DARK);
        GuiUtils.fill(graphics, x, y, TANK_WIDTH, TANK_HEIGHT, BORDER_MID);
        GuiUtils.fill(graphics, x + 1, y + 1, TANK_WIDTH - 2, TANK_HEIGHT - 2, BORDER_LIGHT);

        // --- Fluid fill (petrol tint colour) ---
        if (fillHeight > 0) {
            int fluidColor = FluidTankRenderer.getTintColor(ModFluids.PETROL_SOURCE.get());
            int fillTop = y + TANK_HEIGHT - 2 - fillHeight;
            int fillX = x + 2;
            int fillW = TANK_WIDTH - 4;
            GuiUtils.fill(graphics, fillX, fillTop, fillW, fillHeight, fluidColor);
            // Slight highlight on top edge of fluid
            GuiUtils.fill(graphics, fillX, fillTop, fillW, 1, HIGHLIGHT);
        }

        // --- Text to the right of the tank ---
        int textX = x + TANK_WIDTH + TEXT_GAP;
        int lineH = mc.font.lineHeight;

        // "Fuel" label
        graphics.drawString(mc.font, Component.literal("Fuel"), textX, y, LABEL_COLOR, true);
        // Percentage
        graphics.drawString(mc.font, Component.literal(percent + "%"), textX, y + lineH + 1, TEXT_COLOR, true);

        // --- Numerical value below the tank ---
        String amountText = fuel + " / " + maxFuel + " mB";
        int amountY = y + TANK_HEIGHT + 3;
        graphics.drawString(mc.font, Component.literal(amountText), x, amountY, 0xFFD4A017, true);
    }
}
