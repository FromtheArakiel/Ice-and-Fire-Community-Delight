package dev.arakiel.iceandfirecommunitydelight;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

import java.util.List;

/**
 * The villager trades of the mod.
 *
 * <p>The offers are plain vanilla {@link VillagerTrades.ItemListing}s so the exact same data can be
 * handed to Fabric's {@code TradeOfferHelper} and to NeoForge's trade events.</p>
 */
public final class Trades {
    private Trades() {
    }

    public static List<VillagerTrades.ItemListing> wanderingTrades() {
        return List.of(
                new Listing(new ItemStack(Items.EMERALD, 6), new ItemStack(ItemRegistry.CHIPS_FROM_SHINY_SCALES.get(), 5), 15, 5, 0.05F),
                new Listing(new ItemStack(Items.EMERALD, 13), new ItemStack(ItemRegistry.FRESH_SOUP_FROM_SEA_SERPENT.get()), 3, 5, 0.8F));
    }

    public static List<VillagerTrades.ItemListing> fishermanTrades() {
        return List.of(new Listing(new ItemStack(Items.EMERALD, 4), new ItemStack(ItemRegistry.SEA_SERPENT_MEAT.get()), 7, 5, 0.1F));
    }

    public static List<VillagerTrades.ItemListing> butcherTrades() {
        return List.of(
                new Listing(new ItemStack(Items.EMERALD, 6), new ItemStack(ItemRegistry.SEA_SERPENT_MEAT.get()), 11, 5, 0.1F),
                new Listing(new ItemStack(Items.EMERALD, 5), new ItemStack(ItemRegistry.TROLL_MEAT.get()), 10, 5, 0.1F));
    }

    /** Equivalent of Forge's {@code BasicItemListing}. */
    public record Listing(ItemStack price, ItemStack result, int maxUses, int experience, float priceMultiplier)
            implements VillagerTrades.ItemListing {
        @Override
        public MerchantOffer getOffer(Entity trader, RandomSource random) {
            return new MerchantOffer(new ItemCost(price.getItem(), price.getCount()), result.copy(), maxUses, experience, priceMultiplier);
        }
    }
}
