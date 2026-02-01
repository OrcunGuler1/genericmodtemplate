package com.example.commontransports.api.entity;

import net.minecraft.world.item.Item;

/**
 * Entity that drops a specific item when destroyed.
 */
public interface HasDropItem {

    Item getDropItem();
}
