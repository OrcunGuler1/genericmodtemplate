package com.example.commontransports.item;

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
public class GasCanItem extends Item {
    public static final int CAPACITY = 16000; // 1 bucket worth (1000 mB)
    
    public GasCanItem(Item.Properties properties) {
        super(properties.stacksTo(1));
    }
    
    public static int getFluidAmount(ItemStack stack) {
        if (stack.has(DataComponents.CUSTOM_DATA)) {
            CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
            if (customData != null) {
                return customData.copyTag().getIntOr("FluidAmount", 0);
            }
        }
        return 0;
    }
    
    public static void setFluidAmount(ItemStack stack, int amount) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("FluidAmount", Math.max(0, Math.min(amount, CAPACITY)));
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }
    
    public static boolean hasFuel(ItemStack stack) {
        return getFluidAmount(stack) > 0;
    }
    
    public static int consumeFuel(ItemStack stack, int amount) {
        int current = getFluidAmount(stack);
        int consumed = Math.min(current, amount);
        setFluidAmount(stack, current - consumed);
        return consumed;
    }
    
    @Override
    public boolean isBarVisible(ItemStack stack) {
        // Show bar when partially filled
        int amount = getFluidAmount(stack);
        return amount > 0 && amount < CAPACITY;
    }
    
    @Override
    public int getBarWidth(ItemStack stack) {
        // Full bar is 13 pixels
        int amount = getFluidAmount(stack);
        return Math.round(amount * 13.0f / CAPACITY);
    }
    
    @Override
    public int getBarColor(ItemStack stack) {
        // Golden/amber color for petrol
        return 0xD4A017;
    }
    
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        super.appendHoverText(stack, context, display, tooltipAdder, flag);
        
        int amount = getFluidAmount(stack);
        if (amount > 0) {
            tooltipAdder.accept(Component.literal("Petrol: " + amount + " / " + CAPACITY + " mB")
                    .withStyle(style -> style.withColor(0xD4A017)));
        } else {
            tooltipAdder.accept(Component.literal("Empty")
                    .withStyle(style -> style.withColor(0x888888)));
            tooltipAdder.accept(Component.literal("Right-click Refinery to fill")
                    .withStyle(style -> style.withColor(0x555555)));
        }
    }
}
