package dev.arakiel.iceandfirecommunitydelight;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

/** The single creative tab, keeping the id and title of the 1.20.1 mod. */
public final class ModTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(IceAndFireDelight.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> ICE_AND_FIRE_DELIGHT_CREATIVE_TAB =
            TABS.register(IceAndFireDelight.id("creative_tab"), () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .title(Component.translatable("item_group.iceandfirecommunitydelight.iceandfirecommunitydelight_creative_tab"))
                    .icon(() -> new ItemStack(ModItems.FRESH_SOUP_FROM_SEA_SERPENT.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.ECTOPLASM_JELLY.get());
                        output.accept(ModItems.FRIED_DRAGON_EGG.get());
                        output.accept(ModItems.MYRMEX_RESIN_COOKIE_JUNGLE.get());
                        output.accept(ModItems.MYRMEX_RESIN_COOKIE_DESERT.get());
                        output.accept(ModItems.MYRMEX_JUNGLE_RESIN_JELLY.get());
                        output.accept(ModItems.MYRMEX_DESERT_RESIN_JELLY.get());
                        output.accept(ModItems.CHIPS_FROM_SHINY_SCALES.get());
                        output.accept(ModItems.SPICY_CHIPS_FROM_SHINY_SCALES.get());
                        output.accept(ModItems.SEA_SERPENT_MEAT.get());
                        output.accept(ModItems.COOKED_SEA_SERPENT_MEAT.get());
                        output.accept(ModItems.TROLL_MEAT.get());
                        output.accept(ModItems.COOKED_TROLL_MEAT.get());
                        output.accept(ModItems.CYCLOPS_MEAT.get());
                        output.accept(ModItems.CYCLOPS_STEAK.get());
                        output.accept(ModItems.HYDRA_MEAT.get());
                        output.accept(ModItems.COOKED_HYDRA_MEAT.get());
                        output.accept(ModItems.FRESH_SOUP_FROM_SEA_SERPENT.get());
                        output.accept(ModItems.HONEY_GLAZED_TROLL_MEAT.get());
                        output.accept(ModItems.FIRE_DRAGON_RAMEN.get());
                        output.accept(ModItems.EYE_CHOWDER.get());
                        output.accept(ModItems.HYDRA_VENOM_SOUP.get());
                        output.accept(ModItems.HONEY_GLAZED_CYCLOPS_EYE.get());
                        output.accept(ModItems.FIRE_DRAGON_TACO.get());
                        output.accept(ModItems.COOL_SANDWICH.get());
                        output.accept(ModItems.LIGHTNING_DRAGON_HOTDOG.get());
                        output.accept(ModItems.TROLL_INTESTINES.get());
                        output.accept(ModItems.FIRE_MINCED_MEAT.get());
                        output.accept(ModItems.RAW_FIRE_SAUSAGE.get());
                        output.accept(ModItems.FIRE_SAUSAGE.get());
                        output.accept(ModItems.ICE_MINCED_MEAT.get());
                        output.accept(ModItems.RAW_ICE_SAUSAGE.get());
                        output.accept(ModItems.ICE_SAUSAGE.get());
                        output.accept(ModItems.LIGHTNING_MINCED_MEAT.get());
                        output.accept(ModItems.RAW_LIGHTNING_SAUSAGE.get());
                        output.accept(ModItems.LIGHTNING_SAUSAGE.get());
                        output.accept(ModItems.RAW_DRAGON_SPECIAL_SAUSAGE.get());
                        output.accept(ModItems.DRAGON_SPECIAL_SAUSAGE.get());
                        output.accept(ModItems.FIRE_HEART_WITH_POTATOES_IN_MUSHROOM_SAUCE.get());
                        output.accept(ModItems.ICE_HEART_WITH_POTATOES_IN_MUSHROOM_SAUCE.get());
                        output.accept(ModItems.LIGHTNING_HEART_WITH_POTATOES_IN_MUSHROOM_SAUCE.get());
                        output.accept(ModItems.EMPTY_MEASURING_CYLINDER.get());
                        output.accept(ModItems.FIRE_LILY_EXTRACT.get());
                        output.accept(ModItems.FROST_LILY_EXTRACT.get());
                        output.accept(ModItems.LIGHTNING_LILY_EXTRACT.get());
                        output.accept(ModItems.EMPTY_GLASS.get());
                        output.accept(ModItems.FIERY_HOT_COCKTAIL.get());
                        output.accept(ModItems.FROST_COCKTAIL.get());
                        output.accept(ModItems.ELECTRIC_COCKTAIL.get());
                        output.accept(ModItems.SPECIAL_COCKTAIL.get());
                        output.accept(ModItems.DRAGON_PIE_CRUST.get());
                        output.accept(ModBlocks.FIERY_HOT_PIE.get().asItem());
                        output.accept(ModItems.FIERY_HOT_PIE_SLICE.get());
                        output.accept(ModBlocks.FROST_PIE.get().asItem());
                        output.accept(ModItems.FROST_PIE_SLICE.get());
                        output.accept(ModBlocks.ELECTRIC_PIE.get().asItem());
                        output.accept(ModItems.ELECTRIC_PIE_SLICE.get());
                        output.accept(ModBlocks.DRAGON_SPECIAL_PIE.get().asItem());
                        output.accept(ModItems.DRAGON_SPECIAL_PIE_SLICE.get());
                        output.accept(ModItems.SPICES_FROM_WITHERBONE.get());
                        output.accept(ModItems.SPICES.get());
                        output.accept(ModItems.FLOUR_FROM_DRAGON_BONES.get());
                        output.accept(ModItems.DOUGH_FROM_DRAGON_BONES.get());
                        output.accept(ModItems.DRAGON_BONE_BUN.get());
                        output.accept(ModItems.MINI_PIZZA.get());
                        output.accept(ModItems.MINI_PIZZA_BLANK.get());
                        output.accept(ModItems.RAW_MINI_PIZZA_BLANK.get());
                        output.accept(ModItems.SEA_SERPENT_SLICE.get());
                        output.accept(ModItems.SEA_SERPENT_ROLL.get());
                        output.accept(ModItems.COOKED_SEA_SERPENT_SLICE.get());
                        output.accept(ModItems.SILVER_KNIFE.get());
                        output.accept(ModItems.HYDRA_FANG_KNIFE.get());
                        output.accept(ModItems.SEA_SERPENT_FANG_KNIFE.get());
                        output.accept(ModItems.DRAGONSTEEL_FIRE_KNIFE.get());
                        output.accept(ModItems.DRAGONSTEEL_ICE_KNIFE.get());
                        output.accept(ModItems.DRAGONSTEEL_LIGHTNING_KNIFE.get());
                        output.accept(ModItems.DRAGONBONE_KNIFE.get());
                        output.accept(ModItems.PHANTOM_KNIFE.get());
                        output.accept(ModBlocks.BLACK_DRAGON_CUTTING_BOARD.get().asItem());
                    })
                    .build());

    private ModTabs() {
    }

    public static void register() {
        TABS.register();
    }
}
