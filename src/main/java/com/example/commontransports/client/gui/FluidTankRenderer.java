package com.example.commontransports.client.gui;

import java.util.Optional;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;

/**
 * Client-only helper to get fluid still sprite and tint for tank bar rendering (Mekanism-style).
 */
public final class FluidTankRenderer {

    private FluidTankRenderer() {}

    /** Default tint (white) when fluid has no client extensions. */
    public static final int DEFAULT_TINT = 0xFFFFFFFF;

    /**
     * Returns the fluid's still texture sprite from the block atlas, or empty if not available.
     * When your MC version exposes a block-atlas sprite getter (e.g. getTextureAtlas(LOCATION_BLOCKS).apply(still)),
     * use it here and the refinery will draw tiled fluid texture instead of solid fill.
     */
    public static Optional<TextureAtlasSprite> getStillSprite(Fluid fluid) {
        IClientFluidTypeExtensions ext = IClientFluidTypeExtensions.of(fluid);
        if (ext == null) return Optional.empty();
        Identifier still = ext.getStillTexture();
        if (still == null) return Optional.empty();
        // Block atlas sprite getter API varies by version; until then we use tint-only fill
        return Optional.empty();
    }

    /**
     * Returns the fluid's tint color (ARGB) for rendering. Use {@link #DEFAULT_TINT} when no tint.
     */
    public static int getTintColor(Fluid fluid) {
        IClientFluidTypeExtensions ext = IClientFluidTypeExtensions.of(fluid);
        return ext != null ? ext.getTintColor() : DEFAULT_TINT;
    }
}
