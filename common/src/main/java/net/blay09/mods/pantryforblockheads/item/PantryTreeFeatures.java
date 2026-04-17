package net.blay09.mods.pantryforblockheads.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import static net.blay09.mods.pantryforblockheads.PantryForBlockheads.id;

public class PantryTreeFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEMON = ResourceKey.create(Registries.CONFIGURED_FEATURE, id("lemon"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> PEACH = ResourceKey.create(Registries.CONFIGURED_FEATURE, id("peach"));
}
