package dev.arakiel.iceandfirecommunitydelight.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import vectorwing.farmersdelight.common.block.CuttingBoardBlock;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

/**
 * A cosmetic variant of Farmer's Delight's cutting board.
 *
 * <p>The 1.20.1 version also shipped a custom block entity renderer that reproduced Farmer's
 * Delight's renderer one to one; the board uses Farmer's Delight's block entity type, so that
 * renderer is already registered for it and the copy could be dropped. The tooltip lives in
 * {@code TooltipBlockItem}.</p>
 */
public class BlackDragonCuttingBoardBlock extends CuttingBoardBlock {
    public BlackDragonCuttingBoardBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(BlackDragonCuttingBoardBlock::new);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntityTypes.CUTTING_BOARD.get().create(pos, state);
    }
}
