package net.blay09.mods.pantryforblockheads.fabric.datagen;

import net.blay09.mods.pantryforblockheads.PantryForBlockheads;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;

import java.util.concurrent.CompletableFuture;

public class ModVillagerTradeProvider extends FabricDynamicRegistryProvider {
    public ModVillagerTradeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.VILLAGER_TRADE));
    }

    @Override
    public String getName() {
        return PantryForBlockheads.MOD_ID + " villager trades";
    }
}
