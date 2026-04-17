package net.blay09.mods.pantryforblockheads.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import static net.blay09.mods.pantryforblockheads.PantryForBlockheads.id;

public class ModBiomeTags {
    public static final TagKey<Biome> HAS_LEMON_TREE = TagKey.create(Registries.BIOME, id("has_feature/lemon_tree"));
    public static final TagKey<Biome> HAS_PEACH_TREE = TagKey.create(Registries.BIOME, id("has_feature/peach_tree"));
    public static final TagKey<Biome> HAS_GRAPEVINE = TagKey.create(Registries.BIOME, id("has_feature/grapevine"));
    public static final TagKey<Biome> HAS_GRAPEVINE_RARE = TagKey.create(Registries.BIOME, id("has_feature/grapevine_rare"));
    public static final TagKey<Biome> HAS_BLUEBERRY_BUSH = TagKey.create(Registries.BIOME, id("has_feature/blueberry_bush"));
    public static final TagKey<Biome> HAS_BLUEBERRY_BUSH_RARE = TagKey.create(Registries.BIOME, id("has_feature/blueberry_bush_rare"));
}
