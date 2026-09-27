package dev.arakiel.iceandfirecommunitydelight;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

/** The single creative tab, keeping the id and title of the 1.20.1 mod. */
public final class CreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(IceAndFireDelight.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> ICE_AND_FIRE_DELIGHT_CREATIVE_TAB =
            TABS.register(IceAndFireDelight.id("creative_tab"), () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .title(Component.translatable("item_group.iceandfirecommunitydelight.iceandfirecommunitydelight_creative_tab"))
                    .icon(() -> new ItemStack(ItemRegistry.FRESH_SOUP_FROM_SEA_SERPENT.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ItemRegistry.ECTOPLASM_JELLY.get());
                        output.accept(ItemRegistry.FRIED_DRAGON_EGG.get());
                        output.accept(ItemRegistry.MYRMEX_RESIN_COOKIE_JUNGLE.get());
                        output.accept(ItemRegistry.MYRMEX_RESIN_COOKIE_DESERT.get());
                        output.accept(ItemRegistry.MYRMEX_JUNGLE_RESIN_JELLY.get());
                        output.accept(ItemRegistry.MYRMEX_DESERT_RESIN_JELLY.get());
                        output.accept(ItemRegistry.CHIPS_FROM_SHINY_SCALES.get());
                        output.accept(ItemRegistry.SPICY_CHIPS_FROM_SHINY_SCALES.get());
                        output.accept(ItemRegistry.SEA_SERPENT_MEAT.get());
                        output.accept(ItemRegistry.COOKED_SEA_SERPENT_MEAT.get());
                        output.accept(ItemRegistry.TROLL_MEAT.get());
                        output.accept(ItemRegistry.COOKED_TROLL_MEAT.get());
                        output.accept(ItemRegistry.CYCLOPS_MEAT.get());
                        output.accept(ItemRegistry.CYCLOPS_STEAK.get());
                        output.accept(ItemRegistry.HYDRA_MEAT.get());
                        output.accept(ItemRegistry.COOKED_HYDRA_MEAT.get());
                        output.accept(ItemRegistry.FRESH_SOUP_FROM_SEA_SERPENT.get());
                        output.accept(ItemRegistry.HONEY_GLAZED_TROLL_MEAT.get());
                        output.accept(ItemRegistry.FIRE_DRAGON_RAMEN.get());
                        output.accept(ItemRegistry.EYE_CHOWDER.get());
                        output.accept(ItemRegistry.HYDRA_VENOM_SOUP.get());
                        output.accept(ItemRegistry.HONEY_GLAZED_CYCLOPS_EYE.get());
                        output.accept(ItemRegistry.FIRE_DRAGON_TACO.get());
                        output.accept(ItemRegistry.COOL_SANDWICH.get());
                        output.accept(ItemRegistry.LIGHTNING_DRAGON_HOTDOG.get());
                        output.accept(ItemRegistry.TROLL_INTESTINES.get());
                        output.accept(ItemRegistry.FIRE_MINCED_MEAT.get());
                        output.accept(ItemRegistry.RAW_FIRE_SAUSAGE.get());
                        output.accept(ItemRegistry.FIRE_SAUSAGE.get());
                        output.accept(ItemRegistry.ICE_MINCED_MEAT.get());
                        output.accept(ItemRegistry.RAW_ICE_SAUSAGE.get());
                        output.accept(ItemRegistry.ICE_SAUSAGE.get());
                        output.accept(ItemRegistry.LIGHTNING_MINCED_MEAT.get());
                        output.accept(ItemRegistry.RAW_LIGHTNING_SAUSAGE.get());
                        output.accept(ItemRegistry.LIGHTNING_SAUSAGE.get());
                        output.accept(ItemRegistry.RAW_DRAGON_SPECIAL_SAUSAGE.get());
                        output.accept(ItemRegistry.DRAGON_SPECIAL_SAUSAGE.get());
                        output.accept(ItemRegistry.FIRE_HEART_WITH_POTATOES_IN_MUSHROOM_SAUCE.get());
                        output.accept(ItemRegistry.ICE_HEART_WITH_POTATOES_IN_MUSHROOM_SAUCE.get());
                        output.accept(ItemRegistry.LIGHTNING_HEART_WITH_POTATOES_IN_MUSHROOM_SAUCE.get());
                        output.accept(ItemRegistry.EMPTY_MEASURING_CYLINDER.get());
                        output.accept(ItemRegistry.FIRE_LILY_EXTRACT.get());
                        output.accept(ItemRegistry.FROST_LILY_EXTRACT.get());
                        output.accept(ItemRegistry.LIGHTNING_LILY_EXTRACT.get());
                        output.accept(ItemRegistry.EMPTY_GLASS.get());
                        output.accept(ItemRegistry.FIERY_HOT_COCKTAIL.get());
                        output.accept(ItemRegistry.FROST_COCKTAIL.get());
                        output.accept(ItemRegistry.ELECTRIC_COCKTAIL.get());
                        output.accept(ItemRegistry.SPECIAL_COCKTAIL.get());
                        output.accept(ItemRegistry.DRAGON_PIE_CRUST.get());
                        output.accept(BlockRegistry.FIERY_HOT_PIE.get().asItem());
                        output.accept(ItemRegistry.FIERY_HOT_PIE_SLICE.get());
                        output.accept(BlockRegistry.FROST_PIE.get().asItem());
                        output.accept(ItemRegistry.FROST_PIE_SLICE.get());
                        output.accept(BlockRegistry.ELECTRIC_PIE.get().asItem());
                        output.accept(ItemRegistry.ELECTRIC_PIE_SLICE.get());
                        output.accept(BlockRegistry.DRAGON_SPECIAL_PIE.get().asItem());
                        output.accept(ItemRegistry.DRAGON_SPECIAL_PIE_SLICE.get());
                        output.accept(ItemRegistry.SPICES_FROM_WITHERBONE.get());
                        output.accept(ItemRegistry.SPICES.get());
                        output.accept(ItemRegistry.FLOUR_FROM_DRAGON_BONES.get());
                        output.accept(ItemRegistry.DOUGH_FROM_DRAGON_BONES.get());
                        output.accept(ItemRegistry.DRAGON_BONE_BUN.get());
                        output.accept(ItemRegistry.MINI_PIZZA.get());
                        output.accept(ItemRegistry.MINI_PIZZA_BLANK.get());
                        output.accept(ItemRegistry.RAW_MINI_PIZZA_BLANK.get());
                        output.accept(ItemRegistry.SEA_SERPENT_SLICE.get());
                        output.accept(ItemRegistry.SEA_SERPENT_ROLL.get());
                        output.accept(ItemRegistry.COOKED_SEA_SERPENT_SLICE.get());
                        output.accept(ItemRegistry.SILVER_KNIFE.get());
                        output.accept(ItemRegistry.HYDRA_FANG_KNIFE.get());
                        output.accept(ItemRegistry.SEA_SERPENT_FANG_KNIFE.get());
                        output.accept(ItemRegistry.DRAGONSTEEL_FIRE_KNIFE.get());
                        output.accept(ItemRegistry.DRAGONSTEEL_ICE_KNIFE.get());
                        output.accept(ItemRegistry.DRAGONSTEEL_LIGHTNING_KNIFE.get());
                        output.accept(ItemRegistry.DRAGONBONE_KNIFE.get());
                        output.accept(ItemRegistry.PHANTOM_KNIFE.get());
                        output.accept(BlockRegistry.BLACK_DRAGON_CUTTING_BOARD.get().asItem());
                        output.accept(BlockRegistry.SHINY_SCALES_BLOCK.get().asItem());
                    })
                    .build());

    private CreativeTabs() {
    }

    public static void register() {
        TABS.register();
    }
}
