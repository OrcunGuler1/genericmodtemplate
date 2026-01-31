package com.example.commontransports.block.entity;

import com.example.commontransports.fluid.ModFluids;
import com.example.commontransports.menu.RefineryMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.CombinedResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.transaction.SnapshotJournal;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Refinery Block Entity - processes crude oil into petrol.
 * Uses the modern NeoForge Transfer API (ResourceHandler) for pipe/automation support.
 */
public class RefineryBlockEntity extends BlockEntity implements MenuProvider {
    
    public static final int INPUT_CAPACITY = 8000;  // 8 buckets of crude oil
    public static final int OUTPUT_CAPACITY = 8000; // 8 buckets of petrol
    public static final int PROCESS_TIME = 200;     // 10 seconds per bucket (200 ticks)
    public static final int PROCESS_AMOUNT = 1000;  // 1 bucket at a time
    
    // Simple integer-based fluid storage
    private int inputAmount = 0;
    private int outputAmount = 0;
    private int processProgress = 0;
    
    // Resource handlers for pipe/automation support
    private final InputTankHandler inputHandler = new InputTankHandler();
    private final OutputTankHandler outputHandler = new OutputTankHandler();
    private final ResourceHandler<FluidResource> combinedHandler = new CombinedResourceHandler<>(List.of(inputHandler, outputHandler));
    
