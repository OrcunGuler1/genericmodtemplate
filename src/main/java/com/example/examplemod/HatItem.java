package com.example.examplemod;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.Equippable;
import net.neoforged.neoforge.registries.DeferredItem;

/**
 * Hat item worn in the helmet slot. Cosmetic only (no armor points).
 * Uses the Equippable data component (no ArmorMaterial registry).
 * Add your texture at: assets/genericmod/textures/entity/equipment/hat.png
 * Add equipment model at: assets/genericmod/models/equipment/hat.json
 */
public final class HatItem {

    public static final DeferredItem<Item> HAT = GenericMod.ITEMS.registerSimpleItem("hat",
            p -> p.component(
                    DataComponents.EQUIPPABLE,
                    Equippable.builder(EquipmentSlot.HEAD)
                            .setEquipSound(SoundEvents.ARMOR_EQUIP_LEATHER)
                            .build()));
}
