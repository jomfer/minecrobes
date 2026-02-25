package com.jomfer.minecrobes.block;

import com.jomfer.minecrobes.block.entity.LabBenchBlockEntity;
import com.jomfer.minecrobes.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class LabBenchBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    // Updated model: tabletop (0,12,0)-(16,14,14) + body (1,0,1)-(15,12,13)
    private static final VoxelShape SHAPE_NORTH = Shapes.or(
            Block.box(0, 12, 0, 16, 14, 14),   // tabletop
            Block.box(1, 0, 1, 15, 12, 13)      // body
    );
    private static final VoxelShape SHAPE_SOUTH = Shapes.or(
            Block.box(0, 12, 2, 16, 14, 16),
            Block.box(1, 0, 3, 15, 12, 15)
    );
    private static final VoxelShape SHAPE_EAST = Shapes.or(
            Block.box(2, 12, 0, 16, 14, 16),
            Block.box(3, 0, 1, 15, 12, 15)
    );
    private static final VoxelShape SHAPE_WEST = Shapes.or(
            Block.box(0, 12, 0, 14, 14, 16),
            Block.box(1, 0, 1, 13, 12, 15)
    );

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

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof LabBenchBlockEntity labBench) {
                NetworkHooks.openScreen((ServerPlayer) player, labBench, pos);
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LabBenchBlockEntity(pos, state);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof LabBenchBlockEntity labBench) {
                labBench.dropContents();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }
}
