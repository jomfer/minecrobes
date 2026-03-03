package com.jomfer.minecrobes.block;

import com.jomfer.minecrobes.MinecrobesMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MinecrobesMod.MODID);

    public static final DeferredRegister<Item> BLOCK_ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MinecrobesMod.MODID);

    public static final RegistryObject<Block> LAB_BENCH = BLOCKS.register("lab_bench",
            () -> new LabBenchBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(2.0f, 3.0f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final RegistryObject<Item> LAB_BENCH_ITEM = BLOCK_ITEMS.register("lab_bench",
            () -> new BlockItem(LAB_BENCH.get(), new Item.Properties()));

    public static final RegistryObject<Block> BIOFILM_BLOCK = BLOCKS.register("biofilm_block",
            () -> new BiofilmBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .strength(0.5f)
                    .sound(SoundType.HONEY_BLOCK)
                    .friction(0.8f)
                    .noLootTable()
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Item> BIOFILM_BLOCK_ITEM = BLOCK_ITEMS.register("biofilm_block",
            () -> new BlockItem(BIOFILM_BLOCK.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        BLOCK_ITEMS.register(eventBus);
    }
}
