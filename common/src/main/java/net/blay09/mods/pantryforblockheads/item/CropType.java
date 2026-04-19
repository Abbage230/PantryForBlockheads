package net.blay09.mods.pantryforblockheads.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public final class CropType implements StringRepresentable, Comparable<CropType> {
    private static final IntegerProperty DEFAULT_AGE_PROPERTY = BlockStateProperties.AGE_3;
    private static final int DEFAULT_MAX_AGE = 3;
    private static final Map<String, CropType> REGISTRY = new LinkedHashMap<>();

    public static final CropType BELL_PEPPER = register("bell_pepper", "bell_peppers");
    public static final CropType BROCCOLI = register("broccoli", "broccoli");
    public static final CropType CAULIFLOWER = register("cauliflower", "cauliflower");
    public static final CropType CHILI_PEPPER = register("chili_pepper", "chilli_peppers");
    public static final CropType CORN = register("corn", "corn");
    public static final CropType EGGPLANT = register("eggplant", "eggplants");
    public static final CropType LETTUCE = register("lettuce", "lettuce");
    public static final CropType ONION = register("onion", "onions");
    public static final CropType PEANUT = register("peanut", "peanuts");
    public static final CropType STRAWBERRY = register("strawberry", "strawberries");
    public static final CropType TOMATO = register("tomato", "tomatoes");
    public static final CropType TURNIP = register("turnip", "turnips");
    public static final CropType RICE = register("rice", "rice");
    public static final CropType SOYBEAN = register("soybean", "soybeans");

    public static final Codec<CropType> CODEC = Codec.STRING.comapFlatMap(serializedName -> {
        final var cropType = byName(serializedName);
        return cropType != null ? DataResult.success(cropType) : DataResult.error(() -> "Unknown CropType: " + serializedName);
    }, CropType::getSerializedName);

    private final String serializedName;
    private final String plural;
    private final IntegerProperty ageProperty;
    private final int maxAge;
    private Function<Item.Properties, Item.Properties> propertiesBuilder;

    private CropType(String serializedName, String plural, IntegerProperty ageProperty, int maxAge) {
        this.serializedName = serializedName;
        this.plural = plural;
        this.ageProperty = ageProperty;
        this.maxAge = maxAge;
        propertiesBuilder = it -> it.food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).build());
    }

    private static CropType register(String serializedName, String plural) {
        return register(serializedName, plural, DEFAULT_AGE_PROPERTY, DEFAULT_MAX_AGE);
    }

    private static CropType register(String serializedName, String plural, IntegerProperty ageProperty, int maxAge) {
        final var cropType = new CropType(serializedName, plural, ageProperty, maxAge);
        final var previous = REGISTRY.putIfAbsent(serializedName, cropType);
        if (previous != null) {
            throw new IllegalStateException("Duplicate CropType registration: " + serializedName);
        }
        return cropType;
    }

    public CropType overrideProperties(Function<Item.Properties, Item.Properties> propertiesBuilder) {
        this.propertiesBuilder = propertiesBuilder;
        return this;
    }

    public static Set<CropType> values() {
        return Collections.unmodifiableSet(new LinkedHashSet<>(REGISTRY.values()));
    }

    public static @Nullable CropType byName(String serializedName) {
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

    public String plural() {
        return plural;
    }

    public IntegerProperty ageProperty() {
        return ageProperty;
    }

    public int maxAge() {
        return maxAge;
    }

    @Override
    public int compareTo(CropType o) {
        return serializedName.compareTo(o.serializedName);
    }
}
