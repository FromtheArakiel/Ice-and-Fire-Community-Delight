package dev.arakiel.iceandfirecommunitydelight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * All four mod pies.
 *
 * <p>The 1.20.1 sources had one full block class per pie, each re-implementing Farmer's Delight's
 * bite/cut logic. Here every pie only differs by its slice item and the effect of a bite, so the
 * behaviour is inherited from {@link PieBlock} and the effect is applied by overriding a single
 * method. The matching effects are shared with the slice items.</p>
 */
public class DelightPieBlock extends PieBlock {
    private final Consumer<LivingEntity> onEaten;

    public DelightPieBlock(BlockBehaviour.Properties properties, Supplier<Item> slice, Consumer<LivingEntity> onEaten) {
        super(properties, slice);
        this.onEaten = onEaten;
    }

    @Override
    protected InteractionResult consumeBite(Level level, BlockPos pos, BlockState state, Player player) {
        InteractionResult result = super.consumeBite(level, pos, state, player);
        if (result.consumesAction() && this.onEaten != null) {
            this.onEaten.accept(player);
        }
        return result;
    }
}
