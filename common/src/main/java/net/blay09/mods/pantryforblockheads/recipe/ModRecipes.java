package net.blay09.mods.pantryforblockheads.recipe;

import net.blay09.mods.balm.world.item.crafting.BalmRecipeTypeRegistrar;
import net.blay09.mods.balm.world.item.crafting.DeferredRecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;

public class ModRecipes {

    public static DeferredRecipeType<SingleRecipeInput, ArtisanPressRecipe> artisanPressRecipes;

    public static void initialize(BalmRecipeTypeRegistrar recipeTypes) {
        artisanPressRecipes = recipeTypes.register("artisan_press", ArtisanPressRecipe.class)
                .withSerializer(ArtisanPressRecipe::serializer)
                .withRecipeBookCategory()
                .asDeferredRecipeType();
    }
}
