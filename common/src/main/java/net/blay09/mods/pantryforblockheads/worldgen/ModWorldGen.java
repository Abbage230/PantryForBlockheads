package net.blay09.mods.pantryforblockheads.worldgen;

import net.blay09.mods.balm.world.level.biome.BiomePredicate;
import net.blay09.mods.balm.world.level.levelgen.BalmWorldGen;
import net.blay09.mods.pantryforblockheads.item.PantryBushPlacements;
import net.blay09.mods.pantryforblockheads.item.PantryTreePlacements;
import net.blay09.mods.pantryforblockheads.tag.ModBiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;

import static net.blay09.mods.pantryforblockheads.PantryForBlockheads.id;

public class ModWorldGen {

    public static void initialize(BalmWorldGen worldGen) {
        worldGen.modifyBiome(
                id("add_lemon_tree"),
                matchesTag(ModBiomeTags.HAS_LEMON_TREE),
                (_, builder) -> builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PantryTreePlacements.LEMON));
        worldGen.modifyBiome(
                id("add_peach_tree"),
                matchesTag(ModBiomeTags.HAS_PEACH_TREE),
                (_, builder) -> builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PantryTreePlacements.PEACH));
        worldGen.modifyBiome(
                id("add_blueberry_bush"),
                matchesTag(ModBiomeTags.HAS_BLUEBERRY_BUSH),
                (_, builder) -> builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PantryBushPlacements.BLUEBERRY_BUSH_COMMON));
        worldGen.modifyBiome(
                id("add_blueberry_bush_rare"),
                matchesTag(ModBiomeTags.HAS_BLUEBERRY_BUSH_RARE),
                (_, builder) -> builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PantryBushPlacements.BLUEBERRY_BUSH_RARE));
        worldGen.modifyBiome(
                id("add_grapevine_bush"),
                matchesTag(ModBiomeTags.HAS_GRAPEVINE),
                (_, builder) -> builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PantryBushPlacements.GRAPEVINE_COMMON));
        worldGen.modifyBiome(
                id("add_grapevine_rare"),
                matchesTag(ModBiomeTags.HAS_GRAPEVINE_RARE),
                (_, builder) -> builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PantryBushPlacements.GRAPEVINE_RARE));
    }

    private static BiomePredicate matchesTag(TagKey<Biome> tag) {
        return biomeHolder -> biomeHolder.is(tag);
    }
}
