package net.blay09.mods.pantryforblockheads.fabric;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.fabric.platform.runtime.FabricLoadContext;
import net.fabricmc.api.ModInitializer;
import net.blay09.mods.pantryforblockheads.PantryForBlockheads;

public class FabricPantryForBlockheads implements ModInitializer {
    @Override
    public void onInitialize() {
        Balm.initializeMod(PantryForBlockheads.MOD_ID, FabricLoadContext.INSTANCE, PantryForBlockheads::initialize);
    }
}
