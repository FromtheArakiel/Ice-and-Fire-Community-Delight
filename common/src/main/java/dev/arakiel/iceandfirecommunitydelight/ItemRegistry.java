package dev.arakiel.iceandfirecommunitydelight;

import dev.arakiel.iceandfirecommunitydelight.item.ConsumableItem;
import dev.arakiel.iceandfirecommunitydelight.item.TooltipBlockItem;
import dev.arakiel.iceandfirecommunitydelight.item.OnHitKnifeItem;
import dev.arakiel.iceandfirecommunitydelight.item.PhantomKnifeItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Every item of the mod.
 *
 * <p>The 1.20.1 sources had one class per item; here the food values are data and only three
 * behaviours (eat action, drink container, on hit action) need code, so 73 classes collapse into
 * three shared implementations.</p>
 */
public final class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(IceAndFireDelight.MOD_ID, Registries.ITEM);

    // ------------------------------------------------------------------ basic ingredients

    public static final RegistrySupplier<Item> ECTOPLASM_JELLY = edible("ectoplasm_jelly", Rarity.COMMON, 64, food(4, 1.0F));
    public static final RegistrySupplier<Item> TROLL_INTESTINES = simple("troll_intestines", Rarity.COMMON, 64);
    public static final RegistrySupplier<Item> FIRE_MINCED_MEAT = simple("fire_minced_meat", Rarity.COMMON, 64);
    public static final RegistrySupplier<Item> ICE_MINCED_MEAT = simple("ice_minced_meat", Rarity.COMMON, 64);
    public static final RegistrySupplier<Item> LIGHTNING_MINCED_MEAT = simple("lightning_minced_meat", Rarity.COMMON, 64);
    public static final RegistrySupplier<Item> SPICES_FROM_WITHERBONE = simple("spices_from_witherbone", Rarity.COMMON, 64);
    public static final RegistrySupplier<Item> SPICES = simple("spices", Rarity.COMMON, 64);
    public static final RegistrySupplier<Item> FLOUR_FROM_DRAGON_BONES = simple("flour_from_dragon_bones", Rarity.COMMON, 64);
    public static final RegistrySupplier<Item> DOUGH_FROM_DRAGON_BONES = simple("dough_from_dragon_bones", Rarity.COMMON, 64);
    public static final RegistrySupplier<Item> EMPTY_GLASS = simple("empty_glass", Rarity.COMMON, 64);
    public static final RegistrySupplier<Item> EMPTY_MEASURING_CYLINDER = simple("empty_measuring_cylinder", Rarity.COMMON, 64);
    public static final RegistrySupplier<Item> RAW_FIRE_SAUSAGE = simple("raw_fire_sausage", Rarity.RARE, 16);
    public static final RegistrySupplier<Item> RAW_ICE_SAUSAGE = simple("raw_ice_sausage", Rarity.RARE, 16);
    public static final RegistrySupplier<Item> RAW_LIGHTNING_SAUSAGE = simple("raw_lightning_sausage", Rarity.RARE, 16);
    public static final RegistrySupplier<Item> RAW_DRAGON_SPECIAL_SAUSAGE = simple("raw_dragon_special_sausage", Rarity.EPIC, 16);
    public static final RegistrySupplier<Item> RAW_MINI_PIZZA_BLANK = simple("raw_mini_pizza_blank", Rarity.COMMON, 64);

    // ------------------------------------------------------------------ food

    public static final RegistrySupplier<Item> CYCLOPS_MEAT = edible("cyclops_meat", Rarity.COMMON, 64, food(3, 0.3F));
    public static final RegistrySupplier<Item> CYCLOPS_STEAK = edible("cyclops_steak", Rarity.COMMON, 64, food(9, 0.8F));
    public static final RegistrySupplier<Item> TROLL_MEAT = edible("troll_meat", Rarity.COMMON, 64, food(3, 0.3F));
    public static final RegistrySupplier<Item> COOKED_TROLL_MEAT = edible("cooked_troll_meat", Rarity.COMMON, 64, food(9, 0.9F));
    public static final RegistrySupplier<Item> HONEY_GLAZED_TROLL_MEAT =
            withContainer("honey_glazed_troll_meat", Rarity.COMMON, 1, food(13, 0.45F), null, () -> Items.BOWL);
    public static final RegistrySupplier<Item> SEA_SERPENT_MEAT = edible("sea_serpent_meat", Rarity.COMMON, 64, food(3, 0.35F));
    public static final RegistrySupplier<Item> COOKED_SEA_SERPENT_MEAT =
            edible("cooked_sea_serpent_meat", Rarity.COMMON, 64, food(8, 0.9F));
    public static final RegistrySupplier<Item> FRESH_SOUP_FROM_SEA_SERPENT =
            withContainer("fresh_soup_from_sea_serpent", Rarity.COMMON, 1, food(15, 0.45F), null, () -> Items.BOWL);
    public static final RegistrySupplier<Item> SEA_SERPENT_SLICE =
            edible("sea_serpent_slice", Rarity.COMMON, 64, food(1, 0.4F), null, 5);
    public static final RegistrySupplier<Item> COOKED_SEA_SERPENT_SLICE =
            edible("cooked_sea_serpent_slice", Rarity.COMMON, 64, food(4, 0.9F), null, 5);
    public static final RegistrySupplier<Item> SEA_SERPENT_ROLL =
            edible("sea_serpent_roll", Rarity.COMMON, 64, food(7, 0.3F), null, 10);
    public static final RegistrySupplier<Item> DRAGON_PIE_CRUST = edible("dragon_pie_crust", Rarity.COMMON, 64, food(2, 0.3F));
    public static final RegistrySupplier<Item> DRAGON_BONE_BUN = edible("dragon_bone_bun", Rarity.COMMON, 64, food(5, 0.6F));
    public static final RegistrySupplier<Item> MINI_PIZZA = edible("mini_pizza", Rarity.COMMON, 64, food(9, 0.8F));
    public static final RegistrySupplier<Item> MINI_PIZZA_BLANK =
            edible("mini_pizza_blank", Rarity.COMMON, 64, food(1, 0.1F), null, 15);
    public static final RegistrySupplier<Item> CHIPS_FROM_SHINY_SCALES =
            edible("chips_from_shiny_scales", Rarity.COMMON, 64, food(3, 0.1F), null, 4);
    public static final RegistrySupplier<Item> MYRMEX_JUNGLE_RESIN_JELLY =
            edible("myrmex_jungle_resin_jelly", Rarity.COMMON, 64, food(5, 0.35F), "item.iceandfirecommunitydelight.tooltip.myrmex_jungle_resin_jelly.line1");
    public static final RegistrySupplier<Item> MYRMEX_DESERT_RESIN_JELLY =
            edible("myrmex_desert_resin_jelly", Rarity.COMMON, 64, food(5, 0.35F), "item.iceandfirecommunitydelight.tooltip.myrmex_desert_resin_jelly.line1");
    public static final RegistrySupplier<Item> MYRMEX_RESIN_COOKIE_JUNGLE =
            edible("myrmex_resin_cookie_jungle", Rarity.COMMON, 64, food(5, 0.35F), "item.iceandfirecommunitydelight.tooltip.myrmex_jungle_resin_cookie.line1");
    public static final RegistrySupplier<Item> MYRMEX_RESIN_COOKIE_DESERT =
            edible("myrmex_resin_cookie_desert", Rarity.COMMON, 64, food(5, 0.35F), "item.iceandfirecommunitydelight.tooltip.myrmex_desert_resin_cookie.line1");

    // ------------------------------------------------------------------ food with an effect

    public static final RegistrySupplier<Item> FRIED_DRAGON_EGG = special("fried_dragon_egg", Rarity.RARE, 8, food(16, 0.5F, true, false),
            FoodEffects::friedDragonEgg, "item.iceandfirecommunitydelight.tooltip.fried_dragon_egg.line1");
    public static final RegistrySupplier<Item> HYDRA_MEAT = special("hydra_meat", Rarity.COMMON, 64, food(4, 0.3F),
            FoodEffects::hydraMeat);
    /** The new sources removed the poison/resistance effect from cooked hydra meat. */
    public static final RegistrySupplier<Item> COOKED_HYDRA_MEAT = edible("cooked_hydra_meat", Rarity.COMMON, 64, food(10, 0.9F));
    public static final RegistrySupplier<Item> COOL_SANDWICH = special("cool_sandwich", Rarity.UNCOMMON, 64, food(8, 0.4F, true, false),
            FoodEffects::coolSandwich, "item.iceandfirecommunitydelight.tooltip.cool_sandwich.line1");
    public static final RegistrySupplier<Item> FIRE_DRAGON_RAMEN =
            withContainer("fire_dragon_ramen", Rarity.UNCOMMON, 1, food(11, 0.4F, true, false), FoodEffects::fireDragonRamen,
                    () -> Items.BOWL,
                    "item.iceandfirecommunitydelight.tooltip.fire_dragon_ramen.line1",
                    "item.iceandfirecommunitydelight.tooltip.fire_dragon_ramen.line2");
    public static final RegistrySupplier<Item> FIRE_DRAGON_TACO = special("fire_dragon_taco", Rarity.UNCOMMON, 64, food(8, 0.4F, true, false),
            FoodEffects::fireDragonRamen,
            "item.iceandfirecommunitydelight.tooltip.fire_dragon_ramen.line1",
            "item.iceandfirecommunitydelight.tooltip.fire_dragon_ramen.line2");
    public static final RegistrySupplier<Item> EYE_CHOWDER =
            withContainer("eye_chowder", Rarity.RARE, 1, food(16, 0.5F, true, false), FoodEffects::eyeChowder, () -> Items.BOWL,
                    "item.iceandfirecommunitydelight.tooltip.eye_chowder.line1");
    public static final RegistrySupplier<Item> HONEY_GLAZED_CYCLOPS_EYE =
            withContainer("honey_glazed_cyclops_eye", Rarity.RARE, 64, food(9, 0.5F, true, false), FoodEffects::honeyGlazedCyclopsEye,
                    () -> Items.STICK,
                    "item.iceandfirecommunitydelight.tooltip.honey_glazed_cyclops_eye.line1");
    public static final RegistrySupplier<Item> FIRE_HEART_WITH_POTATOES_IN_MUSHROOM_SAUCE =
            withContainer("fire_heart_with_potatoes_in_mushroom_sauce", Rarity.RARE, 1, food(16, 0.45F, true, false),
                    FoodEffects::fireHeart, () -> Items.BOWL,
                    "item.iceandfirecommunitydelight.tooltip.fire_heart_with_potatoes_in_mushroom_sauce.line1",
                    "item.iceandfirecommunitydelight.tooltip.fire_heart_with_potatoes_in_mushroom_sauce.line2",
                    "item.iceandfirecommunitydelight.tooltip.fire_heart_with_potatoes_in_mushroom_sauce.line3");
    public static final RegistrySupplier<Item> ICE_HEART_WITH_POTATOES_IN_MUSHROOM_SAUCE =
            withContainer("ice_heart_with_potatoes_in_mushroom_sauce", Rarity.RARE, 1, food(16, 0.45F, true, false),
                    FoodEffects::iceHeart, () -> Items.BOWL,
                    "item.iceandfirecommunitydelight.tooltip.ice_heart_with_potatoes_in_mushroom_sauce.line1",
                    "item.iceandfirecommunitydelight.tooltip.ice_heart_with_potatoes_in_mushroom_sauce.line2",
                    "item.iceandfirecommunitydelight.tooltip.ice_heart_with_potatoes_in_mushroom_sauce.line3");
    public static final RegistrySupplier<Item> LIGHTNING_HEART_WITH_POTATOES_IN_MUSHROOM_SAUCE =
            withContainer("lightning_heart_with_potatoes_in_mushroom_sauce", Rarity.RARE, 1, food(16, 0.45F, true, false),
                    FoodEffects::lightningHeart, () -> Items.BOWL,
                    "item.iceandfirecommunitydelight.tooltip.lightning_heart_with_potatoes_in_mushroom_sauce.line1",
                    "item.iceandfirecommunitydelight.tooltip.lightning_heart_with_potatoes_in_mushroom_sauce.line2",
                    "item.iceandfirecommunitydelight.tooltip.lightning_heart_with_potatoes_in_mushroom_sauce.line3");
    public static final RegistrySupplier<Item> SPICY_CHIPS_FROM_SHINY_SCALES =
            special("spicy_chips_from_shiny_scales", Rarity.UNCOMMON, 64, food(3, 0.2F, true, false), FoodEffects::spicyChips,
                    "item.iceandfirecommunitydelight.tooltip.spicy_chips_from_shiny_scales.line1",
                    "item.iceandfirecommunitydelight.tooltip.spicy_chips_from_shiny_scales.line2");
    public static final RegistrySupplier<Item> LIGHTNING_DRAGON_HOTDOG =
            special("lightning_dragon_hotdog", Rarity.RARE, 64, food(10, 0.75F, true, false), FoodEffects::lightningDragonHotdog,
                    "item.iceandfirecommunitydelight.tooltip.lightning_dragon_hotdog.line1",
                    "item.iceandfirecommunitydelight.tooltip.lightning_dragon_hotdog.line2");

    // ------------------------------------------------------------------ sausages

    public static final RegistrySupplier<Item> FIRE_SAUSAGE = special("fire_sausage", Rarity.RARE, 16, food(9, 0.7F, true, false),
            FoodEffects::fireSausage, "item.iceandfirecommunitydelight.tooltip.fire_sausage.line1");
    public static final RegistrySupplier<Item> ICE_SAUSAGE = special("ice_sausage", Rarity.RARE, 16, food(9, 0.7F, true, false),
            FoodEffects::iceSausage, "item.iceandfirecommunitydelight.tooltip.frost_sausage.line1");
    public static final RegistrySupplier<Item> LIGHTNING_SAUSAGE = special("lightning_sausage", Rarity.RARE, 16, food(9, 0.7F, true, false),
            FoodEffects::lightningSausage, "item.iceandfirecommunitydelight.tooltip.lightning_sausage.line1");
    public static final RegistrySupplier<Item> DRAGON_SPECIAL_SAUSAGE =
            special("dragon_special_sausage", Rarity.EPIC, 16, food(11, 0.8F, true, false), FoodEffects::dragonSpecialSausage,
                    "item.iceandfirecommunitydelight.tooltip.dragon_special_sausage.line1",
                    "item.iceandfirecommunitydelight.tooltip.dragon_special_sausage.line2");

    // ------------------------------------------------------------------ pies and slices

    public static final RegistrySupplier<Item> FIERY_HOT_PIE = blockItem("fiery_hot_pie", BlockRegistry.FIERY_HOT_PIE);
    public static final RegistrySupplier<Item> FIERY_HOT_PIE_SLICE =
            special("fiery_hot_pie_slice", Rarity.RARE, 64, food(4, 0.4F, true, true), FoodEffects::fieryHot,
                    "item.iceandfirecommunitydelight.tooltip.fiery_hot_pie_slice.line1",
                    "item.iceandfirecommunitydelight.tooltip.fiery_hot_pie_slice.line2");
    public static final RegistrySupplier<Item> FROST_PIE = blockItem("frost_pie", BlockRegistry.FROST_PIE);
    public static final RegistrySupplier<Item> FROST_PIE_SLICE =
            special("frost_pie_slice", Rarity.RARE, 64, food(4, 0.4F, true, true), FoodEffects::frost,
                    "item.iceandfirecommunitydelight.tooltip.frost_pie_slice.line1",
                    "item.iceandfirecommunitydelight.tooltip.frost_pie_slice.line2");
    public static final RegistrySupplier<Item> ELECTRIC_PIE = blockItem("electric_pie", BlockRegistry.ELECTRIC_PIE);
    public static final RegistrySupplier<Item> ELECTRIC_PIE_SLICE =
            special("electric_pie_slice", Rarity.RARE, 64, food(4, 0.4F, true, true), FoodEffects::electric,
                    "item.iceandfirecommunitydelight.tooltip.electric_pie_slice.line1",
                    "item.iceandfirecommunitydelight.tooltip.electric_pie_slice.line2");
    public static final RegistrySupplier<Item> DRAGON_SPECIAL_PIE = blockItem("dragon_special_pie", BlockRegistry.DRAGON_SPECIAL_PIE);
    public static final RegistrySupplier<Item> DRAGON_SPECIAL_PIE_SLICE =
            special("dragon_special_pie_slice", Rarity.EPIC, 64, food(4, 0.4F, true, true), FoodEffects::dragonSpecialPieSlice,
                    "item.iceandfirecommunitydelight.tooltip.dragon_special_pie_slice.line1",
                    "item.iceandfirecommunitydelight.tooltip.dragon_special_pie_slice.line2");

    // ------------------------------------------------------------------ drinks

    public static final RegistrySupplier<Item> FIERY_HOT_COCKTAIL = drink("fiery_hot_cocktail", Rarity.RARE, FoodEffects::fieryHotCocktail,
            () -> ItemRegistry.EMPTY_GLASS.get(),
            "item.iceandfirecommunitydelight.tooltip.fiery_hot_cocktail.line1",
            "item.iceandfirecommunitydelight.tooltip.fiery_hot_cocktail.line2");
    public static final RegistrySupplier<Item> FROST_COCKTAIL = drink("frost_cocktail", Rarity.RARE, FoodEffects::frostCocktail,
            () -> ItemRegistry.EMPTY_GLASS.get(),
            "item.iceandfirecommunitydelight.tooltip.frost_cocktail.line1",
            "item.iceandfirecommunitydelight.tooltip.frost_cocktail.line2");
    public static final RegistrySupplier<Item> ELECTRIC_COCKTAIL = drink("electric_cocktail", Rarity.RARE, FoodEffects::electricCocktail,
            () -> ItemRegistry.EMPTY_GLASS.get(),
            "item.iceandfirecommunitydelight.tooltip.electric_cocktail.line1",
            "item.iceandfirecommunitydelight.tooltip.electric_cocktail.line2");
    public static final RegistrySupplier<Item> SPECIAL_COCKTAIL = drink("special_cocktail", Rarity.EPIC, FoodEffects::specialCocktail,
            () -> ItemRegistry.EMPTY_GLASS.get(),
            "item.iceandfirecommunitydelight.tooltip.special_cocktail.line1",
            "item.iceandfirecommunitydelight.tooltip.special_cocktail.line2",
            "item.iceandfirecommunitydelight.tooltip.special_cocktail.line3");
    public static final RegistrySupplier<Item> FIRE_LILY_EXTRACT = extract("fire_lily_extract", FoodEffects::fireLilyExtract,
            "item.iceandfirecommunitydelight.tooltip.fire_lily_extract.line1",
            "item.iceandfirecommunitydelight.tooltip.fire_lily_extract.line2");
    public static final RegistrySupplier<Item> FROST_LILY_EXTRACT = extract("frost_lily_extract", FoodEffects::frostLilyExtract,
            "item.iceandfirecommunitydelight.tooltip.frost_lily_extract.line1",
            "item.iceandfirecommunitydelight.tooltip.frost_lily_extract.line2");
    public static final RegistrySupplier<Item> LIGHTNING_LILY_EXTRACT = extract("lightning_lily_extract", FoodEffects::lightningLilyExtract,
            "item.iceandfirecommunitydelight.tooltip.lightning_lily_extract.line1",
            "item.iceandfirecommunitydelight.tooltip.lightning_lily_extract.line2");
    public static final RegistrySupplier<Item> HYDRA_VENOM_SOUP =
            ITEMS.register(IceAndFireDelight.id("hydra_venom_soup"), () -> new ConsumableItem(
                    properties(Rarity.UNCOMMON, 1).food(food(7, 1.1F, true, false)), FoodEffects::hydraVenomSoup,
                    () -> Items.BOWL, true, 30)
                    .tooltip("item.iceandfirecommunitydelight.tooltip.hydra_venom_soup.line1",
                            "item.iceandfirecommunitydelight.tooltip.hydra_venom_soup.line2",
                            "item.iceandfirecommunitydelight.tooltip.warning"));

    // ------------------------------------------------------------------ knives

    public static final RegistrySupplier<Item> SILVER_KNIFE = knife("silver_knife", KnifeTiers.SILVER, null,
            "item.iceandfirecommunitydelight.tooltip.silver_knife.line1");
    public static final RegistrySupplier<Item> HYDRA_FANG_KNIFE = knife("hydra_fang_knife", KnifeTiers.HYDRA_FANG, null,
            "item.iceandfirecommunitydelight.tooltip.hydra_fang_knife.line1");
    public static final RegistrySupplier<Item> SEA_SERPENT_FANG_KNIFE = knife("sea_serpent_fang_knife", KnifeTiers.SEA_SERPENT_FANG, null);
    public static final RegistrySupplier<Item> DRAGONSTEEL_FIRE_KNIFE =
            knife("dragonsteel_fire_knife", KnifeTiers.DRAGONSTEEL_FIRE, CombatEffects::igniteFromKnife);
    public static final RegistrySupplier<Item> DRAGONSTEEL_ICE_KNIFE =
            knife("dragonsteel_ice_knife", KnifeTiers.DRAGONSTEEL_ICE, CombatEffects::frostbite);
    public static final RegistrySupplier<Item> DRAGONSTEEL_LIGHTNING_KNIFE =
            knife("dragonsteel_lightning_knife", KnifeTiers.DRAGONSTEEL_LIGHTNING, CombatEffects::lightningFromKnife);
    public static final RegistrySupplier<Item> DRAGONBONE_KNIFE = knife("dragonbone_knife", KnifeTiers.DRAGONBONE, null);
    public static final RegistrySupplier<Item> PHANTOM_KNIFE =
            ITEMS.register(IceAndFireDelight.id("phantom_knife"), PhantomKnifeItem::new);

    public static final RegistrySupplier<Item> BLACK_DRAGON_CUTTING_BOARD =
            ITEMS.register(IceAndFireDelight.id("black_dragon_cutting_board"),
                    () -> new TooltipBlockItem(BlockRegistry.BLACK_DRAGON_CUTTING_BOARD.get(), properties(Rarity.COMMON, 64)));

    public static final RegistrySupplier<Item> SHINY_SCALES_BLOCK = blockItem("shiny_scales_block", BlockRegistry.SHINY_SCALES_BLOCK);

    private ItemRegistry() {
    }

    public static void register() {
        ITEMS.register();
    }

    // ------------------------------------------------------------------ factories

    private static RegistrySupplier<Item> simple(String name, Rarity rarity, int stackSize) {
        return ITEMS.register(IceAndFireDelight.id(name), () -> new Item(properties(rarity, stackSize)));
    }

    private static RegistrySupplier<Item> edible(String name, Rarity rarity, int stackSize, FoodProperties food, String... tooltips) {
        return ITEMS.register(IceAndFireDelight.id(name),
                () -> new ConsumableItem(properties(rarity, stackSize).food(food), null, null, false, 0).tooltip(tooltips));
    }

    /** Food with a custom use duration (0 keeps the vanilla value). */
    private static RegistrySupplier<Item> edible(String name, Rarity rarity, int stackSize, FoodProperties food,
                                                Consumer<LivingEntity> onEaten, int useDuration) {
        return ITEMS.register(IceAndFireDelight.id(name),
                () -> new ConsumableItem(properties(rarity, stackSize).food(food), onEaten, null, false, useDuration));
    }

    private static RegistrySupplier<Item> special(String name, Rarity rarity, int stackSize, FoodProperties food,
                                                 Consumer<LivingEntity> onEaten, String... tooltips) {
        return ITEMS.register(IceAndFireDelight.id(name),
                () -> new ConsumableItem(properties(rarity, stackSize).food(food), onEaten, null, false, 0).tooltip(tooltips));
    }

    /** Food that hands its container back after being eaten, like the bowl of a soup. */
    private static RegistrySupplier<Item> withContainer(String name, Rarity rarity, int stackSize, FoodProperties food,
                                                       Consumer<LivingEntity> onEaten, Supplier<Item> container, String... tooltips) {
        return ITEMS.register(IceAndFireDelight.id(name),
                () -> new ConsumableItem(properties(rarity, stackSize).food(food), onEaten, container, false, 0).tooltip(tooltips));
    }

    private static RegistrySupplier<Item> drink(String name, Rarity rarity, Consumer<LivingEntity> onEaten,
                                                Supplier<Item> container, String... tooltips) {
        return ITEMS.register(IceAndFireDelight.id(name), () -> new ConsumableItem(
                properties(rarity, 16).food(food(0, 0.0F, true, false)), onEaten, container, true, 20).tooltip(tooltips));
    }

    private static RegistrySupplier<Item> extract(String name, Consumer<LivingEntity> onEaten, String... tooltips) {
        return ITEMS.register(IceAndFireDelight.id(name), () -> new ConsumableItem(
                properties(Rarity.RARE, 64).food(food(0, 0.0F, true, false))
                        .craftRemainder(ItemRegistry.EMPTY_MEASURING_CYLINDER.get()), onEaten,
                () -> ItemRegistry.EMPTY_MEASURING_CYLINDER.get(), true, 20).tooltip(tooltips));
    }

    private static RegistrySupplier<Item> knife(String name, Tier tier, OnHitKnifeItem.HitAction onHit, String... tooltips) {
        return ITEMS.register(IceAndFireDelight.id(name), () -> new OnHitKnifeItem(tier, 3.0F, -2.0F, onHit).tooltip(tooltips));
    }

    private static RegistrySupplier<Item> blockItem(String name, RegistrySupplier<Block> block) {
        return ITEMS.register(IceAndFireDelight.id(name), () -> new BlockItem(block.get(), properties(Rarity.COMMON, 64)));
    }

    private static Item.Properties properties(Rarity rarity, int stackSize) {
        return new Item.Properties().stacksTo(stackSize).rarity(rarity);
    }

    private static FoodProperties food(int nutrition, float saturation) {
        return food(nutrition, saturation, false, false);
    }

    private static FoodProperties food(int nutrition, float saturation, boolean alwaysEdible, boolean fast) {
        FoodProperties.Builder builder = new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation);
        if (alwaysEdible) {
            builder.alwaysEdible();
        }
        if (fast) {
            builder.fast();
        }
        return builder.build();
    }

}
