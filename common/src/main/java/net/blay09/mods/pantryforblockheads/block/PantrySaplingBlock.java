package net.blay09.mods.pantryforblockheads.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.blay09.mods.pantryforblockheads.item.TreeType;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;

public class PantrySaplingBlock extends SaplingBlock {
    public static final MapCodec<PantrySaplingBlock> CODEC = RecordCodecBuilder.mapCodec((it) -> it.group(
                    TreeType.CODEC.fieldOf("type").forGetter(PantrySaplingBlock::getTreeType), propertiesCodec())
            .apply(it, PantrySaplingBlock::new));

    private final TreeType treeType;

    protected PantrySaplingBlock(TreeType treeType, Properties properties) {
        super(treeType.treeGrower(), properties);
        this.treeType = treeType;
    }

    public TreeType getTreeType() {
        return treeType;
    }

    @Override
    public MapCodec<? extends SaplingBlock> codec() {
        return CODEC;
    }
}
