package dev.arakiel.iceandfirecommunitydelight.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;

/**
 * Decorative block made of shiny scales.
 *
 * <p>Uses the vanilla axis property so it follows the face it was placed on, like the new 1.20.1
 * sources. Dropping itself is handled by a loot table.</p>
 */
public class ShinyScalesBlock extends Block {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

    public ShinyScalesBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.Y));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(AXIS, context.getClickedFace().getAxis());
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        boolean quarterTurn = rotation == Rotation.CLOCKWISE_90 || rotation == Rotation.COUNTERCLOCKWISE_90;
        if (quarterTurn && state.getValue(AXIS).isHorizontal()) {
            return state.setValue(AXIS, state.getValue(AXIS) == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X);
        }
        return state;
    }
}
