package net.blay09.mods.pantryforblockheads.fabric.datagen;

import net.blay09.mods.balm.world.item.DeferredItem;
import net.blay09.mods.balm.world.level.block.DeferredBlock;
import net.blay09.mods.pantryforblockheads.PantryForBlockheads;
import net.blay09.mods.pantryforblockheads.block.ArtisanPressBlock;
import net.blay09.mods.pantryforblockheads.block.PantryBushBlock;
import net.blay09.mods.pantryforblockheads.block.PantryLeavesBlock;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

import java.util.stream.IntStream;

import static net.minecraft.client.data.models.BlockModelGenerators.*;
import static net.minecraft.client.data.models.BlockModelGenerators.NOP;
import static net.minecraft.client.data.models.BlockModelGenerators.Y_ROT_270;
import static net.minecraft.client.data.models.model.TextureMapping.getBlockTexture;

public class ModModelProvider extends FabricModelProvider {

    private static final PropertyDispatch<VariantMutator> ROTATION_HORIZONTAL_FACING = PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
            .select(Direction.EAST, Y_ROT_90)
            .select(Direction.SOUTH, Y_ROT_180)
            .select(Direction.WEST, Y_ROT_270)
            .select(Direction.NORTH, NOP);

    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        final var blocks = PantryForBlockheads.blocks();
        blocks.bushes.values().stream().map(DeferredBlock::asBlock)
                .forEach(block -> blockStateModelGenerator.createCropBlock(block, PantryBushBlock.AGE, 0, 1, 2, 3));
        blocks.crops.forEach((type, block) -> {
            final int[] stages = IntStream.range(0, type.maxAge() + 1).toArray();
            blockStateModelGenerator.createCropBlock(block.asBlock(), type.ageProperty(), stages);
        });
        blocks.saplings.forEach((type, block) -> blockStateModelGenerator.createPlantWithDefaultItem(block.asBlock(), blocks.pottedSaplings.get(type).asBlock(), BlockModelGenerators.PlantType.NOT_TINTED));
        blocks.leaves.forEach((_, block) -> {
            final var leavesBlock = (PantryLeavesBlock) block.asBlock();
            final var blockModel = TexturedModel.LEAVES.create(leavesBlock, blockStateModelGenerator.modelOutput);
            final var fruitsBlockModel = TexturedModel.LEAVES
                    .updateTexture(mapping -> mapping.put(TextureSlot.ALL, getBlockTexture(leavesBlock, "_fruits")))
                    .createWithSuffix(leavesBlock, "_fruits", blockStateModelGenerator.modelOutput);
            blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.dispatch(leavesBlock)
                    .with(PropertyDispatch.initial(leavesBlock.getAgeProperty())
                            .select(0, plainVariant(blockModel))
                            .select(1, plainVariant(blockModel))
                            .select(2, plainVariant(blockModel))
                            .select(3, plainVariant(fruitsBlockModel))));
            blockStateModelGenerator.registerSimpleItemModel(leavesBlock, fruitsBlockModel);
        });

        final var artisanPress = blocks.artisanPress.asBlock();
        final var artisanPressUpper = ModelLocationUtils.getModelLocation(artisanPress, "_upper");
        final var artisanPressLower = ModelLocationUtils.getModelLocation(artisanPress, "_lower");
        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.dispatch(artisanPress)
                .with(PropertyDispatch.initial(ArtisanPressBlock.HALF)
                        .select(DoubleBlockHalf.UPPER, plainVariant(artisanPressUpper))
                        .select(DoubleBlockHalf.LOWER, plainVariant(artisanPressLower)))
                .with(ROTATION_HORIZONTAL_FACING));
        blockStateModelGenerator.registerSimpleItemModel(artisanPress, ModelLocationUtils.getModelLocation(artisanPress.asItem()));
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        final var items = PantryForBlockheads.items();
        itemModelGenerator.generateFlatItem(items.magicSprinkles.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(items.pot.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.declareCustomModelItem(items.fryingPan.asItem());
        itemModelGenerator.generateFlatItem(items.mixingBowl.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(items.bakingSheet.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.declareCustomModelItem(items.knife.asItem());
        items.crops.values().stream().map(DeferredItem::asItem).forEach(item -> itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM));
        items.fruits.values().stream().map(DeferredItem::asItem).forEach(item -> itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM));
        items.meals.values().stream().map(DeferredItem::asItem).forEach(item -> itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM));
    }

}
