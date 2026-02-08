package com.example.commontransports.processing.entity;

import com.example.commontransports.block.entity.ModBlockEntities;
import com.example.commontransports.fluid.ModFluids;
import com.example.commontransports.processing.menu.CatalyticReformerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

/**
 * Catalytic Reformer:
 * Naphtha + FE -> Reformate
 */
public class CatalyticReformerBlockEntity extends AbstractPoweredFluidProcessorBlockEntity implements MenuProvider {

    public static final int INPUT_CAPACITY = 8000;
    public static final int OUTPUT_CAPACITY = 8000;
    public static final int PROCESS_AMOUNT = 1000;
    public static final int PROCESS_TIME = 100;
    public static final int PROCESS_RATE = 3;
    public static final int FE_PER_TICK = 30;
    public static final int ENERGY_CAPACITY = 60000;
    public static final int MAX_FE_INPUT = 3000;

    private final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> getInputAmount();
                case 1 -> getInputCapacity();
                case 2 -> getOutputAmount();
                case 3 -> getOutputCapacity();
                case 4 -> getProcessProgress();
                case 5 -> getEnergyStored();
                case 6 -> getEnergyCapacity();
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {}

        @Override
        public int getCount() {
            return 7;
        }
    };

    public CatalyticReformerBlockEntity(BlockPos pos, BlockState state) {
        super(
                ModBlockEntities.CATALYTIC_REFORMER.get(),
                pos,
                state,
                ModFluids.NAPHTHA_SOURCE,
                ModFluids.NAPHTHA_FLOWING,
                ModFluids.REFORMATE_SOURCE,
                ModFluids.REFORMATE_FLOWING,
                INPUT_CAPACITY,
                OUTPUT_CAPACITY,
                PROCESS_AMOUNT,
                PROCESS_TIME,
                PROCESS_RATE,
                FE_PER_TICK,
                ENERGY_CAPACITY,
                MAX_FE_INPUT
        );
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.common_transports.catalytic_reformer");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new CatalyticReformerMenu(containerId, playerInventory, this, data);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, CatalyticReformerBlockEntity blockEntity) {
        blockEntity.tickServer();
    }
}
