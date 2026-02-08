package com.example.commontransports.pipe.entity;

import com.example.commontransports.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandlerUtil;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.transaction.SnapshotJournal;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import org.jspecify.annotations.Nullable;

import java.util.function.Predicate;

/**
 * Minimal fluid transport node with automatic pull/push behavior on all sides.
 */
public class BasicFluidPipeBlockEntity extends BlockEntity {

    public static final int TANK_CAPACITY = 4000;
    public static final int PULL_RATE_PER_SIDE = 200;
    public static final int PUSH_RATE_PER_SIDE = 200;

    private Fluid storedFluid = Fluids.EMPTY;
    private int storedAmount = 0;
    private int recentReceiveMask = 0;

    private final PipeTankHandler fluidHandler = new PipeTankHandler();
    private final ResourceHandler<FluidResource>[] sidedHandlers = createSidedHandlers();

    public BasicFluidPipeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BASIC_FLUID_PIPE.get(), pos, state);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, BasicFluidPipeBlockEntity blockEntity) {
        blockEntity.tickServer();
    }

    public ResourceHandler<FluidResource> getFluidHandler(@Nullable Direction side) {
        return side == null ? fluidHandler : sidedHandlers[side.ordinal()];
    }

    private void tickServer() {
        if (level == null || level.isClientSide()) return;

        // Block pushing back into sides that recently inserted into this pipe.
        int blockedPushMask = recentReceiveMask;
        recentReceiveMask = 0;

        // Pull first so the pipe can forward in the same tick. Track pull sides to avoid instant backflow.
        for (Direction side : Direction.values()) {
            int moved = pullFromNeighbor(side);
            if (moved > 0) {
                blockedPushMask |= sideBit(side);
            }
        }
        for (Direction side : Direction.values()) {
            if ((blockedPushMask & sideBit(side)) != 0) continue;
            pushToNeighbor(side);
        }
    }

    private int pushToNeighbor(Direction side) {
        if (storedAmount <= 0 || storedFluid == Fluids.EMPTY || level == null) return 0;

        var neighbor = level.getCapability(
                Capabilities.Fluid.BLOCK,
                worldPosition.relative(side),
                side.getOpposite()
        );
        if (neighbor == null) return 0;

        FluidResource resource = FluidResource.of(storedFluid);
        return ResourceHandlerUtil.move(fluidHandler, neighbor, resource::equals, PUSH_RATE_PER_SIDE, null);
    }

    private int pullFromNeighbor(Direction side) {
        if (storedAmount >= TANK_CAPACITY || level == null) return 0;

        var neighbor = level.getCapability(
                Capabilities.Fluid.BLOCK,
                worldPosition.relative(side),
                side.getOpposite()
        );
        if (neighbor == null) return 0;

        Predicate<FluidResource> filter;
        if (storedFluid == Fluids.EMPTY) {
            filter = resource -> !resource.isEmpty();
        } else {
            FluidResource target = FluidResource.of(storedFluid);
            filter = target::equals;
        }

        return ResourceHandlerUtil.move(neighbor, fluidHandler, filter, PULL_RATE_PER_SIDE, null);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("StoredAmount", storedAmount);
        if (storedAmount > 0 && storedFluid != Fluids.EMPTY) {
            output.putString("StoredFluid", BuiltInRegistries.FLUID.getKey(storedFluid).toString());
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        storedAmount = Math.max(0, Math.min(TANK_CAPACITY, input.getIntOr("StoredAmount", 0)));
        if (storedAmount <= 0) {
            storedFluid = Fluids.EMPTY;
            storedAmount = 0;
            return;
        }

        String fluidId = input.getStringOr("StoredFluid", "minecraft:empty");
        Identifier id = Identifier.tryParse(fluidId);
        Fluid loadedFluid = id != null ? BuiltInRegistries.FLUID.getValue(id) : Fluids.EMPTY;
        if (loadedFluid == null || loadedFluid == Fluids.EMPTY) {
            storedFluid = Fluids.EMPTY;
            storedAmount = 0;
        } else {
            storedFluid = loadedFluid;
        }
    }

    private record TankSnapshot(Fluid fluid, int amount) {}

    private static int sideBit(Direction side) {
        return 1 << side.ordinal();
    }

    @SuppressWarnings("unchecked")
    private ResourceHandler<FluidResource>[] createSidedHandlers() {
        ResourceHandler<FluidResource>[] handlers = (ResourceHandler<FluidResource>[]) new ResourceHandler[Direction.values().length];
        for (Direction direction : Direction.values()) {
            handlers[direction.ordinal()] = new SidedPipeTankHandler(direction);
        }
        return handlers;
    }

    private class SidedPipeTankHandler implements ResourceHandler<FluidResource> {
        private final Direction side;

        private SidedPipeTankHandler(Direction side) {
            this.side = side;
        }

        @Override
        public int size() {
            return fluidHandler.size();
        }

        @Override
        public FluidResource getResource(int index) {
            return fluidHandler.getResource(index);
        }

        @Override
        public long getAmountAsLong(int index) {
            return fluidHandler.getAmountAsLong(index);
        }

        @Override
        public long getCapacityAsLong(int index, FluidResource resource) {
            return fluidHandler.getCapacityAsLong(index, resource);
        }

        @Override
        public boolean isValid(int index, FluidResource resource) {
            return fluidHandler.isValid(index, resource);
        }

        @Override
        public int insert(int index, FluidResource resource, int amount, TransactionContext transaction) {
            int inserted = fluidHandler.insert(index, resource, amount, transaction);
            if (inserted > 0) {
                recentReceiveMask |= sideBit(side);
            }
            return inserted;
        }

        @Override
        public int extract(int index, FluidResource resource, int amount, TransactionContext transaction) {
            return fluidHandler.extract(index, resource, amount, transaction);
        }
    }

    private class PipeTankHandler extends SnapshotJournal<TankSnapshot> implements ResourceHandler<FluidResource> {
        @Override
        public int size() {
            return 1;
        }

        @Override
        public FluidResource getResource(int index) {
            return index == 0 && storedAmount > 0 ? FluidResource.of(storedFluid) : FluidResource.EMPTY;
        }

        @Override
        public long getAmountAsLong(int index) {
            return index == 0 ? storedAmount : 0;
        }

        @Override
        public long getCapacityAsLong(int index, FluidResource resource) {
            if (index != 0) return 0;
            if (resource.isEmpty()) return TANK_CAPACITY;
            if (storedAmount > 0 && resource.getFluid() != storedFluid) return 0;
            return TANK_CAPACITY;
        }

        @Override
        public boolean isValid(int index, FluidResource resource) {
            return index == 0 && !resource.isEmpty() && (storedAmount <= 0 || resource.getFluid() == storedFluid);
        }

        @Override
        public int insert(int index, FluidResource resource, int amount, TransactionContext transaction) {
            if (index != 0 || amount <= 0 || resource.isEmpty()) return 0;
            if (storedAmount > 0 && resource.getFluid() != storedFluid) return 0;

            int space = TANK_CAPACITY - storedAmount;
            int toInsert = Math.min(space, amount);
            if (toInsert <= 0) return 0;

            updateSnapshots(transaction);
            if (storedAmount == 0) {
                storedFluid = resource.getFluid();
            }
            storedAmount += toInsert;
            return toInsert;
        }

        @Override
        public int extract(int index, FluidResource resource, int amount, TransactionContext transaction) {
            if (index != 0 || amount <= 0 || storedAmount <= 0) return 0;
            if (resource.isEmpty() || resource.getFluid() != storedFluid) return 0;

            int toExtract = Math.min(storedAmount, amount);
            if (toExtract <= 0) return 0;

            updateSnapshots(transaction);
            storedAmount -= toExtract;
            if (storedAmount == 0) {
                storedFluid = Fluids.EMPTY;
            }
            return toExtract;
        }

        @Override
        protected TankSnapshot createSnapshot() {
            return new TankSnapshot(storedFluid, storedAmount);
        }

        @Override
        protected void revertToSnapshot(TankSnapshot snapshot) {
            storedFluid = snapshot.fluid();
            storedAmount = snapshot.amount();
        }

        @Override
        protected void onRootCommit(TankSnapshot originalState) {
            if (storedFluid != originalState.fluid() || storedAmount != originalState.amount()) {
                setChanged();
            }
        }
    }
}
