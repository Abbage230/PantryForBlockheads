package net.blay09.mods.pantryforblockheads.menu;

import net.blay09.mods.balm.world.inventory.QuickMove;
import net.blay09.mods.pantryforblockheads.PantryForBlockheads;
import net.blay09.mods.pantryforblockheads.block.entity.ArtisanPressBlockEntity;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ArtisanPressMenu extends AbstractContainerMenu {
    private final Container container;
    private final ContainerLevelAccess access;
    private final ContainerData data;
    private final QuickMove.Routing quickMove;

    public ArtisanPressMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, new SimpleContainer(ArtisanPressBlockEntity.SLOT_COUNT), new SimpleContainerData(ArtisanPressBlockEntity.DATA_COUNT), ContainerLevelAccess.NULL);
    }

    public ArtisanPressMenu(int containerId, Inventory inventory, Container container, ContainerData data, ContainerLevelAccess containerLevelAccess) {
        super(ModMenus.artisanPress.asSupplier().get(), containerId);
        checkContainerSize(container, ArtisanPressBlockEntity.SLOT_COUNT);
        checkContainerDataCount(data, ArtisanPressBlockEntity.DATA_COUNT);
        this.container = container;
        this.data = data;
        this.access = containerLevelAccess;
        container.startOpen(inventory.player);

        addSlot(new Slot(container, 0, 79, 21));
        addSlot(new ArtisanPressResultSlot(container, 1, 63, 50));
        addSlot(new ArtisanPressResultSlot(container, 2, 95, 50));
        addStandardInventorySlots(inventory, 8, 84);
        addDataSlots(data);
        quickMove = QuickMove.create(this, this::moveItemStackTo)
                .slot("input", 0)
                .route(stack -> container.canPlaceItem(0, stack), QuickMove.PLAYER, "input")
                .build();
    }

    public int getScaledProgress(int size) {
        final int progress = data.get(0);
        final int maxProgress = data.get(1);
        return maxProgress > 0 && progress > 0 ? progress * size / maxProgress : 0;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        return quickMove.transfer(this, player, slotIndex);
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(access, player, PantryForBlockheads.blocks().artisanPress.asBlock());
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        container.stopOpen(player);
    }

    private static class ArtisanPressResultSlot extends Slot {
        private ArtisanPressResultSlot(Container container, int slot, int x, int y) {
            super(container, slot, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }
    }
}
