package dev.arakiel.iceandfirecommunitydelight;

import dev.arakiel.iceandfirecommunitydelight.block.BlackDragonCuttingBoardBlock;
import dev.arakiel.iceandfirecommunitydelight.block.DelightPieBlock;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

/** The five blocks of the mod. */
public final class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(IceAndFireDelight.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<Block> FIERY_HOT_PIE = BLOCKS.register(IceAndFireDelight.id("fiery_hot_pie"),
            () -> new DelightPieBlock(pieProperties(), () -> ModItems.FIERY_HOT_PIE_SLICE.get(), ModFoodEffects::fieryHot));

    public static final RegistrySupplier<Block> FROST_PIE = BLOCKS.register(IceAndFireDelight.id("frost_pie"),
            () -> new DelightPieBlock(pieProperties(), () -> ModItems.FROST_PIE_SLICE.get(), ModFoodEffects::frost));

    public static final RegistrySupplier<Block> ELECTRIC_PIE = BLOCKS.register(IceAndFireDelight.id("electric_pie"),
            () -> new DelightPieBlock(pieProperties(), () -> ModItems.ELECTRIC_PIE_SLICE.get(), ModFoodEffects::electric));

    public static final RegistrySupplier<Block> DRAGON_SPECIAL_PIE = BLOCKS.register(IceAndFireDelight.id("dragon_special_pie"),
            () -> new DelightPieBlock(pieProperties(), () -> ModItems.DRAGON_SPECIAL_PIE_SLICE.get(), ModFoodEffects::dragonSpecialPieBlock));

    public static final RegistrySupplier<Block> BLACK_DRAGON_CUTTING_BOARD =
            BLOCKS.register(IceAndFireDelight.id("black_dragon_cutting_board"),
                    () -> new BlackDragonCuttingBoardBlock(BlockBehaviour.Properties.of()
                            .strength(2.5F)
                            .sound(SoundType.WOOD)));

    private ModBlocks() {
    }

    public static void register() {
        BLOCKS.register();
    }

    /**
     * Pies are not full blocks, never conduct redstone and never drop themselves - the slices are
     * handed out by {@code PieBlock} instead.
     */
    private static BlockBehaviour.Properties pieProperties() {
        return BlockBehaviour.Properties.of()
                .sound(SoundType.EMPTY)
                .strength(1.0F, 10.0F)
                .noOcclusion()
                .noLootTable();
    }
}
