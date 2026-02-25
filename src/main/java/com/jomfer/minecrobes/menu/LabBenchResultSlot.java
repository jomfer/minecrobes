package com.jomfer.minecrobes.menu;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class LabBenchResultSlot extends Slot {

    private final LabBenchMenu menu;

    public LabBenchResultSlot(Container container, int index, int x, int y, LabBenchMenu menu) {
        super(container, index, x, y);
        this.menu = menu;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return false;
    }

    @Override
    public void onTake(Player player, ItemStack stack) {
        menu.consumeInputs();
        menu.updateResult();
        super.onTake(player, stack);
    }
}
