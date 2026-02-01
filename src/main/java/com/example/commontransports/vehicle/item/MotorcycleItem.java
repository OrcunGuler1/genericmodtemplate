package com.example.commontransports.vehicle.item;

import com.example.commontransports.GenericMod;
import com.example.commontransports.vehicle.entity.MotorcycleEntity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class MotorcycleItem extends AbstractVehiclePlacementItem {

    public MotorcycleItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    protected Entity createEntity(Level level, Vec3 spawnPos, float yRot, Player player) {
        MotorcycleEntity motorcycle = new MotorcycleEntity(GenericMod.MOTORCYCLE.get(), level);
        motorcycle.setYRot(yRot);
        return motorcycle;
    }
}
