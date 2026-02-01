package com.example.commontransports.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.Identifier;

/**
 * Shared GUI drawing helpers (inspired by Mekanism's GuiUtils).
 * Use for consistent fills, outlines, backdrops, and nine-sliced backgrounds.
 */
public final class GuiUtils {

    private GuiUtils() {}

    // ---------- Nine-sliced background rendering ----------

    /**
     * Renders a background using a small texture expanded via nine-slice to the given size.
     * Good for small widgets where the background is a single color or simple pattern.
     * Texture size is inferred as (2 * sideWidth + 1) x (2 * sideHeight + 1).
     */
    public static void renderExtendedTexture(GuiGraphics guiGraphics, Identifier texture,
            int sideWidth, int sideHeight, int left, int top, int width, int height) {
        int texW = 2 * sideWidth + 1;
        int texH = 2 * sideHeight + 1;
        blitNineSlicedSized(guiGraphics, texture, left, top, width, height,
                sideWidth, sideHeight, texW, texH, 0, 0, texW, texH);
    }

    /**
     * Renders a background using a large texture, scaled/tiled via nine-slice to the given size.
     * Good for larger panels; use a sufficiently large texture to avoid heavy tiling.
     */
    public static void renderBackgroundTexture(GuiGraphics guiGraphics, Identifier texture,
            int texSideWidth, int texSideHeight, int left, int top, int width, int height,
            int textureWidth, int textureHeight) {
        blitNineSlicedSized(guiGraphics, texture, left, top, width, height,
                texSideWidth, texSideHeight, textureWidth, textureHeight, 0, 0, textureWidth, textureHeight);
    }

    /**
     * Draws a nine-sliced texture: corners and edges from the texture, center stretched to fill.
     * Uses one slice size for both dimensions when sliceWidth == sliceHeight.
     */
    public static void blitNineSlicedSized(GuiGraphics guiGraphics, Identifier texture,
            int x, int y, int width, int height, int sliceSize,
            int uWidth, int vHeight, int uOffset, int vOffset, int textureWidth, int textureHeight) {
        blitNineSlicedSized(guiGraphics, texture, x, y, width, height,
                sliceSize, sliceSize, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight);
    }

    /**
     * Draws a nine-sliced texture at (x,y) with size (width, height). Slice dimensions are
     * clamped to half of width/height so the layout stays valid.
     */
    public static void blitNineSlicedSized(GuiGraphics guiGraphics, Identifier texture,
            int x, int y, int width, int height, int sliceWidth, int sliceHeight,
            int uWidth, int vHeight, int uOffset, int vOffset, int textureWidth, int textureHeight) {
        int cw = Math.min(sliceWidth, width / 2);
        int ch = Math.min(sliceHeight, height / 2);
        int ew = cw;
        int eh = ch;
        float tw = (float) textureWidth;
        float th = (float) textureHeight;

        if (width == uWidth && height == vHeight) {
            blit(guiGraphics, texture, x, y, width, height, uOffset, vOffset, width, height, tw, th);
        } else if (height == vHeight) {
            blit(guiGraphics, texture, x, y, cw, height, uOffset, vOffset, cw, vHeight, tw, th);
            blit(guiGraphics, texture, x + cw, y, width - ew - cw, height, uOffset + cw, vOffset, uWidth - ew - cw, vHeight, tw, th);
            blit(guiGraphics, texture, x + width - ew, y, ew, height, uOffset + uWidth - ew, vOffset, ew, vHeight, tw, th);
        } else if (width == uWidth) {
            blit(guiGraphics, texture, x, y, width, ch, uOffset, vOffset, uWidth, ch, tw, th);
            blit(guiGraphics, texture, x, y + ch, width, height - eh - ch, uOffset, vOffset + ch, uWidth, vHeight - eh - ch, tw, th);
            blit(guiGraphics, texture, x, y + height - eh, width, eh, uOffset, vOffset + vHeight - eh, uWidth, eh, tw, th);
        } else {
            // 4 corners
            blit(guiGraphics, texture, x, y, cw, ch, uOffset, vOffset, cw, ch, tw, th);
            blit(guiGraphics, texture, x + width - ew, y, ew, ch, uOffset + uWidth - ew, vOffset, ew, ch, tw, th);
            blit(guiGraphics, texture, x, y + height - eh, cw, eh, uOffset, vOffset + vHeight - eh, cw, eh, tw, th);
            blit(guiGraphics, texture, x + width - ew, y + height - eh, ew, eh, uOffset + uWidth - ew, vOffset + vHeight - eh, ew, eh, tw, th);
            // 4 edges
            blit(guiGraphics, texture, x + cw, y, width - ew - cw, ch, uOffset + cw, vOffset, uWidth - ew - cw, ch, tw, th);
            blit(guiGraphics, texture, x + cw, y + height - eh, width - ew - cw, eh, uOffset + cw, vOffset + vHeight - eh, uWidth - ew - cw, eh, tw, th);
            blit(guiGraphics, texture, x, y + ch, cw, height - eh - ch, uOffset, vOffset + ch, cw, vHeight - eh - ch, tw, th);
            blit(guiGraphics, texture, x + width - ew, y + ch, ew, height - eh - ch, uOffset + uWidth - ew, vOffset + ch, ew, vHeight - eh - ch, tw, th);
            // center
            blit(guiGraphics, texture, x + cw, y + ch, width - ew - cw, height - eh - ch,
                    uOffset + cw, vOffset + ch, uWidth - ew - cw, vHeight - eh - ch, tw, th);
        }
    }

