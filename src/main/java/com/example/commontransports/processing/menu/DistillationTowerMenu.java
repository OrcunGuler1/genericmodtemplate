package com.example.commontransports.processing.menu;

import com.example.commontransports.api.menu.ProcessingMenu;
import com.example.commontransports.block.ModBlocks;
import com.example.commontransports.menu.ModMenuTypes;
import com.example.commontransports.processing.entity.DistillationTowerBlockEntity;

import net.minecraft.core.BlockPos;
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
import org.jspecify.annotations.Nullable;

public class DistillationTowerMenu extends AbstractContainerMenu implements ProcessingMenu {

    private record MenuContext(BlockPos blockPos, @Nullable BlockEntity blockEntity) {}

    private final @Nullable DistillationTowerBlockEntity blockEntity;
    private final BlockPos blockPos;
    private final ContainerLevelAccess levelAccess;
    private final ContainerData data;

    public DistillationTowerMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory, resolveContext(playerInventory, extraData), new SimpleContainerData(7));
    }

    public DistillationTowerMenu(int containerId, Inventory playerInventory, BlockEntity blockEntity, ContainerData data) {
        this(containerId, playerInventory, blockEntity, data, blockEntity.getBlockPos());
    }

    private DistillationTowerMenu(int containerId, Inventory playerInventory, MenuContext context, ContainerData data) {
        this(containerId, playerInventory, context.blockEntity(), data, context.blockPos());
    }

    private DistillationTowerMenu(int containerId, Inventory playerInventory, @Nullable BlockEntity blockEntity, ContainerData data,
                                  BlockPos blockPos) {
        super(ModMenuTypes.DISTILLATION_TOWER.get(), containerId);
        this.blockEntity = blockEntity instanceof DistillationTowerBlockEntity tower ? tower : null;
        this.blockPos = blockPos;
        this.levelAccess = this.blockEntity != null && this.blockEntity.getLevel() != null
                ? ContainerLevelAccess.create(this.blockEntity.getLevel(), blockPos)
                : ContainerLevelAccess.NULL;
        this.data = data;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
        addDataSlots(data);
    }

    private static MenuContext resolveContext(Inventory playerInventory, FriendlyByteBuf extraData) {
        BlockPos blockPos = extraData.readBlockPos();
        BlockEntity blockEntity = playerInventory.player.level().getBlockEntity(blockPos);
        return new MenuContext(blockPos, blockEntity);
    }

    @Override
    public boolean stillValid(Player player) {
        if (blockEntity == null) return false;
        return stillValid(levelAccess, player, ModBlocks.DISTILLATION_TOWER.get());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override public int getInputAmount() { return data.get(0); }
    @Override public int getInputCapacity() { return data.get(1); }
    @Override public int getOutputAmount() { return data.get(2); }
    @Override public int getOutputCapacity() { return data.get(3); }
    @Override public int getProgress() { return data.get(4); }
    @Override public int getProcessTime() { return DistillationTowerBlockEntity.PROCESS_TIME; }
    @Override public int getEnergyStored() { return data.get(5); }
    @Override public int getEnergyCapacity() { return data.get(6); }

    @Override
    public BlockPos getBlockPos() {
        return blockPos;
    }
}
