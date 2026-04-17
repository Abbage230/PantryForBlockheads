package net.blay09.mods.pantryforblockheads.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Locale;

import static net.blay09.mods.pantryforblockheads.PantryForBlockheads.id;

public enum BushType implements StringRepresentable {
    BLUEBERRIES("blueberry_bush", ResourceKey.create(Registries.LOOT_TABLE, id("harvest/blueberry_bush"))),
    GRAPES("grapevine", ResourceKey.create(Registries.LOOT_TABLE, id("harvest/grapevine")));

    public static final StringRepresentable.EnumCodec<BushType> CODEC = StringRepresentable.fromEnum(BushType::values);

    private final String bushName;
    private final ResourceKey<LootTable> harvestLootTable;

    BushType(String bushName, ResourceKey<LootTable> harvestLootTable) {
        this.bushName = bushName;
        this.harvestLootTable = harvestLootTable;
    }

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }

    @Override
    public String toString() {
        return getSerializedName();
    }

    public FoodProperties foodProperties() {
        return new FoodProperties.Builder().nutrition(1).saturationModifier(1f).build();
    }

    public String bushName() {
        return bushName;
    }

    public ResourceKey<LootTable> harvestLootTable() {
        return harvestLootTable;
    }
}