    private static void blit(GuiGraphics g, Identifier texture, int x, int y, int w, int h,
            int srcX, int srcY, int srcW, int srcH, float texW, float texH) {
        if (w <= 0 || h <= 0) return;
        float u0 = srcX / texW;
        float v0 = srcY / texH;
        float u1 = (srcX + srcW) / texW;
        float v1 = (srcY + srcH) / texH;
        g.blit(texture, x, y, w, h, u0, v0, u1, v1);
    }

    /**
     * Fills a rectangle. No-op if width or height is zero (avoids degenerate draws).
     */
    public static void fill(GuiGraphics guiGraphics, int x, int y, int width, int height, int color) {
        if (width != 0 && height != 0) {
            guiGraphics.fill(x, y, x + width, y + height, color);
        }
    }

    /**
     * Draws a 1px outline around the given rectangle.
     */
    public static void drawOutline(GuiGraphics guiGraphics, int x, int y, int width, int height, int color) {
        fill(guiGraphics, x, y, width, 1, color);
        fill(guiGraphics, x, y + height - 1, width, 1, color);
        if (height > 2) {
            fill(guiGraphics, x, y + 1, 1, height - 2, color);
            fill(guiGraphics, x + width - 1, y + 1, 1, height - 2, color);
        }
    }

    /**
     * Draws a 1px border using horizontal and vertical lines (same result as {@link #drawOutline}).
     */
    public static void drawBorder(GuiGraphics guiGraphics, int x, int y, int boxWidth, int boxHeight, int color) {
        guiGraphics.hLine(x, x + boxWidth, y, color);
        guiGraphics.hLine(x, x + boxWidth, y + boxHeight, color);
        guiGraphics.vLine(x, y, y + boxHeight, color);
        guiGraphics.vLine(x + boxWidth, y, y + boxHeight, color);
    }

    /**
     * Draws a text backdrop (slightly larger than the text area) using the game's background color and the given alpha.
     * Convenience overload using the font's line height for height.
     */
    public static void drawBackdrop(GuiGraphics guiGraphics, Minecraft minecraft, int x, int y, int width, int alpha) {
        drawBackdrop(guiGraphics, minecraft, x, y, width, minecraft.font.lineHeight, alpha);
    }

    /**
     * Draws a text backdrop with the game's background color and the given alpha.
     * Slightly modified copy of Gui#drawBackdrop so it can be used where that method is not available.
     */
    public static void drawBackdrop(GuiGraphics guiGraphics, Minecraft minecraft, int x, int y, int width, int height, int alpha) {
        int backgroundColor = minecraft.options.getBackgroundColor(0.0F);
        if (backgroundColor != 0) {
            int argb = 0xFFFFFF | (alpha << 24);
            guiGraphics.fill(x - 2, y - 2, x + width + 2, y + height + 2, multiplyArgb(backgroundColor, argb));
        }
    }

    /** Component-wise ARGB multiply (each channel 0..255). */
    private static int multiplyArgb(int c1, int c2) {
        int a = ((c1 >> 24) & 0xFF) * ((c2 >> 24) & 0xFF) / 255;
        int r = ((c1 >> 16) & 0xFF) * ((c2 >> 16) & 0xFF) / 255;
        int g = ((c1 >> 8) & 0xFF) * ((c2 >> 8) & 0xFF) / 255;
        int b = (c1 & 0xFF) * (c2 & 0xFF) / 255;
        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    // ---------- Tank bar / tiled sprite (Mekanism-style) ----------

    /**
     * Direction for tiling when the fill does not align to texture size
     * (e.g. fill from top-left vs bottom-right).
     */
    public enum TilingDirection {
        /** Tile from top-left to bottom-right. */
        DOWN_RIGHT(true, true),
        /** Tile from top-right to bottom-left. */
        DOWN_LEFT(true, false),
        /** Tile from bottom-left to top-right. */
        UP_RIGHT(false, true),
        /** Tile from bottom-right to top-left. */
        UP_LEFT(false, false);

        private final boolean down;
        private final boolean right;

        TilingDirection(boolean down, boolean right) {
            this.down = down;
            this.right = right;
        }
    }

    /**
     * Draws a tiled sprite in the given rectangle (e.g. fluid in a tank bar).
     * Tiles the sprite to fill the area; partial tiles at edges use the correct UV.
     * Uses DOWN_RIGHT for fill-from-top-left (e.g. vertical tank from bottom).
     */
    public static void drawTiledSprite(GuiGraphics guiGraphics, int x, int y, int width, int height,
            TextureAtlasSprite sprite, int spriteWidth, int spriteHeight, TilingDirection direction) {
        if (width <= 0 || height <= 0 || spriteWidth <= 0 || spriteHeight <= 0) return;
        float uMin = sprite.getU0();
        float uMax = sprite.getU1();
        float vMin = sprite.getV0();
        float vMax = sprite.getV1();
        float uSpan = uMax - uMin;
        float vSpan = vMax - vMin;
        for (int iy = 0; iy < height; iy += spriteHeight) {
            int drawH = Math.min(spriteHeight, height - iy);
            float v0 = vMin;
            float v1 = vMin + (float) drawH / spriteHeight * vSpan;
            int destY = direction.down ? y + iy : y + height - iy - drawH;
            for (int ix = 0; ix < width; ix += spriteWidth) {
                int drawW = Math.min(spriteWidth, width - ix);
                float u0 = uMin;
                float u1 = uMin + (float) drawW / spriteWidth * uSpan;
                int destX = direction.right ? x + ix : x + width - ix - drawW;
                guiGraphics.blit(sprite.atlasLocation(), destX, destY, drawW, drawH, u0, v0, u1, v1);
            }
        }
    }
}
