package com.example.commontransports.fluid;

import com.example.commontransports.api.item.FluidContainerItem;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

/**
 * Gas Can - starts empty and can only be filled from a Refinery machine.
 * Right-click on a Refinery with at least 1 bucket of petrol to fill.
 * Use on motorcycle to fuel it.
 */
public class GasCanItem extends Item implements FluidContainerItem {

    public static final int CAPACITY = 16000;

    public GasCanItem(Item.Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public int getFluidAmount(ItemStack stack) {
        return getFluidAmountStatic(stack);
    }

    @Override
    public void setFluidAmount(ItemStack stack, int amount) {
        setFluidAmountStatic(stack, amount);
    }

    @Override
    public int getCapacity() {
        return CAPACITY;
    }

    private static int getFluidAmountStatic(ItemStack stack) {
        if (stack.has(DataComponents.CUSTOM_DATA)) {
            CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
            if (customData != null) {
                return customData.copyTag().getIntOr("FluidAmount", 0);
            }
        }
        return 0;
    }

    private static void setFluidAmountStatic(ItemStack stack, int amount) {
        CompoundTag tag = new CompoundTag();
        if (stack.has(DataComponents.CUSTOM_DATA)) {
            CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
            if (customData != null) {
                tag = customData.copyTag();
            }
        }
        tag.putInt("FluidAmount", Math.max(0, Math.min(amount, CAPACITY)));
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        int amount = getFluidAmount(stack);
        return amount > 0 && amount < CAPACITY;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(getFluidAmount(stack) * 13.0f / CAPACITY);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return 0xD4A017;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        int amount = getFluidAmount(stack);
        if (amount > 0) {
            tooltipAdder.accept(Component.literal("Petrol: " + amount + " / " + CAPACITY + " mB").withStyle(style -> style.withColor(0xD4A017)));
        } else {
            tooltipAdder.accept(Component.literal("Empty").withStyle(style -> style.withColor(0x888888)));
            tooltipAdder.accept(Component.literal("Right-click Refinery to fill").withStyle(style -> style.withColor(0x555555)));
        }
    }
}
