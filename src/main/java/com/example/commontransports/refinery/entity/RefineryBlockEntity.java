package com.example.commontransports.refinery.entity;

import com.example.commontransports.api.block.HasInputFluidTank;
import com.example.commontransports.api.block.HasOutputFluidTank;
import com.example.commontransports.api.block.HasProcessingProgress;
import com.example.commontransports.block.entity.ModBlockEntities;
import com.example.commontransports.fluid.ModFluids;
import com.example.commontransports.processing.entity.AbstractPoweredFluidProcessorBlockEntity;
import com.example.commontransports.refinery.menu.RefineryMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

/**
 * Refinery Block Entity - final two-stage reformate -> petrol processing.
 * Shares fluid/energy capability handling with other FE processors.
 */
public class RefineryBlockEntity extends AbstractPoweredFluidProcessorBlockEntity implements MenuProvider,
        HasInputFluidTank, HasOutputFluidTank, HasProcessingProgress {

    public static final int INPUT_CAPACITY = 8000;
    public static final int OUTPUT_CAPACITY = 8000;
    public static final int PROCESS_AMOUNT = 1000;
    public static final int DISTILLATION_TIME = 120;
    public static final int REFORMING_TIME = 100;
    public static final int PROCESS_TIME = DISTILLATION_TIME + REFORMING_TIME;
    public static final int PROCESS_RATE = 3;
    public static final int FE_PER_TICK = 16;
    public static final int ENERGY_CAPACITY = 50000;
    public static final int MAX_FE_INPUT = 2500;

    private RefiningStage refiningStage = RefiningStage.IDLE;
    private int stageProgress = 0;
    private int batchAmount = 0;

    private final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> getInputAmount();
                case 1 -> getInputCapacity();
                case 2 -> getOutputAmount();
                case 3 -> getOutputCapacity();
                case 4 -> getProcessProgress();
                case 5 -> refiningStage.id;
                case 6 -> getEnergyStored();
                case 7 -> getEnergyCapacity();
                case 8 -> getSpeedUpgrades();
                case 9 -> getEfficiencyUpgrades();
                case 10 -> getMaxUpgradesPerType();
                case 11 -> getEffectiveProcessRate();
                case 12 -> getEffectiveFePerTick();
                case 13 -> getSideMode(net.minecraft.core.Direction.DOWN).id();
                case 14 -> getSideMode(net.minecraft.core.Direction.UP).id();
                case 15 -> getSideMode(net.minecraft.core.Direction.NORTH).id();
                case 16 -> getSideMode(net.minecraft.core.Direction.SOUTH).id();
                case 17 -> getSideMode(net.minecraft.core.Direction.WEST).id();
                case 18 -> getSideMode(net.minecraft.core.Direction.EAST).id();
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {}

        @Override
        public int getCount() {
            return 19;
        }
    };

    public RefineryBlockEntity(BlockPos pos, BlockState state) {
        super(
                ModBlockEntities.REFINERY.get(),
                pos,
                state,
                ModFluids.REFORMATE_SOURCE,
                ModFluids.REFORMATE_FLOWING,
                ModFluids.PETROL_SOURCE,
                ModFluids.PETROL_FLOWING,
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
        return Component.translatable("container.common_transports.refinery");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new RefineryMenu(containerId, playerInventory, this, data);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, RefineryBlockEntity refinery) {
        refinery.tickServer();
    }

    @Override
    public void tickServer() {
        if (level == null || level.isClientSide()) return;
        switch (refiningStage) {
            case IDLE -> tryStartDistillation();
            case DISTILLATION -> runDistillation();
            case REFORMING -> runReforming();
        }
    }

    private void tryStartDistillation() {
        int outputSpace = getOutputCapacity() - getOutputAmount();
        int availableInput = getInputAmount();
        if (availableInput <= 0 || outputSpace <= 0) return;

        batchAmount = Math.min(PROCESS_AMOUNT, Math.min(availableInput, outputSpace));
        refiningStage = RefiningStage.DISTILLATION;
        stageProgress = 0;
        setChanged();
    }

    private void runDistillation() {
        if (batchAmount <= 0) {
            refiningStage = RefiningStage.IDLE;
            stageProgress = 0;
            return;
        }
        if (!consumeEnergy(getEffectiveFePerTick())) return;
        int step = Math.min(getEffectiveProcessRate(), batchAmount - stageProgress);
        int consumed = drainInput(step, true);
        if (consumed <= 0) return;

        stageProgress += consumed;
        if (stageProgress >= batchAmount || getInputAmount() <= 0) {
            // If input ran out early, continue with whatever was distilled so far.
            batchAmount = stageProgress;
            refiningStage = RefiningStage.REFORMING;
            stageProgress = 0;
        }
        setChanged();
    }

    private void runReforming() {
        if (batchAmount <= 0) {
            refiningStage = RefiningStage.IDLE;
            stageProgress = 0;
            return;
        }
        if (!consumeEnergy(getEffectiveFePerTick())) return;
        int step = Math.min(getEffectiveProcessRate(), batchAmount - stageProgress);
        int produced = addOutputAmount(step);
        stageProgress += produced;
        if (stageProgress >= batchAmount) {
            refiningStage = RefiningStage.IDLE;
            stageProgress = 0;
            batchAmount = 0;
        }
        setChanged();
    }

    @Override
    public int getProcessProgress() {
        if (batchAmount <= 0) return 0;
        return switch (refiningStage) {
            case IDLE -> 0;
            case DISTILLATION -> Math.min(DISTILLATION_TIME, (int) ((long) stageProgress * DISTILLATION_TIME / batchAmount));
            case REFORMING -> Math.min(PROCESS_TIME,
                    DISTILLATION_TIME + (int) ((long) stageProgress * REFORMING_TIME / batchAmount));
        };
    }

    private enum RefiningStage {
        IDLE(0),
        DISTILLATION(1),
        REFORMING(2);

        private final int id;

        RefiningStage(int id) {
            this.id = id;
        }

        private static RefiningStage fromId(int id) {
            for (RefiningStage stage : values()) {
                if (stage.id == id) return stage;
            }
            return IDLE;
        }
    }

    /** Clears the input tank (used by GUI "flush" action). */
    public void clearInputFluid() {
        drainInput(getInputAmount(), true);
    }

    /** Clears the output tank (used by GUI "flush" action). */
    public void clearOutputFluid() {
        drainOutput(getOutputAmount(), true);
    }

    @Override
    public boolean hasInputFluid() {
        return getInputAmount() > 0;
    }

    @Override
    public boolean hasOutputFluid() {
        return getOutputAmount() > 0;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("RefiningStage", refiningStage.id);
        output.putInt("StageProgress", stageProgress);
        output.putInt("BatchAmount", batchAmount);
        // Keep single-progress key for backward compatibility with older saves/readers.
        output.putInt("ProcessProgress", getProcessProgress());
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        int stageId = input.getIntOr("RefiningStage", -1);
        if (stageId >= 0) {
            refiningStage = RefiningStage.fromId(stageId);
            stageProgress = input.getIntOr("StageProgress", 0);
            batchAmount = input.getIntOr("BatchAmount", 0);
            if (refiningStage != RefiningStage.IDLE && batchAmount <= 0) {
                // Migration path for saves from before batch amount existed.
                batchAmount = PROCESS_AMOUNT;
                if (refiningStage == RefiningStage.DISTILLATION) {
                    int oldStageTicks = Math.min(stageProgress, DISTILLATION_TIME);
                    stageProgress = (int) ((long) oldStageTicks * batchAmount / DISTILLATION_TIME);
                } else if (refiningStage == RefiningStage.REFORMING) {
                    int oldStageTicks = Math.min(stageProgress, REFORMING_TIME);
                    stageProgress = (int) ((long) oldStageTicks * batchAmount / REFORMING_TIME);
                }
            }
            if (refiningStage == RefiningStage.IDLE) {
                stageProgress = 0;
                batchAmount = 0;
            }
            return;
        }

        // Migration path for older saves with a single progress value.
        int oldProgress = input.getIntOr("ProcessProgress", 0);
        if (oldProgress <= 0) {
            refiningStage = RefiningStage.IDLE;
            stageProgress = 0;
            batchAmount = 0;
        } else if (oldProgress < DISTILLATION_TIME) {
            refiningStage = RefiningStage.DISTILLATION;
            batchAmount = PROCESS_AMOUNT;
            stageProgress = (int) ((long) oldProgress * batchAmount / DISTILLATION_TIME);
        } else {
            refiningStage = RefiningStage.REFORMING;
            batchAmount = PROCESS_AMOUNT;
            int reformingProgress = Math.min(oldProgress - DISTILLATION_TIME, REFORMING_TIME);
            stageProgress = (int) ((long) reformingProgress * batchAmount / REFORMING_TIME);
        }
    }
}
