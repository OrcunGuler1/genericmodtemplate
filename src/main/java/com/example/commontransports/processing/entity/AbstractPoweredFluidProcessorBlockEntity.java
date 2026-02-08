package com.example.commontransports.processing.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.CombinedResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.energy.SimpleEnergyHandler;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.transaction.SnapshotJournal;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

/**
 * Shared implementation for FE-powered fluid processors with one input and one output tank.
 */
public abstract class AbstractPoweredFluidProcessorBlockEntity extends BlockEntity {

    public static final int MAX_SPEED_UPGRADES = 8;
    public static final int MAX_EFFICIENCY_UPGRADES = 8;

    private final Supplier<? extends Fluid> inputSourceSupplier;
    private final Supplier<? extends Fluid> inputFlowingSupplier;
    private final Supplier<? extends Fluid> outputSourceSupplier;
    private final Supplier<? extends Fluid> outputFlowingSupplier;

    protected final int inputCapacity;
    protected final int outputCapacity;
    protected final int processAmount;
    protected final int processTime;
    protected final int processRate;
    protected final int fePerTick;
    protected final int energyCapacity;
    protected final int maxFeInput;

    private int inputAmount = 0;
    private int outputAmount = 0;
    private int processProgress = 0;
    private int speedUpgrades = 0;
    private int efficiencyUpgrades = 0;

    private final SimpleEnergyHandler energyHandler;
    private final EnergyHandler externalEnergyHandler = new EnergyHandler() {
        @Override
        public long getAmountAsLong() {
            return energyHandler.getAmountAsLong();
        }

        @Override
        public long getCapacityAsLong() {
            return energyHandler.getCapacityAsLong();
        }

        @Override
        public int insert(int amount, TransactionContext transaction) {
            return energyHandler.insert(amount, transaction);
        }

        @Override
        public int extract(int amount, TransactionContext transaction) {
            return 0;
        }
    };
    private final InputTankHandler inputHandler = new InputTankHandler();
    private final OutputTankHandler outputHandler = new OutputTankHandler();
    // Expose output first so automation that chooses "first non-empty tank" extracts product, not feedstock.
    private final ResourceHandler<FluidResource> combinedFluidHandler = new CombinedResourceHandler<>(List.of(outputHandler, inputHandler));

    protected AbstractPoweredFluidProcessorBlockEntity(
            BlockEntityType<?> type, BlockPos pos, BlockState state,
            Supplier<? extends Fluid> inputSourceSupplier, Supplier<? extends Fluid> inputFlowingSupplier,
            Supplier<? extends Fluid> outputSourceSupplier, Supplier<? extends Fluid> outputFlowingSupplier,
            int inputCapacity, int outputCapacity, int processAmount, int processTime, int processRate,
            int fePerTick, int energyCapacity, int maxFeInput
    ) {
        super(type, pos, state);
        this.inputSourceSupplier = inputSourceSupplier;
        this.inputFlowingSupplier = inputFlowingSupplier;
        this.outputSourceSupplier = outputSourceSupplier;
        this.outputFlowingSupplier = outputFlowingSupplier;
        this.inputCapacity = inputCapacity;
        this.outputCapacity = outputCapacity;
        this.processAmount = processAmount;
        this.processTime = processTime;
        this.processRate = processRate;
        this.fePerTick = fePerTick;
        this.energyCapacity = energyCapacity;
        this.maxFeInput = maxFeInput;
        // Internal extraction must be enabled so the machine can spend FE while processing.
        this.energyHandler = new SimpleEnergyHandler(energyCapacity, maxFeInput, Integer.MAX_VALUE) {
            @Override
            protected void onEnergyChanged(int previousAmount) {
                setChanged();
            }
        };
    }

    public void tickServer() {
        if (level == null || level.isClientSide()) return;

        int toProcess = Math.min(getEffectiveProcessRate(), Math.min(inputAmount, outputCapacity - outputAmount));
        if (toProcess <= 0) {
            if (processProgress > 0) {
                processProgress = 0;
                setChanged();
            }
            return;
        }

        if (!consumeEnergy(getEffectiveFePerTick())) return;

        inputAmount -= toProcess;
        outputAmount += toProcess;

        if (processTime > 0) {
            processProgress += toProcess;
            if (processProgress >= processTime) {
                processProgress %= processTime;
            }
        }
        setChanged();
    }

