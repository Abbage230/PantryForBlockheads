package net.blay09.mods.pantryforblockheads.network;

import net.blay09.mods.pantryforblockheads.PantryForBlockheads;
import net.blay09.mods.pantryforblockheads.client.FortuneOverlay;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;

public record ShowFortuneMessagePayload(Component message) implements CustomPacketPayload {

    public static final Type<ShowFortuneMessagePayload> TYPE = new Type<>(PantryForBlockheads.id("show_fortune_message"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ShowFortuneMessagePayload> STREAM_CODEC = StreamCodec.composite(
            ComponentSerialization.TRUSTED_STREAM_CODEC,
            ShowFortuneMessagePayload::message,
            ShowFortuneMessagePayload::new
    );

    public static void handle(Player player, ShowFortuneMessagePayload payload) {
        FortuneOverlay.show(payload.message());
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

}
