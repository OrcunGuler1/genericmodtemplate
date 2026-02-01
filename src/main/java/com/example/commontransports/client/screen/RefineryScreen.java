package com.example.commontransports.client.screen;

import com.example.commontransports.GenericMod;
import com.example.commontransports.fluid.ModFluids;
import com.example.commontransports.refinery.menu.RefineryMenu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.client.gui.screens.inventory.tooltip.MenuTooltipPositioner;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class RefineryScreen extends AbstractProcessingScreen<RefineryMenu> {

    private static final Identifier BACKGROUND_TEXTURE = Identifier.fromNamespaceAndPath(GenericMod.MODID, "textures/gui/base.png");
    private static final Identifier TOOLTIP_BACKGROUND = Identifier.fromNamespaceAndPath("minecraft", "textures/gui/tooltip.png");

    private static final int INPUT_TANK_X = 44;
    private static final int INPUT_TANK_Y = 18;
    private static final int OUTPUT_TANK_X = 116;
    private static final int OUTPUT_TANK_Y = 18;
    private static final int ARROW_X = 76;
    private static final int ARROW_Y = 35;
    private static final int ARROW_WIDTH = 24;
    private static final int ARROW_HEIGHT = 17;
    private static final int LABEL_Y_OFFSET = 10;
    private static final int COLOR_AMOUNT = 0xD4A017;
    private static final int COLOR_HINT = 0x555555;

    public RefineryScreen(RefineryMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, 176, 166);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        graphics.blit(BACKGROUND_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);

        int labelColor = 0x404040;
        graphics.drawString(font, Component.translatable("fluid_type.common_transports.crude_oil").getString(),
                leftPos + INPUT_TANK_X, topPos + INPUT_TANK_Y - LABEL_Y_OFFSET, labelColor, false);
        graphics.drawString(font, Component.translatable("fluid_type.common_transports.petrol").getString(),
                leftPos + OUTPUT_TANK_X, topPos + OUTPUT_TANK_Y - LABEL_Y_OFFSET, labelColor, false);

        drawTank(graphics, leftPos + INPUT_TANK_X, topPos + INPUT_TANK_Y, TANK_WIDTH, TANK_HEIGHT,
                menu.getInputScaled(TANK_HEIGHT), ModFluids.CRUDE_OIL_SOURCE.get());
        drawTank(graphics, leftPos + OUTPUT_TANK_X, topPos + OUTPUT_TANK_Y, TANK_WIDTH, TANK_HEIGHT,
                menu.getOutputScaled(TANK_HEIGHT), ModFluids.PETROL_SOURCE.get());

        int progressPx = menu.getProgressScaled(ARROW_WIDTH - 2);
        drawProgressBar(graphics, leftPos + ARROW_X, topPos + ARROW_Y, ARROW_WIDTH, ARROW_HEIGHT, progressPx, 0xFF00AA00);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        updateScreenPosition();
        super.render(graphics, mouseX, mouseY, partialTick);

        List<Component> tooltipLines = null;
        if (isHovering(INPUT_TANK_X, INPUT_TANK_Y, TANK_WIDTH, TANK_HEIGHT, mouseX, mouseY)) {
            tooltipLines = List.of(
                    Component.translatable("fluid_type.common_transports.crude_oil"),
                    Component.literal(menu.getInputAmount() + " / " + menu.getInputCapacity() + " mB")
                            .withStyle(style -> style.withColor(COLOR_AMOUNT)));
        } else if (isHovering(OUTPUT_TANK_X, OUTPUT_TANK_Y, TANK_WIDTH, TANK_HEIGHT, mouseX, mouseY)) {
            var list = new ArrayList<Component>();
            list.add(Component.translatable("fluid_type.common_transports.petrol"));
            list.add(Component.literal(menu.getOutputAmount() + " / " + menu.getOutputCapacity() + " mB")
                    .withStyle(style -> style.withColor(COLOR_AMOUNT)));
            if (menu.getOutputAmount() >= 1000) {
                list.add(Component.literal("Right-click with Gas Can to fill")
                        .withStyle(style -> style.withColor(COLOR_HINT)));
            }
            tooltipLines = list;
        } else if (isHovering(ARROW_X, ARROW_Y, ARROW_WIDTH, ARROW_HEIGHT, mouseX, mouseY)) {
            int maxProgress = menu.getProcessTime();
            int percent = maxProgress > 0 ? menu.getProgress() * 100 / maxProgress : 0;
            tooltipLines = List.of(
                    Component.literal("Refining: " + percent + "%")
                            .withStyle(style -> style.withColor(COLOR_AMOUNT)));
        }

        if (tooltipLines != null && !tooltipLines.isEmpty()) {
            List<ClientTooltipComponent> components = new ArrayList<>();
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
}
