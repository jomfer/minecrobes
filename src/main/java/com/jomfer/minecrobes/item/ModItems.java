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

    public static final RegistryObject<Item> PETRI_DISH =
            ITEMS.register("petri_dish", PetriDishItem::new);

    public static final RegistryObject<Item> LB_MEDIUM =
            ITEMS.register("lb_medium", () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> LB_AGAR_PETRI_DISH =
            ITEMS.register("lb_agar_petri_dish", () -> new Item(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> EMPTY_COLLECTION_TUBE =
            ITEMS.register("empty_collection_tube", () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> FULL_COLLECTION_TUBE =
            ITEMS.register("full_collection_tube", () -> new Item(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> CULTURED_PLATE =
            ITEMS.register("cultured_plate", () -> new Item(new Item.Properties().stacksTo(16)));

    // Hook into the Forge event system
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