    public final boolean installSpeedUpgrade() {
        if (speedUpgrades >= MAX_SPEED_UPGRADES) return false;
        speedUpgrades++;
        setChanged();
        return true;
    }

    public final boolean installEfficiencyUpgrade() {
        if (efficiencyUpgrades >= MAX_EFFICIENCY_UPGRADES) return false;
        efficiencyUpgrades++;
        setChanged();
        return true;
    }

    public final int getSpeedUpgrades() {
        return speedUpgrades;
    }

    public final int getEfficiencyUpgrades() {
        return efficiencyUpgrades;
    }

    public final int getEffectiveProcessRate() {
        return processRate + speedUpgrades * 2;
    }

    public final int getEffectiveFePerTick() {
        int scaled = fePerTick * (10 - efficiencyUpgrades) / 10;
        return Math.max(1, scaled);
    }

    protected final boolean consumeEnergy(int amount) {
        try (var transaction = Transaction.openRoot()) {
            int extracted = energyHandler.extract(amount, transaction);
            if (extracted != amount) return false;
            transaction.commit();
            return true;
        }
    }

    protected final int addOutputAmount(int amount) {
        if (amount <= 0) return 0;
        int space = outputCapacity - outputAmount;
        int toAdd = Math.min(amount, space);
        if (toAdd > 0) {
            outputAmount += toAdd;
            setChanged();
        }
        return toAdd;
    }

    public final int fillInput(Fluid fluid, int amount, boolean execute) {
        if (fluid != inputSourceSupplier.get() && fluid != inputFlowingSupplier.get()) return 0;
        int space = inputCapacity - inputAmount;
        int toFill = Math.min(amount, space);
        if (execute && toFill > 0) {
            inputAmount += toFill;
            setChanged();
        }
        return toFill;
    }

    public final int drainInput(int amount, boolean execute) {
        int toDrain = Math.min(amount, inputAmount);
        if (execute && toDrain > 0) {
            inputAmount -= toDrain;
            setChanged();
        }
        return toDrain;
    }

    public final int drainOutput(int amount, boolean execute) {
        int toDrain = Math.min(amount, outputAmount);
        if (execute && toDrain > 0) {
            outputAmount -= toDrain;
            setChanged();
        }
        return toDrain;
    }

    public final int getInputAmount() {
        return inputAmount;
    }

    public final int getInputCapacity() {
        return inputCapacity;
    }

    public final int getOutputAmount() {
        return outputAmount;
    }

    public final int getOutputCapacity() {
        return outputCapacity;
    }

    public int getProcessProgress() {
        return processProgress;
    }

    public final int getProcessTime() {
        return processTime;
    }

    public final int getEnergyStored() {
        return energyHandler.getAmountAsInt();
    }

    public final int getEnergyCapacity() {
        return energyCapacity;
    }

    public final ResourceHandler<FluidResource> getFluidHandler(@Nullable Direction side) {
        return combinedFluidHandler;
    }

    public final ResourceHandler<FluidResource> getOutputFluidHandler(@Nullable Direction side) {
        return outputHandler;
    }

    public final EnergyHandler getEnergyHandler(@Nullable Direction side) {
        return externalEnergyHandler;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("InputAmount", inputAmount);
        output.putInt("OutputAmount", outputAmount);
        output.putInt("ProcessProgress", processProgress);
        output.putInt("SpeedUpgrades", speedUpgrades);
        output.putInt("EfficiencyUpgrades", efficiencyUpgrades);
        output.putChild("Energy", energyHandler);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        inputAmount = input.getIntOr("InputAmount", 0);
        outputAmount = input.getIntOr("OutputAmount", 0);
        processProgress = input.getIntOr("ProcessProgress", 0);
        speedUpgrades = Math.min(MAX_SPEED_UPGRADES, Math.max(0, input.getIntOr("SpeedUpgrades", 0)));
        efficiencyUpgrades = Math.min(MAX_EFFICIENCY_UPGRADES, Math.max(0, input.getIntOr("EfficiencyUpgrades", 0)));
        input.readChild("Energy", energyHandler);
    }

