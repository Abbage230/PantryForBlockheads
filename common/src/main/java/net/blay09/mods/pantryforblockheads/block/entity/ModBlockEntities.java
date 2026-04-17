package net.blay09.mods.pantryforblockheads.block.entity;

import net.blay09.mods.balm.world.level.block.entity.BalmBlockEntityTypeRegistrar;
import net.blay09.mods.balm.world.level.block.entity.BalmBlockEntityTypeRegistration;
import net.blay09.mods.pantryforblockheads.PantryForBlockheads;

public class ModBlockEntities {

    public static BalmBlockEntityTypeRegistration<ArtisanPressBlockEntity> artisanPress;

    public static void initialize(BalmBlockEntityTypeRegistrar blockEntities) {
        artisanPress = blockEntities.register("artisan_press", ArtisanPressBlockEntity::new, PantryForBlockheads.blocks().artisanPress);
    }
}
