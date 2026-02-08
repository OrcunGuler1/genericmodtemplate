package com.example.commontransports.network;

import com.example.commontransports.GenericMod;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

/**
 * Client -> server payload for refinery GUI actions (e.g. flush input/output tank).
 * Inspired by AdvancedAE's client actions (e.g. ReactionChamberMenu FLUSH_FLUID).
 */
public record RefineryActionPayload(BlockPos blockPos, int actionId) implements CustomPacketPayload {

    public static final Identifier ID = Identifier.fromNamespaceAndPath(GenericMod.MODID, "refinery_action");
    public static final CustomPacketPayload.Type<RefineryActionPayload> TYPE = new CustomPacketPayload.Type<>(ID);

    public static final StreamCodec<FriendlyByteBuf, RefineryActionPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, p -> p.blockPos().getX(),
            ByteBufCodecs.INT, p -> p.blockPos().getY(),
            ByteBufCodecs.INT, p -> p.blockPos().getZ(),
            ByteBufCodecs.VAR_INT, RefineryActionPayload::actionId,
            (x, y, z, actionId) -> new RefineryActionPayload(new BlockPos(x, y, z), actionId)
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
