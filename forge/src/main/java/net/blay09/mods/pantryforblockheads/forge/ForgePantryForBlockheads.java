package net.blay09.mods.pantryforblockheads.forge;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.client.BalmClient;
import net.blay09.mods.balm.forge.platform.runtime.ForgeLoadContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.blay09.mods.pantryforblockheads.PantryForBlockheads;
import net.blay09.mods.pantryforblockheads.client.PantryForBlockheadsClient;

@Mod(PantryForBlockheads.MOD_ID)
public class ForgePantryForBlockheads {

    public ForgePantryForBlockheads(FMLJavaModLoadingContext context) {
        final var loadContext = new ForgeLoadContext(context.getModBusGroup());
        Balm.initializeMod(PantryForBlockheads.MOD_ID, loadContext, PantryForBlockheads::initialize);
        if (FMLEnvironment.dist.isClient()) {
            BalmClient.initializeMod(PantryForBlockheads.MOD_ID, loadContext, PantryForBlockheadsClient::initialize);
        }
    }

}
