package net.blay09.mods.pantryforblockheads.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;

import java.util.List;

public record ArtisanPressRecipe(
        Ingredient ingredient,
        List<ItemStackTemplate> results
) implements Recipe<SingleRecipeInput> {

    private static final MapCodec<ArtisanPressRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Ingredient.CODEC.fieldOf("ingredient").forGetter(ArtisanPressRecipe::ingredient),
            ItemStackTemplate.CODEC.listOf(1, 2).fieldOf("results").forGetter(ArtisanPressRecipe::results)
    ).apply(instance, ArtisanPressRecipe::new));

    private static final StreamCodec<RegistryFriendlyByteBuf, ArtisanPressRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC,
            ArtisanPressRecipe::ingredient,
            ItemStackTemplate.STREAM_CODEC.apply(ByteBufCodecs.list()),
            ArtisanPressRecipe::results,
            ArtisanPressRecipe::new
    );

    @Override
    public boolean matches(SingleRecipeInput recipeInput, Level level) {
        return ingredient.test(recipeInput.item());
    }

    @Override
    public ItemStack assemble(SingleRecipeInput recipeInput) {
        return results.getFirst().create();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public RecipeSerializer<ArtisanPressRecipe> getSerializer() {
        return ModRecipes.artisanPressRecipes.serializer();
    }

    @Override
    public RecipeType<ArtisanPressRecipe> getType() {
        return ModRecipes.artisanPressRecipes.type();
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.create(ingredient);
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return ModRecipes.artisanPressRecipes.bookCategory();
    }

    public static RecipeSerializer<ArtisanPressRecipe> serializer() {
        return new RecipeSerializer<>(CODEC, STREAM_CODEC);
    }
}
