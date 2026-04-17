package net.blay09.mods.pantryforblockheads.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.platform.config.schema.ConfiguredBoolean;
import net.blay09.mods.pantryforblockheads.PantryForBlockheads;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.stream.Stream;

public class ConfigEnabledPlacementModifier extends PlacementModifier {

    public static final MapCodec<ConfigEnabledPlacementModifier> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Identifier.CODEC.fieldOf("schema").forGetter(ConfigEnabledPlacementModifier::schema),
            Codec.STRING.fieldOf("category").forGetter(ConfigEnabledPlacementModifier::category),
            Codec.STRING.fieldOf("property").forGetter(ConfigEnabledPlacementModifier::property)
    ).apply(instance, ConfigEnabledPlacementModifier::new));

    private final Identifier schema;
    private final String category;
    private final String property;

    public ConfigEnabledPlacementModifier(Identifier schema, String category, String property) {
        this.schema = schema;
        this.category = category;
        this.property = property;
    }

    @Override
    public Stream<BlockPos> getPositions(PlacementContext context, RandomSource random, BlockPos pos) {
        return isEnabled() ? Stream.of(pos) : Stream.empty();
    }

    @Override
    public PlacementModifierType<?> type() {
        return PantryForBlockheads.placementModifierTypes().CONFIG_ENABLED.value();
    }

    public Identifier schema() {
        return schema;
    }

    public String category() {
        return category;
    }

    public String property() {
        return property;
    }

    private boolean isEnabled() {
        final var configSchema = Balm.config().getSchema(schema);
        if (configSchema == null) {
            return false;
        }

        final var configuredProperty = category.isEmpty() ? configSchema.findRootProperty(property) : configSchema.findProperty(category, property);
        if (!(configuredProperty instanceof ConfiguredBoolean booleanProperty)) {
            return false;
        }

        final var loadedConfig = Balm.config().getActiveConfig(schema);
        if (loadedConfig == null) {
            return false;
        }

        return booleanProperty.get(loadedConfig);
    }

}
