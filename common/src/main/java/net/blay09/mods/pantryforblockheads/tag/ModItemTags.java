package net.blay09.mods.pantryforblockheads.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static net.blay09.mods.pantryforblockheads.PantryForBlockheads.id;

public class ModItemTags {
    public static final TagKey<Item> ANY_EDIBLE_RAW_FISH = TagKey.create(Registries.ITEM, id("any_edible_raw_fish"));
    public static final TagKey<Item> ANY_SUSHI_FILLING = TagKey.create(Registries.ITEM, id("any_sushi_filling"));
    public static final TagKey<Item> ANY_DUMPLING_FILLING = TagKey.create(Registries.ITEM, id("any_dumpling_filling"));
    public static final TagKey<Item> ANY_PIZZA_VEGETABLE = TagKey.create(Registries.ITEM, id("any_pizza_vegetable"));
    public static final TagKey<Item> ANY_BERRY = TagKey.create(Registries.ITEM, id("any_berry"));
    public static final TagKey<Item> EXCESS_NUTRITION_GRANTS_ABSORPTION = TagKey.create(Registries.ITEM, id("excess_nutrition_grants_absorption"));
}
