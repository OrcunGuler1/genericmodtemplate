package com.example.commontransports.pipe.menu;

import com.example.commontransports.block.ModBlocks;
import com.example.commontransports.menu.ModMenuTypes;
import com.example.commontransports.pipe.entity.BasicFluidPipeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jspecify.annotations.Nullable;

public class BasicFluidPipeMenu extends AbstractContainerMenu {

    public static final int DATA_COUNT = 9;

    private record MenuContext(BlockPos blockPos, @Nullable BlockEntity blockEntity) {}

    private final @Nullable BasicFluidPipeBlockEntity blockEntity;
    private final BlockPos blockPos;
    private final ContainerLevelAccess levelAccess;
    private final ContainerData data;

    public BasicFluidPipeMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory, resolveContext(playerInventory, extraData), new SimpleContainerData(DATA_COUNT));
    }

    public BasicFluidPipeMenu(int containerId, Inventory playerInventory, BlockEntity blockEntity, ContainerData data) {
        this(containerId, playerInventory, blockEntity, data, blockEntity.getBlockPos());
    }

    private BasicFluidPipeMenu(int containerId, Inventory playerInventory, MenuContext context, ContainerData data) {
        this(containerId, playerInventory, context.blockEntity(), data, context.blockPos());
    }

    private BasicFluidPipeMenu(int containerId, Inventory playerInventory, @Nullable BlockEntity blockEntity, ContainerData data,
            BlockPos blockPos) {
        super(ModMenuTypes.BASIC_FLUID_PIPE.get(), containerId);
        this.blockEntity = blockEntity instanceof BasicFluidPipeBlockEntity pipe ? pipe : null;
        this.blockPos = blockPos;
        this.levelAccess = this.blockEntity != null && this.blockEntity.getLevel() != null
                ? ContainerLevelAccess.create(this.blockEntity.getLevel(), blockPos)
                : ContainerLevelAccess.NULL;
        this.data = data;

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
        return stillValid(levelAccess, player, ModBlocks.BASIC_FLUID_PIPE.get());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public int getStoredAmount() {
        return data.get(0);
    }

    public int getCapacity() {
        return data.get(1);
    }

    public int getFluidId() {
        return data.get(2);
    }

    public BasicFluidPipeBlockEntity.SideMode getSideMode(Direction side) {
        return BasicFluidPipeBlockEntity.SideMode.fromId(data.get(3 + side.ordinal()));
    }
}
