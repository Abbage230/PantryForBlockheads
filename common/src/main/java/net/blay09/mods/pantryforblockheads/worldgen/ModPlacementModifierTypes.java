package net.blay09.mods.pantryforblockheads.worldgen;

import net.blay09.mods.balm.core.BalmRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

public class ModPlacementModifierTypes {

    public final Holder<PlacementModifierType<?>> CONFIG_ENABLED;

    public ModPlacementModifierTypes(BalmRegistrar.Scoped<PlacementModifierType<?>> registrar) {
        CONFIG_ENABLED = registrar.register("config_enabled", _ -> (PlacementModifierType<ConfigEnabledPlacementModifier>) () -> ConfigEnabledPlacementModifier.CODEC);
    }

}