    // ContainerData for syncing to client
    private final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> inputAmount;
                case 1 -> INPUT_CAPACITY;
                case 2 -> outputAmount;
                case 3 -> OUTPUT_CAPACITY;
                case 4 -> processProgress;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            // Client-side updates not needed for display-only data
        }

        @Override
        public int getCount() {
            return 5;
        }
    };
    
    public RefineryBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.REFINERY.get(), pos, state);
    }
    
    // MenuProvider implementation
    @Override
    public Component getDisplayName() {
        return Component.translatable("container.common_transports.refinery");
    }
    
    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new RefineryMenu(containerId, playerInventory, this, data);
    }
    
    public static void serverTick(Level level, BlockPos pos, BlockState state, RefineryBlockEntity refinery) {
        refinery.tick();
    }
    
    private void tick() {
        if (level == null || level.isClientSide()) {
            return;
        }
        
        // Check if we can process
        boolean canProcess = canProcess();
        
        if (canProcess) {
            processProgress++;
            
            if (processProgress >= PROCESS_TIME) {
                // Complete processing - consume input, produce output
                inputAmount -= PROCESS_AMOUNT;
                outputAmount += PROCESS_AMOUNT;
                processProgress = 0;
            }
            setChanged();
        } else if (processProgress > 0) {
            // Reset progress if we can't continue
            processProgress = 0;
            setChanged();
        }
    }
    
    private boolean canProcess() {
        // Need at least PROCESS_AMOUNT crude oil in input
        if (inputAmount < PROCESS_AMOUNT) {
            return false;
        }
        // Need space in output for PROCESS_AMOUNT petrol
        return (OUTPUT_CAPACITY - outputAmount) >= PROCESS_AMOUNT;
    }
    
    // Public methods for block interaction
    public int fillInput(Fluid fluid, int amount, boolean execute) {
        if (fluid != ModFluids.CRUDE_OIL_SOURCE.get()) {
            return 0;
        }
        int space = INPUT_CAPACITY - inputAmount;
        int toFill = Math.min(amount, space);
        if (execute && toFill > 0) {
            inputAmount += toFill;
            setChanged();
        }
        return toFill;
    }
    
    public int drainInput(int amount, boolean execute) {
        int toDrain = Math.min(amount, inputAmount);
        if (execute && toDrain > 0) {
            inputAmount -= toDrain;
            setChanged();
        }
        return toDrain;
    }
    
    public int drainOutput(int amount, boolean execute) {
        int toDrain = Math.min(amount, outputAmount);
        if (execute && toDrain > 0) {
            outputAmount -= toDrain;
            setChanged();
        }
        return toDrain;
    }
    
    public boolean hasInputFluid() {
        return inputAmount > 0;
    }
    
    public boolean hasOutputFluid() {
        return outputAmount > 0;
    }
    
    public int getInputAmount() {
        return inputAmount;
    }
    
    public int getInputCapacity() {
        return INPUT_CAPACITY;
    }
    
    public int getOutputAmount() {
        return outputAmount;
    }
    
    public int getOutputCapacity() {
        return OUTPUT_CAPACITY;
    }
    
    public int getProcessProgress() {
        return processProgress;
    }
    
    public int getProcessTime() {
        return PROCESS_TIME;
    }
    
    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("InputAmount", inputAmount);
        output.putInt("OutputAmount", outputAmount);
        output.putInt("ProcessProgress", processProgress);
    }
    
    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        inputAmount = input.getIntOr("InputAmount", 0);
        outputAmount = input.getIntOr("OutputAmount", 0);
        processProgress = input.getIntOr("ProcessProgress", 0);
    }
    
    /**
     * Get the appropriate fluid handler for the given side.
     * Provides pipe/automation support via the modern ResourceHandler API.
     * - TOP/sides: input tank (crude oil in)
     * - BOTTOM: output tank (petrol out)
     * - null: combined handler (for GUI queries)
     */
    public ResourceHandler<FluidResource> getFluidHandler(@Nullable Direction side) {
        if (side == Direction.DOWN) {
            return outputHandler;
        } else if (side == null) {
            return combinedHandler;
        } else {
            return inputHandler;
        }
    }
    
    // Inner class for input tank handler with transaction support
    private class InputTankHandler extends SnapshotJournal<Integer> implements ResourceHandler<FluidResource> {
        private final FluidResource crudeOilResource = FluidResource.of(ModFluids.CRUDE_OIL_SOURCE.get());
        
        @Override
        public int size() {
            return 1;
        }
        
        @Override
        public FluidResource getResource(int index) {
            return index == 0 && inputAmount > 0 ? crudeOilResource : FluidResource.EMPTY;
        }
        
        @Override
        public long getAmountAsLong(int index) {
            return index == 0 ? inputAmount : 0;
        }
        
        @Override
        public long getCapacityAsLong(int index, FluidResource resource) {
            return index == 0 && (resource.isEmpty() || resource.equals(crudeOilResource)) ? INPUT_CAPACITY : 0;
        }
        
        @Override
        public boolean isValid(int index, FluidResource resource) {
            return index == 0 && resource.equals(crudeOilResource);
        }
        
        @Override
        public int insert(int index, FluidResource resource, int amount, TransactionContext transaction) {
            if (index != 0 || !resource.equals(crudeOilResource) || amount <= 0) {
                return 0;
            }
            int space = INPUT_CAPACITY - inputAmount;
            int toInsert = Math.min(amount, space);
            if (toInsert > 0) {
                updateSnapshots(transaction);
                inputAmount += toInsert;
            }
            return toInsert;
        }
        
        @Override
        public int extract(int index, FluidResource resource, int amount, TransactionContext transaction) {
            if (index != 0 || !resource.equals(crudeOilResource) || inputAmount <= 0 || amount <= 0) {
                return 0;
            }
            int toExtract = Math.min(amount, inputAmount);
            if (toExtract > 0) {
                updateSnapshots(transaction);
                inputAmount -= toExtract;
            }
            return toExtract;
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
    
    // Inner class for output tank handler with transaction support
    private class OutputTankHandler extends SnapshotJournal<Integer> implements ResourceHandler<FluidResource> {
        private final FluidResource petrolResource = FluidResource.of(ModFluids.PETROL_SOURCE.get());
        
        @Override
        public int size() {
            return 1;
        }
        
        @Override
        public FluidResource getResource(int index) {
            return index == 0 && outputAmount > 0 ? petrolResource : FluidResource.EMPTY;
        }
        
        @Override
        public long getAmountAsLong(int index) {
            return index == 0 ? outputAmount : 0;
        }
        
        @Override
        public long getCapacityAsLong(int index, FluidResource resource) {
            return index == 0 && (resource.isEmpty() || resource.equals(petrolResource)) ? OUTPUT_CAPACITY : 0;
        }
        
        @Override
        public boolean isValid(int index, FluidResource resource) {
            // Output tank doesn't accept external fluids
            return false;
        }
        
        @Override
        public int insert(int index, FluidResource resource, int amount, TransactionContext transaction) {
            // Output tank doesn't accept external inserts
            return 0;
        }
        
        @Override
        public int extract(int index, FluidResource resource, int amount, TransactionContext transaction) {
            if (index != 0 || !resource.equals(petrolResource) || outputAmount <= 0 || amount <= 0) {
                return 0;
            }
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
