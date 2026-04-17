package net.blay09.mods.pantryforblockheads.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static net.blay09.mods.pantryforblockheads.PantryForBlockheads.id;

public class PantryTreePlacements {
    public static final ResourceKey<PlacedFeature> LEMON = ResourceKey.create(Registries.PLACED_FEATURE, id("trees_lemon"));
    public static final ResourceKey<PlacedFeature> PEACH = ResourceKey.create(Registries.PLACED_FEATURE, id("trees_peach"));
}
