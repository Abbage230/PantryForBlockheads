package net.blay09.mods.pantryforblockheads.fabric.client;

import net.blay09.mods.balm.client.BalmClient;
import net.blay09.mods.balm.fabric.platform.runtime.FabricLoadContext;
import net.fabricmc.api.ClientModInitializer;
import net.blay09.mods.pantryforblockheads.PantryForBlockheads;
import net.blay09.mods.pantryforblockheads.client.PantryForBlockheadsClient;

public class FabricPantryForBlockheadsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BalmClient.initializeMod(PantryForBlockheads.MOD_ID, FabricLoadContext.INSTANCE, PantryForBlockheadsClient::initialize);
    }
}
