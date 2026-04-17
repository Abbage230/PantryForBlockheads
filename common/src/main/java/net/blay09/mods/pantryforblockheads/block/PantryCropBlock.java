package net.blay09.mods.pantryforblockheads.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.blay09.mods.pantryforblockheads.item.CropType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class PantryCropBlock extends CropBlock {
    public static final MapCodec<PantryCropBlock> CODEC = RecordCodecBuilder.mapCodec((it) -> it.group(
                    CropType.CODEC.fieldOf("type").forGetter(PantryCropBlock::getCropType), propertiesCodec())
            .apply(it, PantryCropBlock::new));

    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private final CropType cropType;

    protected PantryCropBlock(CropType cropType, Properties properties) {
        this.cropType = cropType;
        super(properties);
    }

    public CropType getCropType() {
        return cropType;
    }

    @Override
    public MapCodec<? extends CropBlock> codec() {
        return CODEC;
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return cropType.ageProperty();
    }

    @Override
    public int getMaxAge() {
        return cropType.maxAge();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(getAgeProperty());
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return asItem();
    }
}
