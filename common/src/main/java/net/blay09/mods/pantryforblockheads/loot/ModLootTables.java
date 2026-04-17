package net.blay09.mods.pantryforblockheads.loot;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

import static net.blay09.mods.pantryforblockheads.PantryForBlockheads.id;

public final class ModLootTables {
    public static final ResourceKey<LootTable> GRASS_SEEDS = ResourceKey.create(Registries.LOOT_TABLE, id("gameplay/grass_seeds"));
    public static final ResourceKey<LootTable> CHEST_ABANDONED_MINESHAFT = ResourceKey.create(Registries.LOOT_TABLE, id("loot_additions/chest/abandoned_mineshaft"));
    public static final ResourceKey<LootTable> CHEST_SIMPLE_DUNGEON = ResourceKey.create(Registries.LOOT_TABLE, id("loot_additions/chest/simple_dungeon"));
    public static final ResourceKey<LootTable> CHESTS_VILLAGE_TAIGA_HOUSE = ResourceKey.create(Registries.LOOT_TABLE, id("loot_additions/chests/village/village_taiga_house"));
    public static final ResourceKey<LootTable> CHEST_WOODLAND_MANSION = ResourceKey.create(Registries.LOOT_TABLE, id("loot_additions/chest/woodland_mansion"));

    private ModLootTables() {
    }
}
