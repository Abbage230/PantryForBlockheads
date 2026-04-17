package net.blay09.mods.pantryforblockheads.menu;

import net.blay09.mods.balm.world.BalmMenuFactory;
import net.blay09.mods.balm.world.inventory.BalmMenuTypeRegistrar;
import net.blay09.mods.balm.world.inventory.BalmMenuTypeRegistration;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.player.Inventory;

public class ModMenus {

    public static BalmMenuTypeRegistration<ArtisanPressMenu> artisanPress;

    public static void initialize(BalmMenuTypeRegistrar menuTypes) {
        artisanPress = menuTypes.register("artisan_press", new BalmMenuFactory<ArtisanPressMenu, Unit>() {
            @Override
            public ArtisanPressMenu create(int syncId, Inventory inventory, Unit unit) {
                return new ArtisanPressMenu(syncId, inventory);
            }

            @Override
            public StreamCodec<RegistryFriendlyByteBuf, Unit> getStreamCodec() {
                return Unit.STREAM_CODEC.cast();
            }
        });
    }
}
