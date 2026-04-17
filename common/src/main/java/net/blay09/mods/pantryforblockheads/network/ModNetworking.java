package net.blay09.mods.pantryforblockheads.network;

import net.blay09.mods.balm.network.BalmNetworking;

public class ModNetworking {

    public static void initialize(BalmNetworking networking) {
        networking.registerClientboundPacket(ShowFortuneMessagePayload.TYPE,
                ShowFortuneMessagePayload.class,
                ShowFortuneMessagePayload.STREAM_CODEC,
                ShowFortuneMessagePayload::handle);
    }

}
