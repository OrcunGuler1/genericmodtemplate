package com.example.commontransports.vehicle.item;

import com.example.commontransports.GenericMod;
import com.example.commontransports.vehicle.entity.ChopperEntity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ChopperItem extends AbstractVehiclePlacementItem {

    public ChopperItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    protected Entity createEntity(Level level, Vec3 spawnPos, float yRot, Player player) {
        ChopperEntity chopper = new ChopperEntity(GenericMod.CHOPPER.get(), level);
        chopper.setYRot(yRot);
        return chopper;
    }
}
