package dev.arakiel.iceandfirecommunitydelight.integration;

import dev.arakiel.iceandfirecommunitydelight.IceAndFireDelight;
import dev.arakiel.iceandfirecommunitydelight.ItemRegistry;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * JEI integration.
 *
 * <p>JEI is a soft dependency: the library is only on the compile class path and nothing is
 * bundled. JEI finds the plugin on its own - on NeoForge by scanning for the {@link JeiPlugin}
 * annotation, on Fabric through the {@code jei_mod_plugin} entrypoint of {@code fabric.mod.json} -
 * and this class is only loaded while JEI is asking for its plugins, so the mod runs fine without
 * it. This is the whole JEI coupling of the mod; the 1.20.1 sources split it over
 * {@code IceAndFireDelightModJeiInformation} and {@code IceAndFireDelightModBrewingRecipes}.</p>
 *
 * <p>The brewing plugin of the original mod only ever registered an empty recipe list, which has no
 * visible effect, therefore only the ingredient information is ported.</p>
 */
@JeiPlugin
public final class JeiIntegration implements IModPlugin {
    private static final String KEY_PREFIX = "jei.iceandfirecommunitydelight.";

    @Override
    public ResourceLocation getPluginUid() {
        return IceAndFireDelight.id("jei_integration");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        // Ingredients, tools and decoration.
        info(registration, "troll_intestines_jei", ItemRegistry.TROLL_INTESTINES);
        info(registration, "knife",
                ItemRegistry.SILVER_KNIFE,
                ItemRegistry.HYDRA_FANG_KNIFE,
                ItemRegistry.SEA_SERPENT_FANG_KNIFE,
                ItemRegistry.DRAGONSTEEL_FIRE_KNIFE,
                ItemRegistry.DRAGONSTEEL_ICE_KNIFE,
                ItemRegistry.DRAGONSTEEL_LIGHTNING_KNIFE,
                ItemRegistry.DRAGONBONE_KNIFE);
        info(registration, "phantom_knife_jei", ItemRegistry.PHANTOM_KNIFE);
        info(registration, "decorative_blocks",
                ItemRegistry.SHINY_SCALES_BLOCK,
                ItemRegistry.BLACK_DRAGON_CUTTING_BOARD);

        // The four pies and their slices.
        info(registration, "pies",
                ItemRegistry.FIERY_HOT_PIE,
                ItemRegistry.FROST_PIE,
                ItemRegistry.ELECTRIC_PIE,
                ItemRegistry.DRAGON_SPECIAL_PIE);
        info(registration, "pie_slices",
                ItemRegistry.FIERY_HOT_PIE_SLICE,
                ItemRegistry.FROST_PIE_SLICE,
                ItemRegistry.ELECTRIC_PIE_SLICE,
                ItemRegistry.DRAGON_SPECIAL_PIE_SLICE);

        // Dragon themed food and drinks.
        info(registration, "special_dragon_food",
                ItemRegistry.DRAGON_SPECIAL_PIE,
                ItemRegistry.DRAGON_SPECIAL_PIE_SLICE,
                ItemRegistry.DRAGON_SPECIAL_SAUSAGE);
        info(registration, "hearts_with_potatoes",
                ItemRegistry.FIRE_HEART_WITH_POTATOES_IN_MUSHROOM_SAUCE,
                ItemRegistry.ICE_HEART_WITH_POTATOES_IN_MUSHROOM_SAUCE,
                ItemRegistry.LIGHTNING_HEART_WITH_POTATOES_IN_MUSHROOM_SAUCE);
        info(registration, "cocktails",
                ItemRegistry.FIERY_HOT_COCKTAIL,
                ItemRegistry.FROST_COCKTAIL,
                ItemRegistry.ELECTRIC_COCKTAIL,
                ItemRegistry.SPECIAL_COCKTAIL);
        info(registration, "lily_extracts",
                ItemRegistry.FIRE_LILY_EXTRACT,
                ItemRegistry.FROST_LILY_EXTRACT,
                ItemRegistry.LIGHTNING_LILY_EXTRACT);
        info(registration, "sausages",
                ItemRegistry.FIRE_SAUSAGE,
                ItemRegistry.ICE_SAUSAGE,
                ItemRegistry.LIGHTNING_SAUSAGE);
        info(registration, "noodles_and_snacks",
                ItemRegistry.FIRE_DRAGON_RAMEN,
                ItemRegistry.FIRE_DRAGON_TACO,
                ItemRegistry.LIGHTNING_DRAGON_HOTDOG,
                ItemRegistry.COOL_SANDWICH);
        info(registration, "spicy_chips_jei", ItemRegistry.SPICY_CHIPS_FROM_SHINY_SCALES);

        // Food whose effect comes with a catch.
        info(registration, "hydra_meat_jei", ItemRegistry.HYDRA_MEAT);
        info(registration, "hydra_venom_soup_jei", ItemRegistry.HYDRA_VENOM_SOUP);
        info(registration, "center_of_weakness_food",
                ItemRegistry.EYE_CHOWDER,
                ItemRegistry.HONEY_GLAZED_CYCLOPS_EYE);
        info(registration, "fried_dragon_egg_jei", ItemRegistry.FRIED_DRAGON_EGG);
    }

    /** Shows one description text on every given item. */
    @SafeVarargs
    private static void info(IRecipeRegistration registration, String key, Supplier<? extends Item>... items) {
        List<ItemStack> stacks = new ArrayList<>(items.length);
        for (Supplier<? extends Item> item : items) {
            stacks.add(new ItemStack(item.get()));
        }
        registration.addIngredientInfo(stacks, VanillaTypes.ITEM_STACK, Component.translatable(KEY_PREFIX + key));
    }
}
