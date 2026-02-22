package com.jomfer.minecrobes.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LabBenchBlock extends HorizontalDirectionalBlock {

    // Blockbench model: 0,0,0 to 16,2,14 — facing north by default (14px deep on Z axis)
    private static final VoxelShape SHAPE_NORTH = Block.box(0, 0, 0, 16, 2, 14);
    private static final VoxelShape SHAPE_SOUTH = Block.box(0, 0, 2, 16, 2, 16);
    private static final VoxelShape SHAPE_EAST  = Block.box(2, 0, 0, 16, 2, 16);
    private static final VoxelShape SHAPE_WEST  = Block.box(0, 0, 0, 14, 2, 16);

    public LabBenchBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> SHAPE_SOUTH;
            case EAST -> SHAPE_EAST;
            case WEST -> SHAPE_WEST;
            default -> SHAPE_NORTH;
        };
    }
}
