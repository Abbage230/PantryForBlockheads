package net.blay09.mods.pantryforblockheads.item;

import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class PantryTreeGrowers {
    public static final TreeGrower LEMON = new TreeGrower("lemon", 0f, Optional.empty(), Optional.empty(), Optional.of(PantryTreeFeatures.LEMON), Optional.empty(), Optional.empty(), Optional.empty());
    public static final TreeGrower PEACH = new TreeGrower("peach", 0f, Optional.empty(), Optional.empty(), Optional.of(PantryTreeFeatures.PEACH), Optional.empty(), Optional.empty(), Optional.empty());
}
