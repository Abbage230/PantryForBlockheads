package net.blay09.mods.pantryforblockheads.client.screen;

import net.blay09.mods.pantryforblockheads.menu.ArtisanPressMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import static net.blay09.mods.pantryforblockheads.PantryForBlockheads.id;

public class ArtisanPressScreen extends AbstractContainerScreen<ArtisanPressMenu> {
    private static final Identifier TEXTURE = id("textures/gui/container/artisan_press.png");
    private static final Identifier PROGRESS_SPRITE = Identifier.withDefaultNamespace("pantryforblockheads/artisan_press_progress");

    public ArtisanPressScreen(ArtisanPressMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0f, 0f, imageWidth, imageHeight, 256, 256);

        final int progressHeight = menu.getScaledProgress(28);
        if (progressHeight > 0) {
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, PROGRESS_SPRITE, 9, 28, 0, 0, leftPos + 68, topPos + 20, 9, progressHeight);
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, PROGRESS_SPRITE, 9, 28, 0, 0, leftPos + 97, topPos + 20, 9, progressHeight);
        }
    }
}
