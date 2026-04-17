package net.blay09.mods.pantryforblockheads.client;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import static net.blay09.mods.pantryforblockheads.PantryForBlockheads.id;

public class FortuneOverlay {
    private static final Identifier TEXTURE = id("textures/gui/fortune_cookie.png");
    private static final int TEXTURE_WIDTH = 256;
    private static final int TEXTURE_HEIGHT = 64;
    private static final int DISPLAY_TICKS = 100;
    private static final int MAX_TEXT_WIDTH = 222;
    private static final int TEXT_COLOR = 0xFF3A2615;

    private static Component message = Component.empty();
    private static int remainingTicks;

    public static void show(Component message) {
        FortuneOverlay.message = message;
        remainingTicks = DISPLAY_TICKS;
    }

    public static void tick(Minecraft client) {
        if (remainingTicks > 0 && !client.isPaused()) {
            remainingTicks--;
        }
    }

    public static void render(GuiGraphicsExtractor graphics, Window window) {
        if (remainingTicks <= 0 || message.getString().isBlank()) {
            return;
        }

        final var minecraft = Minecraft.getInstance();
        final var font = minecraft.font;
        final int x = window.getGuiScaledWidth() / 2 - TEXTURE_WIDTH / 2;
        final int y = Math.max(24, window.getGuiScaledHeight() / 5);

        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT, TEXTURE_WIDTH, TEXTURE_HEIGHT);

        final var lines = font.split(message, MAX_TEXT_WIDTH);
        int textY = y + TEXTURE_HEIGHT / 2 - (lines.size() * font.lineHeight / 2) - font.lineHeight + 2;
        final int textCenterX = x + TEXTURE_WIDTH / 2;
        for (final var line : lines) {
            final var textWidth = font.width(line);
            graphics.text(font, line, textCenterX - textWidth / 2, textY, TEXT_COLOR, false);
            textY += font.lineHeight;
        }
    }

}
