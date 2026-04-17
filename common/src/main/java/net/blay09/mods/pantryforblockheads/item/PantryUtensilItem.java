package net.blay09.mods.pantryforblockheads.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;

public class PantryUtensilItem extends Item {
    public PantryUtensilItem(Properties properties) {
        super(properties);
    }

    public void postInitialize() {
        craftingRemainingItem = new ItemStackTemplate(this);
    }
}
