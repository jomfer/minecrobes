package com.jomfer.minecrobes.block;

import com.jomfer.minecrobes.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BiofilmBlock extends Block {

    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 14, 16);

    public BiofilmBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        // Slow entities like honey block
        entity.makeStuckInBlock(state, new net.minecraft.world.phys.Vec3(0.25D, 0.05D, 0.25D));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getItemInHand(hand);

        if (heldItem.is(ModItems.EMPTY_COLLECTION_TUBE.get())) {
            if (!level.isClientSide()) {
                // Consume one empty tube
                heldItem.shrink(1);

                // Give or drop a full_collection_tube
                ItemStack sample = new ItemStack(ModItems.FULL_COLLECTION_TUBE.get());
                if (!player.getInventory().add(sample)) {
                    ItemEntity drop = new ItemEntity(level,
                            pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, sample);
                    level.addFreshEntity(drop);
                }

                // Destroy the biofilm block
                level.destroyBlock(pos, false);
            }
            return InteractionResult.sidedSuccess(level.isClientSide());
        }

        return InteractionResult.PASS;
    }
}
