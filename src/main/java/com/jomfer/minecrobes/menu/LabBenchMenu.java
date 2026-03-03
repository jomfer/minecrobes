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

    public static final int SLOT_INPUT_1 = 0;
    public static final int SLOT_INPUT_2 = 1;
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
        this.addSlot(new SlotItemHandler(handler, SLOT_INPUT_1, 38, 35));
        this.addSlot(new SlotItemHandler(handler, SLOT_INPUT_2, 66, 35));

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

    /**
     * Returns the output for the current recipe, or EMPTY if no recipe matches.
     * Recipe A: petri_dish + lb_medium -> lb_agar_petri_dish
     * Recipe B: full_collection_tube + lb_agar_petri_dish -> cultured_plate
     */
    private ItemStack getRecipeResult() {
        ItemStack input1 = itemHandler.getStackInSlot(SLOT_INPUT_1);
        ItemStack input2 = itemHandler.getStackInSlot(SLOT_INPUT_2);

        // Recipe A (shapeless): petri_dish + lb_medium -> lb_agar_petri_dish
        if (hasIngredients(input1, input2, ModItems.PETRI_DISH.get(), ModItems.LB_MEDIUM.get())) {
            return new ItemStack(ModItems.LB_AGAR_PETRI_DISH.get());
        }
        // Recipe B (shapeless): full_collection_tube + lb_agar_petri_dish -> cultured_plate
        if (hasIngredients(input1, input2, ModItems.FULL_COLLECTION_TUBE.get(), ModItems.LB_AGAR_PETRI_DISH.get())) {
            return new ItemStack(ModItems.CULTURED_PLATE.get());
        }

        return ItemStack.EMPTY;
    }

    /** Returns true if the two slots contain both ingredients in any order. */
    private static boolean hasIngredients(ItemStack slot1, ItemStack slot2,
                                          net.minecraft.world.level.ItemLike ingredientA,
                                          net.minecraft.world.level.ItemLike ingredientB) {
        return (slot1.is(ingredientA.asItem()) && slot2.is(ingredientB.asItem()))
            || (slot1.is(ingredientB.asItem()) && slot2.is(ingredientA.asItem()));
    }

    public boolean hasRecipe() {
        return !getRecipeResult().isEmpty();
    }

    public void consumeInputs() {
        itemHandler.extractItem(SLOT_INPUT_1, 1, false);
        itemHandler.extractItem(SLOT_INPUT_2, 1, false);
    }

    public void updateResult() {
        resultContainer.setItem(0, getRecipeResult());
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
            // Any valid lab bench ingredient can go into either input slot
            if (slotStack.is(ModItems.PETRI_DISH.get()) || slotStack.is(ModItems.FULL_COLLECTION_TUBE.get())
                    || slotStack.is(ModItems.LB_MEDIUM.get()) || slotStack.is(ModItems.LB_AGAR_PETRI_DISH.get())) {
                // Try slot 1 first, then slot 2
                if (!this.moveItemStackTo(slotStack, SLOT_INPUT_1, SLOT_INPUT_2 + 1, false)) {
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
