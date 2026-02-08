package com.example.commontransports.refinery.menu;

import com.example.commontransports.api.menu.ProcessingMenu;
import com.example.commontransports.block.ModBlocks;
import com.example.commontransports.menu.ModMenuTypes;
import com.example.commontransports.refinery.entity.RefineryBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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

public class RefineryMenu extends AbstractContainerMenu implements ProcessingMenu {

    private record MenuContext(BlockPos blockPos, @Nullable BlockEntity blockEntity) {}

    /** Client action: flush input tank. */
    public static final int ACTION_FLUSH_INPUT = 0;
    /** Client action: flush output (petrol) tank. */
    public static final int ACTION_FLUSH_OUTPUT = 1;

    private final @Nullable RefineryBlockEntity blockEntity;
    private final BlockPos blockPos;
    private final ContainerLevelAccess levelAccess;
    private final ContainerData data;

    public RefineryMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory, resolveContext(playerInventory, extraData), new SimpleContainerData(19));
    }

    public RefineryMenu(int containerId, Inventory playerInventory, BlockEntity blockEntity, ContainerData data) {
        this(containerId, playerInventory, blockEntity, data, blockEntity.getBlockPos());
    }

    private RefineryMenu(int containerId, Inventory playerInventory, MenuContext context, ContainerData data) {
        this(containerId, playerInventory, context.blockEntity(), data, context.blockPos());
    }

    private RefineryMenu(int containerId, Inventory playerInventory, @Nullable BlockEntity blockEntity, ContainerData data,
            BlockPos blockPos) {
        super(ModMenuTypes.REFINERY.get(), containerId);
        this.blockEntity = blockEntity instanceof RefineryBlockEntity refinery ? refinery : null;
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
        return stillValid(levelAccess, player, ModBlocks.REFINERY.get());
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
    @Override public int getProcessTime() { return RefineryBlockEntity.PROCESS_TIME; }
    @Override public int getEnergyStored() { return data.get(6); }
    @Override public int getEnergyCapacity() { return data.get(7); }
    @Override public int getSpeedUpgrades() { return data.get(8); }
    @Override public int getEfficiencyUpgrades() { return data.get(9); }
    @Override public int getMaxUpgradesPerType() { return data.get(10); }
    @Override public int getEffectiveProcessRate() { return data.get(11); }
    @Override public int getEffectiveFePerTick() { return data.get(12); }
    @Override public int getSideModeId(Direction side) { return data.get(13 + side.ordinal()); }

    @Override
    public BlockPos getBlockPos() { return blockPos; }
    public @Nullable RefineryBlockEntity getBlockEntity() { return blockEntity; }
}
