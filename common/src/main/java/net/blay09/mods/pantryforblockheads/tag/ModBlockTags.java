package net.blay09.mods.pantryforblockheads.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static net.blay09.mods.pantryforblockheads.PantryForBlockheads.id;

public class ModBlockTags {
    public static final TagKey<Block> BUSHES = TagKey.create(Registries.BLOCK, id("bushes"));
    public static final TagKey<Block> DROPS_SEEDS = TagKey.create(Registries.BLOCK, id("drops_seeds"));
}
