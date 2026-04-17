package net.blay09.mods.pantryforblockheads.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static net.blay09.mods.pantryforblockheads.PantryForBlockheads.id;

public class PantryBushPlacements {
    public static final ResourceKey<PlacedFeature> BLUEBERRY_BUSH_COMMON = ResourceKey.create(Registries.PLACED_FEATURE, id("patch_blueberry_common"));
    public static final ResourceKey<PlacedFeature> BLUEBERRY_BUSH_RARE = ResourceKey.create(Registries.PLACED_FEATURE, id("patch_blueberry_rare"));
    public static final ResourceKey<PlacedFeature> GRAPEVINE_COMMON = ResourceKey.create(Registries.PLACED_FEATURE, id("patch_grapevine_common"));
    public static final ResourceKey<PlacedFeature> GRAPEVINE_RARE = ResourceKey.create(Registries.PLACED_FEATURE, id("patch_grapevine_rare"));
}
