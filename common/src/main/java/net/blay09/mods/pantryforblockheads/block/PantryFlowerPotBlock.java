package net.blay09.mods.pantryforblockheads.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;

public class PantryFlowerPotBlock extends FlowerPotBlock {

    public static final MapCodec<FlowerPotBlock> CODEC = RecordCodecBuilder.mapCodec((i) -> i.group(BuiltInRegistries.BLOCK.byNameCodec().fieldOf("potted").forGetter(FlowerPotBlock::getPotted), propertiesCodec()).apply(i, PantryFlowerPotBlock::new));

    public PantryFlowerPotBlock(Block potted, Properties properties) {
        super(potted, properties);
    }

    @Override
    public MapCodec<FlowerPotBlock> codec() {
        return CODEC;
    }
}
