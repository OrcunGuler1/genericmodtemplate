package com.example.commontransports.menu;

import com.example.commontransports.block.ModBlocks;
import com.example.commontransports.block.entity.RefineryBlockEntity;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class RefineryMenu extends AbstractContainerMenu {
    
    private final RefineryBlockEntity blockEntity;
    private final ContainerLevelAccess levelAccess;
    private final ContainerData data;
    
    // Client constructor
    public RefineryMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory, playerInventory.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(5));
    }
    
    // Server constructor
    public RefineryMenu(int containerId, Inventory playerInventory, BlockEntity blockEntity, ContainerData data) {
        super(ModMenuTypes.REFINERY.get(), containerId);
        
        if (blockEntity instanceof RefineryBlockEntity refinery) {
            this.blockEntity = refinery;
        } else {
            throw new IllegalStateException("Invalid block entity for RefineryMenu");
        }
        
        this.levelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
        this.data = data;
        
        // Add player inventory slots (3 rows of 9)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        
        // Add player hotbar slots
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
        
        addDataSlots(data);
    }
    
    @Override
    public boolean stillValid(Player player) {
        return stillValid(levelAccess, player, ModBlocks.REFINERY.get());
    }
    
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        // No item slots in refinery, just return empty
        return ItemStack.EMPTY;
    }
    
    // Getters for screen to access fluid data
    public int getInputAmount() {
        return data.get(0);
    }
    
    public int getInputCapacity() {
        return data.get(1);
    }
    
    public int getOutputAmount() {
        return data.get(2);
    }
    
    public int getOutputCapacity() {
        return data.get(3);
    }
    
    public int getProgress() {
        return data.get(4);
    }
    
    public int getProgressScaled(int pixels) {
        int progress = getProgress();
        int maxProgress = RefineryBlockEntity.PROCESS_TIME;
        return maxProgress != 0 && progress != 0 ? progress * pixels / maxProgress : 0;
    }
    
    public int getInputScaled(int pixels) {
        int amount = getInputAmount();
        int capacity = getInputCapacity();
        return capacity != 0 && amount != 0 ? amount * pixels / capacity : 0;
    }
    
    public int getOutputScaled(int pixels) {
        int amount = getOutputAmount();
        int capacity = getOutputCapacity();
        return capacity != 0 && amount != 0 ? amount * pixels / capacity : 0;
    }
    
    public RefineryBlockEntity getBlockEntity() {
        return blockEntity;
    }
}
