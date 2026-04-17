package net.blay09.mods.pantryforblockheads.client;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.client.BalmClientRegistrars;
import net.blay09.mods.balm.client.platform.event.callback.ClientTickCallback;
import net.blay09.mods.balm.client.platform.event.callback.ClientLifecycleCallback;
import net.blay09.mods.balm.client.platform.event.callback.RenderCallback;
import net.blay09.mods.pantryforblockheads.PantryForBlockheads;
import net.blay09.mods.pantryforblockheads.block.entity.ModBlockEntities;
import net.blay09.mods.pantryforblockheads.compat.recipeviewers.PantryRecipeViewerProvider;
import net.blay09.mods.pantryforblockheads.client.render.ArtisanPressBlockEntityRenderer;
import net.blay09.mods.pantryforblockheads.client.screen.ArtisanPressScreen;
import net.blay09.mods.pantryforblockheads.menu.ModMenus;

public class PantryForBlockheadsClient {

    public static void initialize(BalmClientRegistrars registrars) {
        registrars.menuScreens(it -> it.register(ModMenus.artisanPress.asHolder(), ArtisanPressScreen::new));
        registrars.blockEntityRenderers(it -> it.register(ModBlockEntities.artisanPress.asHolder(), ArtisanPressBlockEntityRenderer::new));
        Balm.modSupport().recipeViewers().register(PantryForBlockheads.id("recipes"), new PantryRecipeViewerProvider());
        ModKeyMappings.initialize();
        ClientTickCallback.AFTER.register(FortuneOverlay::tick);
        RenderCallback.Gui.AFTER.register(FortuneOverlay::render);

        ClientLifecycleCallback.Started.EVENT.register(client -> {
            PantryForBlockheads.items().postInitialize();
        });
    }

}
