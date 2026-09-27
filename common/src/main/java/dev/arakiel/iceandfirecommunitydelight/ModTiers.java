package dev.arakiel.iceandfirecommunitydelight;

import dev.arakiel.iceandfirecommunitydelight.util.IafCompat;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

/**
 * Knife tool materials.
 *
 * <p>The 1.20.1 mod built one anonymous {@link Tier} per knife and pulled the phantasmal material
 * straight out of Ice and Fire. Ice and Fire exposes tool materials under different class names on
 * Fabric and NeoForge, so the materials are defined here once and only their repair materials are
 * resolved from the item registry.</p>
 */
public final class ModTiers {
    /** {@code c:ingots/silver} - filled by Ice and Fire and every other mod adding silver. */
    public static final Tier SILVER = new SimpleTier(460, 6.0F, -0.5F, 14, BlockTags.INCORRECT_FOR_IRON_TOOL,
            () -> Ingredient.of(tag("ingots/silver")));

    public static final Tier HYDRA_FANG = new SimpleTier(700, 4.0F, 1.0F, 2, BlockTags.INCORRECT_FOR_IRON_TOOL,
            () -> IafCompat.itemIngredient("iceandfire:hydra_fang"));

    public static final Tier SEA_SERPENT_FANG = new SimpleTier(600, 4.0F, 0.0F, 2, BlockTags.INCORRECT_FOR_IRON_TOOL,
            () -> IafCompat.itemIngredient("iceandfire:sea_serpent_fang"));

    public static final Tier DRAGONSTEEL_FIRE = new SimpleTier(2000, 4.0F, 6.0F, 2, BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            () -> IafCompat.itemIngredient("iceandfire:dragonsteel_fire_ingot"));

    public static final Tier DRAGONSTEEL_ICE = new SimpleTier(2000, 4.0F, 6.0F, 2, BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            () -> IafCompat.itemIngredient("iceandfire:dragonsteel_ice_ingot"));

    public static final Tier DRAGONSTEEL_LIGHTNING = new SimpleTier(2000, 4.0F, 6.0F, 2, BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            () -> IafCompat.itemIngredient("iceandfire:dragonsteel_lightning_ingot"));

    public static final Tier DRAGONBONE = new SimpleTier(1660, 4.0F, 2.0F, 2, BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            () -> IafCompat.itemIngredient("iceandfire:dragonbone"));

    /** Mirrors Ice and Fire's ghost sword tool material. */
    public static final Tier PHANTASMAL = new SimpleTier(3000, 10.0F, 5.0F, 25, BlockTags.INCORRECT_FOR_WOODEN_TOOL,
            () -> IafCompat.itemIngredient("iceandfire:ghost_ingot"));

    private ModTiers() {
    }

    static void init() {
        // Forces class initialisation so the materials exist before the knives are built.
    }

    private static TagKey<Item> tag(String path) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", path));
    }

    private record SimpleTier(int uses, float speed, float attackDamageBonus, int enchantmentValue,
                              TagKey<Block> incorrectBlocksForDrops,
                              Supplier<Ingredient> repairIngredient) implements Tier {
        @Override
        public int getUses() {
            return uses;
        }

        @Override
        public float getSpeed() {
            return speed;
        }

        @Override
        public float getAttackDamageBonus() {
            return attackDamageBonus;
        }

        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return incorrectBlocksForDrops;
        }

        @Override
        public int getEnchantmentValue() {
            return enchantmentValue;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return repairIngredient.get();
        }
    }
}
