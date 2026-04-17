package net.blay09.mods.pantryforblockheads.neoforge;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.neoforge.platform.runtime.NeoForgeLoadContext;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.blay09.mods.pantryforblockheads.PantryForBlockheads;

@Mod(PantryForBlockheads.MOD_ID)
public class NeoForgePantryForBlockheads {

    public NeoForgePantryForBlockheads(ModContainer modContainer, IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modContainer, modEventBus);
        Balm.initializeMod(PantryForBlockheads.MOD_ID, context, PantryForBlockheads::initialize);
    }
}
