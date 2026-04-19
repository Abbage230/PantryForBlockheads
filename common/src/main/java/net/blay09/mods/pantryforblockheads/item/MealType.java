package net.blay09.mods.pantryforblockheads.item;

import net.blay09.mods.pantryforblockheads.core.component.FortuneCookie;
import net.blay09.mods.pantryforblockheads.core.component.PinkDonutWithSprinkles;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumables;

import java.util.*;
import java.util.function.Function;

public final class MealType implements StringRepresentable, Comparable<MealType> {
    private static final Map<String, MealType> REGISTRY = new LinkedHashMap<>();

    public static final MealType BACON = register("bacon", 6, 0.5f);
    public static final MealType BAGEL = register("bagel", 4, 0.2f);
    public static final MealType BERRY_MUFFIN = register("berry_muffin", 3, 0.125f);
    public static final MealType BREADSTICK = register("breadstick", 1, 0.2f);
    public static final MealType BURGER = register("burger", 14, 1.4f);
    public static final MealType BURRITO = register("burrito", 10, 1f);
    public static final MealType CHEESE = register("cheese", 3, 0.1f);
    public static final MealType CHEESE_PIZZA = register("cheese_pizza", 9, 0.8f);
    public static final MealType CHEESE_PIZZA_SLICE = register("cheese_pizza_slice", 1, 0.2f)
            .overrideProperties(it -> it.food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.25f).build(), Consumables.defaultFood().consumeSeconds(0.8f).build()));
    public static final MealType CHICKEN_NUGGETS = register("chicken_nuggets", 1, 0.2f)
            .overrideProperties(it -> it.food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.2f).build(), Consumables.defaultFood().consumeSeconds(0.8f).build()));
    public static final MealType CHOCOLATE = register("chocolate", 1, 0.1f)
            .overrideProperties(it -> it.food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).build(), Consumables.defaultFood().consumeSeconds(0.8f).build()));
    public static final MealType CHOCOLATE_CUPCAKE = register("chocolate_cupcake", 4, 0.3f);
    public static final MealType CHOCOLATE_DOUGHNUT = register("chocolate_doughnut", 8, 0.5f);
    public static final MealType CHOCOLATE_ICECREAM = register("chocolate_icecream", 2, 0.4f);
    public static final MealType CROISSANT = register("croissant", 5, 0.6f);
    public static final MealType CUPCAKE = register("cupcake", 3, 0.2f);
    public static final MealType DUMPLING = register("dumpling", 4, 0.5f);
    public static final MealType FALAFEL = register("falafel", 3, 0.3f);
    public static final MealType FISH_FILLET = register("fish_fillet", 3, 0.3f);
    public static final MealType FISH_STICKS = register("fish_sticks", 3, 0.4f);
    public static final MealType FLATBREAD = register("flatbread", 6, 0.6f);
    public static final MealType FORTUNE_COOKIE = register("fortune_cookie", 2, 0.1f)
            .overrideProperties(it -> it.food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).alwaysEdible().build()).component(FortuneCookie.type(), FortuneCookie.INSTANCE));
    public static final MealType FRIED_EGG = register("fried_egg", 1, 0.1f)
            .overrideProperties(it -> it.food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).build(), Consumables.defaultFood().consumeSeconds(0.8f).build()));
    public static final MealType HOTDOG = register("hotdog", 8, 0.8f);
    public static final MealType ICECREAM = register("icecream", 2, 0.35f);
    public static final MealType INARI_SUSHI = register("inari_sushi", 3, 0.2f);
    public static final MealType MAKI_SUSHI = register("maki_sushi", 4, 0.5f);
    public static final MealType MASHED_PRODUCE = register("mashed_produce", 1, 0)
            .overrideProperties(it -> it.food(new FoodProperties.Builder().nutrition(1).saturationModifier(0f).build(), Consumables.defaultFood().consumeSeconds(0.8f).build()));
    public static final MealType MUFFIN = register("muffin", 2, 0.1f);
    public static final MealType NIGIRI_SUSHI = register("nigiri_sushi", 3, 0.2f);
    public static final MealType ONION_RINGS = register("onion_rings", 2, 0.2f);
    public static final MealType PANCAKES = register("pancakes", 5, 0.25f);
    public static final MealType PEPPERONI_PIZZA = register("pepperoni_pizza", 12, 1.05f);
    public static final MealType PEPPERONI_PIZZA_SLICE = register("pepperoni_pizza_slice", 1, 0.25f)
            .overrideProperties(it -> it.food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.25f).build(), Consumables.defaultFood().consumeSeconds(0.8f).build()));
    public static final MealType PINK_DOUGHNUT = register("pink_doughnut", 5, 0.25f);
    public static final MealType PINK_DOUGHNUT_SPRINKLES = register("pink_doughnut_sprinkles", 5, 0.25f)
            .overrideProperties(it -> it.food(new FoodProperties.Builder().nutrition(5).saturationModifier(0.25f).build()).component(PinkDonutWithSprinkles.type(), PinkDonutWithSprinkles.INSTANCE));
    public static final MealType POPCORN = register("popcorn", 5, 0.6f);
    public static final MealType PRETZEL = register("pretzel", 8, 1f);
    public static final MealType RICE_BALL = register("rice_ball", 2, 0.4f);
    public static final MealType RICE_CRACKER = register("rice_cracker", 1, 0f)
            .overrideProperties(it -> it.food(new FoodProperties.Builder().nutrition(1).saturationModifier(0f).build(), Consumables.defaultFood().consumeSeconds(0.8f).build()));
    public static final MealType SANDWICH = register("sandwich", 9, 0.9f);
    public static final MealType SAUSAGE = register("sausage", 3, 0.25f);
    public static final MealType TACO = register("taco", 9, 0.9f);
    public static final MealType TOFU = register("tofu", 3, 0.2f);
    public static final MealType VEGETABLE_PIZZA = register("vegetable_pizza", 10, 0.9f);
    public static final MealType VEGETABLE_PIZZA_SLICE = register("vegetable_pizza_slice", 1, 0.2f)
            .overrideProperties(it -> it.food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.2f).build(), Consumables.defaultFood().consumeSeconds(0.8f).build()));
    public static final MealType WAFFLE = register("waffle", 6, 0.3f);
    public static final MealType SOY_MILK = register("soy_milk", 2, 0.1f)
            .overrideProperties(it -> it.food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build(), Consumables.DEFAULT_DRINK).usingConvertsTo(Items.GLASS_BOTTLE));

    private final String serializedName;
    private final int nutrition;
    private final float saturation;
    private Function<Item.Properties, Item.Properties> propertiesBuilder;

    private MealType(String serializedName, int nutrition, float saturation) {
        this.serializedName = serializedName;
        this.nutrition = nutrition;
        this.saturation = saturation;
        propertiesBuilder = it -> it.food(new FoodProperties.Builder().nutrition(this.nutrition).saturationModifier(this.saturation).build());
    }

    public MealType overrideProperties(Function<Item.Properties, Item.Properties> propertiesBuilder) {
        this.propertiesBuilder = propertiesBuilder;
        return this;
    }

    private static MealType register(String serializedName, int nutrition, float saturation) {
        final var mealType = new MealType(serializedName, nutrition, saturation);
        final var previous = REGISTRY.putIfAbsent(serializedName, mealType);
        if (previous != null) {
            throw new IllegalStateException("Duplicate MealType registration: " + serializedName);
        }
        return mealType;
    }

    public static Set<MealType> values() {
        return Collections.unmodifiableSet(new LinkedHashSet<>(REGISTRY.values()));
    }

    public static MealType byName(String serializedName) {
        return REGISTRY.get(serializedName);
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }

    @Override
    public String toString() {
        return serializedName;
    }

    public Item.Properties applyProperties(Item.Properties it) {
        return propertiesBuilder.apply(it);
    }

    @Override
    public int compareTo(MealType o) {
        return serializedName.compareTo(o.serializedName);
    }
}