    private boolean isInputResource(FluidResource resource) {
        FluidResource source = FluidResource.of(inputSourceSupplier.get());
        FluidResource flowing = FluidResource.of(inputFlowingSupplier.get());
        return resource.equals(source) || resource.equals(flowing);
    }

    private boolean isOutputResource(FluidResource resource) {
        FluidResource source = FluidResource.of(outputSourceSupplier.get());
        FluidResource flowing = FluidResource.of(outputFlowingSupplier.get());
        return resource.equals(source) || resource.equals(flowing);
    }

    private FluidResource outputResourceOrEmpty() {
        return outputAmount > 0 ? FluidResource.of(outputSourceSupplier.get()) : FluidResource.EMPTY;
    }

    private FluidResource inputResourceOrEmpty() {
        return inputAmount > 0 ? FluidResource.of(inputSourceSupplier.get()) : FluidResource.EMPTY;
    }

    private class InputTankHandler extends SnapshotJournal<Integer> implements ResourceHandler<FluidResource> {
        @Override
        public int size() {
            return 1;
        }

        @Override
        public FluidResource getResource(int index) {
            return index == 0 ? inputResourceOrEmpty() : FluidResource.EMPTY;
        }

        @Override
        public long getAmountAsLong(int index) {
            return index == 0 ? inputAmount : 0;
        }

        @Override
        public long getCapacityAsLong(int index, FluidResource resource) {
            return index == 0 && (resource.isEmpty() || isInputResource(resource)) ? inputCapacity : 0;
        }

        @Override
        public boolean isValid(int index, FluidResource resource) {
            return index == 0 && isInputResource(resource);
        }

        @Override
        public int insert(int index, FluidResource resource, int amount, TransactionContext transaction) {
            if (index != 0 || !isInputResource(resource) || amount <= 0) return 0;
            int space = inputCapacity - inputAmount;
            int toInsert = Math.min(amount, space);
            if (toInsert > 0) {
                updateSnapshots(transaction);
                inputAmount += toInsert;
            }
            return toInsert;
        }

        @Override
        public int extract(int index, FluidResource resource, int amount, TransactionContext transaction) {
            return 0;
        }

        @Override
        protected Integer createSnapshot() {
            return inputAmount;
        }

        @Override
        protected void revertToSnapshot(Integer snapshot) {
            inputAmount = snapshot;
        }

        @Override
        protected void onRootCommit(Integer originalState) {
            setChanged();
        }
    }

    private class OutputTankHandler extends SnapshotJournal<Integer> implements ResourceHandler<FluidResource> {
        @Override
        public int size() {
            return 1;
        }

        @Override
        public FluidResource getResource(int index) {
            return index == 0 ? outputResourceOrEmpty() : FluidResource.EMPTY;
        }

        @Override
        public long getAmountAsLong(int index) {
            return index == 0 ? outputAmount : 0;
        }

        @Override
        public long getCapacityAsLong(int index, FluidResource resource) {
            return index == 0 && (resource.isEmpty() || isOutputResource(resource)) ? outputCapacity : 0;
        }

        @Override
        public boolean isValid(int index, FluidResource resource) {
            return false;
        }

        @Override
        public int insert(int index, FluidResource resource, int amount, TransactionContext transaction) {
            return 0;
        }

        @Override
        public int extract(int index, FluidResource resource, int amount, TransactionContext transaction) {
            if (index != 0 || amount <= 0 || outputAmount <= 0) return 0;
            if (!resource.isEmpty() && !isOutputResource(resource)) return 0;
            int toExtract = Math.min(amount, outputAmount);
            if (toExtract > 0) {
                updateSnapshots(transaction);
                outputAmount -= toExtract;
            }
            return toExtract;
        }

        @Override
        protected Integer createSnapshot() {
            return outputAmount;
        }

        @Override
        protected void revertToSnapshot(Integer snapshot) {
            outputAmount = snapshot;
        }

        @Override
        protected void onRootCommit(Integer originalState) {
            setChanged();
        }
    }
}
