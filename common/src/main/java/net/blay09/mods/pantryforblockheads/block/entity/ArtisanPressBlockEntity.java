package net.blay09.mods.pantryforblockheads.block.entity;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.world.*;
import net.blay09.mods.balm.world.level.block.entity.BalmBlockEntityUtils;
import net.blay09.mods.pantryforblockheads.menu.ArtisanPressMenu;
import net.blay09.mods.pantryforblockheads.recipe.ArtisanPressRecipe;
import net.blay09.mods.pantryforblockheads.recipe.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Unit;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class ArtisanPressBlockEntity extends BlockEntity implements BalmMenuProvider<Unit>, BalmContainerProvider {
    public static final int SLOT_COUNT = 3;
    public static final int DATA_COUNT = 2;
    public static final int DEFAULT_PROCESS_TIME = 140;

    private final DefaultContainer container = new DefaultContainer(SLOT_COUNT) {
        @Override
        public void setChanged() {
            ArtisanPressBlockEntity.this.setChanged();
            BalmBlockEntityUtils.sync(ArtisanPressBlockEntity.this);
        }

        @Override
        public boolean canTakeItem(Container into, int slot, ItemStack itemStack) {
            return outputContainer.containsOuterSlot(slot);
        }

        @Override
        public boolean canPlaceItem(int slot, ItemStack stack) {
            return inputContainer.containsOuterSlot(slot) && getMatchingRecipe(stack) != null;
        }
    };
    private final SubContainer inputContainer = new SubContainer(container, 0, 1);
    private final SubContainer outputContainer = new SubContainer(container, 1, 3);
    private int progress;
    private int maxProgress = DEFAULT_PROCESS_TIME;
    private boolean processing;
    private int animationTick;

    private final ContainerData containerData = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> progress;
                case 1 -> maxProgress;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> progress = value;
                case 1 -> maxProgress = value;
                default -> {
                }
            }
        }

        @Override
        public int getCount() {
            return DATA_COUNT;
        }
    };

    public ArtisanPressBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.artisanPress.asSupplier().get(), pos, state);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, ArtisanPressBlockEntity blockEntity) {
        final var recipe = blockEntity.getCurrentRecipe();
        if (recipe != null && blockEntity.canProcess(recipe.value())) {
            blockEntity.setProcessing(true);
            blockEntity.maxProgress = DEFAULT_PROCESS_TIME;
            blockEntity.progress++;
            blockEntity.setChanged();
            if (blockEntity.progress >= blockEntity.maxProgress) {
                blockEntity.craft(recipe.value());
            }
        } else {
            if (blockEntity.progress != 0) {
                blockEntity.progress = 0;
                blockEntity.setChanged();
            }
            blockEntity.setProcessing(false);
        }
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, ArtisanPressBlockEntity blockEntity) {
        if (blockEntity.processing) {
            blockEntity.animationTick++;
            if (ArtisanPressAnimation.isImpactTick(blockEntity.animationTick)) {
                blockEntity.spawnImpactParticles();
            }
        }
    }

    @Override
    public DefaultContainer getContainer() {
        return container;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.pantryforblockheads.artisan_press");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new ArtisanPressMenu(containerId, inventory, container, containerData, ContainerLevelAccess.create(level, worldPosition));
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        container.clearContent();
        ContainerHelper.loadAllItems(input, container.getItems());
        progress = input.getIntOr("progress", 0);
        maxProgress = input.getIntOr("max_progress", DEFAULT_PROCESS_TIME);
        processing = input.getBooleanOr("processing", false);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, container.getItems());
        output.putInt("progress", progress);
        output.putInt("max_progress", maxProgress);
        output.putBoolean("processing", processing);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return BalmBlockEntityUtils.createUpdatePacket(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return BalmBlockEntityUtils.createUpdateTag(registries, this::saveAdditional);
    }

    @Override
    public Unit getScreenOpeningData(ServerPlayer player) {
        return Unit.INSTANCE;
    }

    public ItemStack getInputItem() {
        return inputContainer.getItem(0);
    }

    public boolean isProcessing() {
        return processing;
    }

    public int getAnimationTick() {
        return animationTick;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, Unit> getScreenStreamCodec() {
        return Unit.STREAM_CODEC.cast();
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        if (level != null && !level.isClientSide()) {
            dropItems(level, pos);
        }
        super.preRemoveSideEffects(pos, state);
    }

    @Nullable
    private RecipeHolder<ArtisanPressRecipe> getCurrentRecipe() {
        return getMatchingRecipe(inputContainer.getItem(0));
    }

    @Nullable
    private RecipeHolder<ArtisanPressRecipe> getMatchingRecipe(ItemStack stack) {
        if (level == null || stack.isEmpty() || !(level.recipeAccess() instanceof RecipeManager recipeManager)) {
            return null;
        }

        return recipeManager.getRecipeFor(ModRecipes.artisanPressRecipes.type(), new SingleRecipeInput(stack), level).orElse(null);
    }

    private boolean canProcess(ArtisanPressRecipe recipe) {
        return canOutputResults(outputContainer, recipe.results(), getCraftingRemainder());
    }

    private void craft(ArtisanPressRecipe recipe) {
        if (!canProcess(recipe)) {
            progress = 0;
            return;
        }

        if (!outputResults(outputContainer, recipe.results(), getCraftingRemainder())) {
            progress = 0;
            return;
        }

        inputContainer.removeItem(0, 1);
        progress = 0;
        setChanged();
    }

    private @Nullable ItemStackTemplate getCraftingRemainder() {
        return Balm.hooks().getCraftingRemainingItem(inputContainer.getItem(0));
    }

    private boolean canOutputResults(Container targetContainer, List<ItemStackTemplate> results, @Nullable ItemStackTemplate remainder) {
        final var copyContainer = new DefaultContainer(targetContainer.getContainerSize());
        for (int i = 0; i < targetContainer.getContainerSize(); i++) {
            copyContainer.setItem(i, targetContainer.getItem(i).copy());
        }
        return outputResults(copyContainer, results, remainder);
    }

    private boolean outputResults(Container targetContainer, List<ItemStackTemplate> results, @Nullable ItemStackTemplate remainder) {
        for (final var result : results) {
            if (!ContainerUtils.insertItemStacked(targetContainer, result.create(), false).isEmpty()) {
                return false;
            }
        }
        return remainder == null || ContainerUtils.insertItemStacked(targetContainer, remainder.create(), false).isEmpty();
    }

    private void setProcessing(boolean processing) {
        if (this.processing != processing) {
            this.processing = processing;
            setChanged();
            if (level != null && !level.isClientSide()) {
                BalmBlockEntityUtils.sync(this);
            }
        }
    }

    private void spawnImpactParticles() {
        if (level == null || !level.isClientSide()) {
            return;
        }

        for (int i = 0; i < 8; i++) {
            final double velocityX = (level.getRandom().nextDouble() - 0.5) * 0.08;
            final double velocityY = 0.04 + level.getRandom().nextDouble() * 0.04;
            final double velocityZ = (level.getRandom().nextDouble() - 0.5) * 0.08;
            level.addParticle(
                    new BlockParticleOption(ParticleTypes.BLOCK, Blocks.ANVIL.defaultBlockState()),
                    worldPosition.getX() + 0.5 + (level.getRandom().nextDouble() - 0.5) * 0.2,
                    worldPosition.getY() + 0.55,
                    worldPosition.getZ() + 0.5 + (level.getRandom().nextDouble() - 0.5) * 0.2,
                    velocityX,
                    velocityY,
                    velocityZ);
        }
    }

}
