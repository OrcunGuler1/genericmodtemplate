package com.example.commontransports.power.entity;

import com.example.commontransports.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import org.jspecify.annotations.Nullable;

/**
 * Creative energy source that exposes effectively infinite FE and pushes FE to all adjacent blocks.
 */
public class CreativeEnergyCellBlockEntity extends BlockEntity {

    private static final int REPORTED_ENERGY = Integer.MAX_VALUE;
    public static final int PUSH_PER_SIDE_PER_TICK = 1_000_000;

    private final EnergyHandler energyHandler = new EnergyHandler() {
        @Override
        public long getAmountAsLong() {
            return REPORTED_ENERGY;
        }

        @Override
        public long getCapacityAsLong() {
            return REPORTED_ENERGY;
        }

        @Override
        public int insert(int amount, TransactionContext transaction) {
            if (amount < 0) {
                throw new IllegalArgumentException("amount must be non-negative");
            }
            return 0;
        }

        @Override
        public int extract(int amount, TransactionContext transaction) {
            if (amount < 0) {
                throw new IllegalArgumentException("amount must be non-negative");
            }
            return amount;
        }
    };

    public CreativeEnergyCellBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CREATIVE_ENERGY_CELL.get(), pos, state);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, CreativeEnergyCellBlockEntity blockEntity) {
        blockEntity.pushEnergyToNeighbors();
    }

    public EnergyHandler getEnergyHandler(@Nullable Direction side) {
        return energyHandler;
    }

    private void pushEnergyToNeighbors() {
        if (level == null || level.isClientSide()) return;

        for (Direction side : Direction.values()) {
            EnergyHandler neighbor = level.getCapability(
                    Capabilities.Energy.BLOCK,
                    worldPosition.relative(side),
                    side.getOpposite()
            );
            if (neighbor == null) continue;

            try (Transaction transaction = Transaction.openRoot()) {
                int inserted = neighbor.insert(PUSH_PER_SIDE_PER_TICK, transaction);
                if (inserted > 0) {
                    transaction.commit();
                }
            }
        }
    }
}
