package com.jomfer.minecrobes.item;

import com.jomfer.minecrobes.MinecrobesMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModItems {

    // The registry where we'll put our items
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MinecrobesMod.MODID);

    // The actual item, registered with a name and a constructor
    public static final RegistryObject<Item> PETRI_DISH =
            ITEMS.register("petri_dish", PetriDishItem::new);

    // Hook into the Forge event system
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
