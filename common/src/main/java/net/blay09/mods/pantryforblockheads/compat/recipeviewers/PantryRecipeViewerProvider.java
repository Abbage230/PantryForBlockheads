package net.blay09.mods.pantryforblockheads.compat.recipeviewers;

import net.blay09.mods.balm.platform.compatibility.recipeviewer.RecipeViewerInfoProvider;
import net.blay09.mods.balm.platform.compatibility.recipeviewer.RecipeViewerRegistrar;
import net.blay09.mods.pantryforblockheads.PantryForBlockheads;
import net.blay09.mods.pantryforblockheads.recipe.ArtisanPressRecipe;
import net.blay09.mods.pantryforblockheads.recipe.ModRecipes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import static net.blay09.mods.pantryforblockheads.PantryForBlockheads.id;

public class PantryRecipeViewerProvider implements RecipeViewerInfoProvider {

    private static final Identifier ARTISAN_PRESS_TEXTURE = id("textures/gui/jei/artisan_press.png");

    @Override
    public void initialize(RecipeViewerRegistrar registrar) {
        registrar.registerRecipeType(id("artisan_press"), ArtisanPressRecipe.class)
                .withSyncedRecipes(ModRecipes.artisanPressRecipes)
                .withCraftingStation(PantryForBlockheads.blocks().artisanPress)
                .buildDisplay(display -> display
                        .title(Component.translatable("block.pantryforblockheads.artisan_press"))
                        .icon(PantryForBlockheads.blocks().artisanPress)
                        .size(50, 47)
                        .background(ARTISAN_PRESS_TEXTURE)
                        .slots((recipe, slots) -> {
                            slots.inputSlot(17, 1).add(recipe.ingredient());
                            slots.outputSlot(1, 30).add(recipe.results().getFirst());

                            if (recipe.results().size() > 1) {
                                slots.outputSlot(33, 30).add(recipe.results().get(1));
                            }
                        }));
    }
}
