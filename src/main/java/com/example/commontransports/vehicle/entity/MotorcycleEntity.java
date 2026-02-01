package com.example.commontransports.vehicle.entity;

import com.example.commontransports.GenericMod;
import com.example.commontransports.api.entity.VehicleStats;
import com.example.commontransports.api.item.FluidContainerItem;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

/**
 * Motorcycle entity. Uses {@link VehicleStats#MOTORCYCLE} and fuels from gas cans.
 */
public class MotorcycleEntity extends AbstractStatsVehicleEntity {

    private static final EntityDataAccessor<Integer> FUEL = SynchedEntityData.defineId(MotorcycleEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DURABILITY = SynchedEntityData.defineId(MotorcycleEntity.class, EntityDataSerializers.INT);

    public MotorcycleEntity(EntityType<? extends MotorcycleEntity> type, Level level) {
        super(type, level, VehicleStats.MOTORCYCLE.inventorySize);
    }

    @Override
    public VehicleStats getStats() {
        return VehicleStats.MOTORCYCLE;
    }

    @Override
    public Item getDropItem() {
        return GenericMod.MOTORCYCLE_ITEM.get();
    }

    @Override
    public boolean isFuelItem(ItemStack stack) {
        return stack.is(GenericMod.GAS_CAN.get());
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(FUEL, 0);
        builder.define(DURABILITY, VehicleStats.MOTORCYCLE.maxDurability);
    }

    @Override
    public int getFuel() { return entityData.get(FUEL); }

    @Override
    public void setFuel(int value) { entityData.set(FUEL, Math.max(0, value)); }

    @Override
    public int getDurability() { return entityData.get(DURABILITY); }

    @Override
    public void setDurability(int value) {
        entityData.set(DURABILITY, Mth.clamp(value, 0, getStats().maxDurability));
    }

    @Override
    protected InteractionResult handleFueling(Player player, ItemStack held) {
        if (getFuel() >= getStats().maxFuel) return InteractionResult.PASS;
        if (held.getItem() instanceof FluidContainerItem fc) {
            int canFuel = fc.getFluidAmount(held);
            if (canFuel <= 0) return InteractionResult.PASS;
            int spaceInTank = getStats().maxFuel - getFuel();
            int toTransfer = Math.min(canFuel, spaceInTank);
            if (toTransfer > 0 && !player.getAbilities().instabuild) fc.consumeFuel(held, toTransfer);
            setFuel(getFuel() + toTransfer);
            gameEvent(GameEvent.ENTITY_INTERACT);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
