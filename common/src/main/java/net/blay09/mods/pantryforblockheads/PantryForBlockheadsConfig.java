package net.blay09.mods.pantryforblockheads;

import net.blay09.mods.balm.platform.config.reflection.Comment;
import net.blay09.mods.balm.platform.config.reflection.Config;
import net.blay09.mods.balm.platform.config.reflection.IgnoreConfig;

@Config(PantryForBlockheads.MOD_ID)
public class PantryForBlockheadsConfig {

    public FoodEffects foodEffects = new FoodEffects();
    public VanillaModifications vanillaModifications = new VanillaModifications();

    public static class FoodEffects {
        @Comment("Whether fortune cookies grant random effects, with a cooldown.")
        public boolean fortuneCookies = true;

        @Comment("Whether pink donuts with sprinkles grant temporary speed buffs.")
        public boolean pinkDonutWithSprinkles = true;

        @Comment("Whether certain foods grant Well Fed absorption effects when overflowing the hunger bar.")
        public boolean excessNutritionGrantsAbsorption = true;
    }

    public static class VanillaModifications {
        @Comment("Whether seeds from this mod generate within chest loot.")
        public boolean chestLootHoldsBlockheadSeeds = true;

        @IgnoreConfig("I don't think this is a good option to have. It will make people dislike the mod.")
        @Comment("Whether seeds from this mod drop when breaking grass.")
        public boolean grassDropsBlockheadSeeds = false;

        @SuppressWarnings("unused") // referenced in data pack
        @Comment("Whether trees from this mod generate in biomes with the respective `pantryforblockheads:has_feature/*` tags.")
        public boolean generateBlockheadTrees = true;

        @SuppressWarnings("unused") // referenced in data pack
        @Comment("Whether bushes from this mod generate in biomes with the respective `pantryforblockheads:has_feature/*` tags.")
        public boolean generateBlockheadBushes = true;

        @Comment("Whether Sweet Berry Bushes are prevented from slowing and damaging the player.")
        public boolean removeSweetBerryBushDamage = true;
    }

}
