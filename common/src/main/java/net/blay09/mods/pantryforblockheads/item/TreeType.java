package net.blay09.mods.pantryforblockheads.item;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Locale;

public enum TreeType implements StringRepresentable {
    LEMON(PantryTreeGrowers.LEMON),
    PEACH(PantryTreeGrowers.PEACH);

    public static final StringRepresentable.EnumCodec<TreeType> CODEC = StringRepresentable.fromEnum(TreeType::values);

    private final TreeGrower treeGrower;

    TreeType(TreeGrower treeGrower) {
        this.treeGrower = treeGrower;
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

    public TreeGrower treeGrower() {
        return treeGrower;
    }
}
