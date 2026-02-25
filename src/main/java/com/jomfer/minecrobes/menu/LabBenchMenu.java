package com.jomfer.minecrobes.menu;

import com.jomfer.minecrobes.block.ModBlocks;
import com.jomfer.minecrobes.block.entity.LabBenchBlockEntity;
import com.jomfer.minecrobes.item.ModItems;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class LabBenchMenu extends AbstractContainerMenu {

    public static final int SLOT_PETRI_DISH = 0;
    public static final int SLOT_LB_MEDIUM = 1;
    public static final int SLOT_OUTPUT = 2;

    private final LabBenchBlockEntity blockEntity;
    private final ContainerLevelAccess access;
    private final IItemHandler itemHandler;
    private final SimpleContainer resultContainer = new SimpleContainer(1);

    // Client constructor (from network)
    public LabBenchMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory,
                playerInventory.player.level().getBlockEntity(extraData.readBlockPos()),
                new ItemStackHandler(3));
    }

    // Server constructor
    public LabBenchMenu(int containerId, Inventory playerInventory, BlockEntity entity, IItemHandler handler) {
        super(ModMenuTypes.LAB_BENCH_MENU.get(), containerId);
        this.blockEntity = (LabBenchBlockEntity) entity;
        this.access = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
        this.itemHandler = handler;

        // Lab bench input slots
        this.addSlot(new SlotItemHandler(handler, SLOT_PETRI_DISH, 38, 35));
        this.addSlot(new SlotItemHandler(handler, SLOT_LB_MEDIUM, 66, 35));

        // Result slot uses a separate container (like vanilla crafting)
        this.addSlot(new LabBenchResultSlot(resultContainer, 0, 124, 35, this));

        // Player inventory (3 rows of 9)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        // Player hotbar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }

        updateResult();
    }

    public boolean hasRecipe() {
        ItemStack dish = itemHandler.getStackInSlot(SLOT_PETRI_DISH);
        ItemStack medium = itemHandler.getStackInSlot(SLOT_LB_MEDIUM);
        return dish.is(ModItems.PETRI_DISH.get()) && medium.is(ModItems.LB_MEDIUM.get());
    }

    public void consumeInputs() {
        itemHandler.extractItem(SLOT_PETRI_DISH, 1, false);
        itemHandler.extractItem(SLOT_LB_MEDIUM, 1, false);
    }

    public void updateResult() {
        if (hasRecipe()) {
            resultContainer.setItem(0, new ItemStack(ModItems.LB_AGAR_PETRI_DISH.get()));
        } else {
            resultContainer.setItem(0, ItemStack.EMPTY);
        }
    }

    @Override
    public void broadcastChanges() {
        updateResult();
        super.broadcastChanges();
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack slotStack = slot.getItem();
        ItemStack originalStack = slotStack.copy();

        int benchSlotCount = 3;
        int playerInventoryStart = benchSlotCount;
        int playerInventoryEnd = playerInventoryStart + 36;

        if (index == SLOT_OUTPUT) {
            // Shift-click output -> player inventory
            if (!this.moveItemStackTo(slotStack, playerInventoryStart, playerInventoryEnd, true)) {
                return ItemStack.EMPTY;
            }
            slot.onQuickCraft(slotStack, originalStack);
        } else if (index >= playerInventoryStart) {
            // Shift-click from player inventory -> bench input slots
            if (slotStack.is(ModItems.PETRI_DISH.get())) {
                if (!this.moveItemStackTo(slotStack, SLOT_PETRI_DISH, SLOT_PETRI_DISH + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotStack.is(ModItems.LB_MEDIUM.get())) {
                if (!this.moveItemStackTo(slotStack, SLOT_LB_MEDIUM, SLOT_LB_MEDIUM + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                return ItemStack.EMPTY;
            }
        } else {
            // Shift-click from bench input slots -> player inventory
            if (!this.moveItemStackTo(slotStack, playerInventoryStart, playerInventoryEnd, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (slotStack.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        if (slotStack.getCount() == originalStack.getCount()) {
            return ItemStack.EMPTY;
        }

        slot.onTake(player, slotStack);
        return originalStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, ModBlocks.LAB_BENCH.get());
    }
}
