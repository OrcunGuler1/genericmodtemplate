package com.example.commontransports.network;

import com.example.commontransports.GenericMod;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

/**
 * Client -> server payload for generic processor GUI actions.
 */
public record ProcessingActionPayload(BlockPos blockPos, int actionId) implements CustomPacketPayload {

    public static final int ACTION_PICKUP_OUTPUT = 0;
    public static final int ACTION_EJECT_SPEED_UPGRADE = 1;
    public static final int ACTION_EJECT_EFFICIENCY_UPGRADE = 2;
    @Deprecated public static final int ACTION_UNINSTALL_SPEED_UPGRADE = ACTION_EJECT_SPEED_UPGRADE;
    @Deprecated public static final int ACTION_UNINSTALL_EFFICIENCY_UPGRADE = ACTION_EJECT_EFFICIENCY_UPGRADE;

    public static final Identifier ID = Identifier.fromNamespaceAndPath(GenericMod.MODID, "processing_action");
    public static final CustomPacketPayload.Type<ProcessingActionPayload> TYPE = new CustomPacketPayload.Type<>(ID);

    public static final StreamCodec<FriendlyByteBuf, ProcessingActionPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, p -> p.blockPos().getX(),
            ByteBufCodecs.INT, p -> p.blockPos().getY(),
            ByteBufCodecs.INT, p -> p.blockPos().getZ(),
            ByteBufCodecs.VAR_INT, ProcessingActionPayload::actionId,
            (x, y, z, actionId) -> new ProcessingActionPayload(new BlockPos(x, y, z), actionId)
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
