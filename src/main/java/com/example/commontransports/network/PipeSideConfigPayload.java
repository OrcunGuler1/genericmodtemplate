package com.example.commontransports.network;

import com.example.commontransports.GenericMod;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

/**
 * Client -> server payload for basic fluid pipe side-mode changes.
 */
public record PipeSideConfigPayload(BlockPos blockPos, int sideOrdinal, int actionId) implements CustomPacketPayload {

    public static final int SIDE_ALL = -1;
    public static final int ACTION_CYCLE_NEXT = 0;
    public static final int ACTION_CYCLE_PREVIOUS = 1;
    public static final int ACTION_SET_DISABLED = 2;
    public static final int ACTION_CLEAR_ALL_SIDES = 3;

    public static final Identifier ID = Identifier.fromNamespaceAndPath(GenericMod.MODID, "pipe_side_config");
    public static final CustomPacketPayload.Type<PipeSideConfigPayload> TYPE = new CustomPacketPayload.Type<>(ID);

    public static final StreamCodec<FriendlyByteBuf, PipeSideConfigPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, p -> p.blockPos().getX(),
            ByteBufCodecs.INT, p -> p.blockPos().getY(),
            ByteBufCodecs.INT, p -> p.blockPos().getZ(),
            ByteBufCodecs.INT, PipeSideConfigPayload::sideOrdinal,
            ByteBufCodecs.VAR_INT, PipeSideConfigPayload::actionId,
            (x, y, z, sideOrdinal, actionId) -> new PipeSideConfigPayload(new BlockPos(x, y, z), sideOrdinal, actionId)
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
