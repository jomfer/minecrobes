package com.jomfer.minecrobes.block.entity;

import com.jomfer.minecrobes.MinecrobesMod;
import com.jomfer.minecrobes.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MinecrobesMod.MODID);

    public static final RegistryObject<BlockEntityType<LabBenchBlockEntity>> LAB_BENCH_ENTITY =
            BLOCK_ENTITIES.register("lab_bench_entity",
                    () -> BlockEntityType.Builder.of(LabBenchBlockEntity::new, ModBlocks.LAB_BENCH.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
