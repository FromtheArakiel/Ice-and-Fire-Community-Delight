package dev.arakiel.iceandfirecommunitydelight;

import dev.arakiel.iceandfirecommunitydelight.block.BlackDragonCuttingBoardBlock;
import dev.arakiel.iceandfirecommunitydelight.block.EffectPieBlock;
import dev.arakiel.iceandfirecommunitydelight.block.ShinyScalesBlock;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

/** The five blocks of the mod. */
public final class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(IceAndFireDelight.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<Block> FIERY_HOT_PIE = BLOCKS.register(IceAndFireDelight.id("fiery_hot_pie"),
            () -> new EffectPieBlock(pieProperties(), () -> ItemRegistry.FIERY_HOT_PIE_SLICE.get(), FoodEffects::fieryHot));

    public static final RegistrySupplier<Block> FROST_PIE = BLOCKS.register(IceAndFireDelight.id("frost_pie"),
            () -> new EffectPieBlock(pieProperties(), () -> ItemRegistry.FROST_PIE_SLICE.get(), FoodEffects::frost));

    public static final RegistrySupplier<Block> ELECTRIC_PIE = BLOCKS.register(IceAndFireDelight.id("electric_pie"),
            () -> new EffectPieBlock(pieProperties(), () -> ItemRegistry.ELECTRIC_PIE_SLICE.get(), FoodEffects::electric));

    public static final RegistrySupplier<Block> DRAGON_SPECIAL_PIE = BLOCKS.register(IceAndFireDelight.id("dragon_special_pie"),
            () -> new EffectPieBlock(pieProperties(), () -> ItemRegistry.DRAGON_SPECIAL_PIE_SLICE.get(), FoodEffects::dragonSpecialPieBlock));

    public static final RegistrySupplier<Block> BLACK_DRAGON_CUTTING_BOARD =
            BLOCKS.register(IceAndFireDelight.id("black_dragon_cutting_board"),
                    () -> new BlackDragonCuttingBoardBlock(BlockBehaviour.Properties.of()
                            .strength(2.5F)
                            .sound(SoundType.WOOD)));

    public static final RegistrySupplier<Block> SHINY_SCALES_BLOCK =
            BLOCKS.register(IceAndFireDelight.id("shiny_scales_block"),
                    () -> new ShinyScalesBlock(BlockBehaviour.Properties.of()
                            .ignitedByLava()
                            .sound(SoundType.CORAL_BLOCK)
                            .strength(1.0F, 10.0F)));

    private BlockRegistry() {
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
