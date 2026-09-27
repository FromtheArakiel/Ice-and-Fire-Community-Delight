package dev.arakiel.iceandfirecommunitydelight.fabric;

import dev.arakiel.iceandfirecommunitydelight.IceAndFireDelight;
import dev.arakiel.iceandfirecommunitydelight.LootTableAdditions;
import dev.arakiel.iceandfirecommunitydelight.Trades;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;

/**
 * Fabric entry point.
 *
 * <p>Fabric has neither global loot modifiers nor villager trade events, so both are expressed
 * through Fabric API hooks here. Everything else lives in the common module.</p>
 */
public final class IceAndFireDelightFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        IceAndFireDelight.init();
        registerTrades();
        registerLoot();
    }

    private static void registerTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 4,
                offers -> offers.addAll(Trades.fishermanTrades()));
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.BUTCHER, 4,
                offers -> offers.addAll(Trades.butcherTrades()));
        TradeOfferHelper.registerWanderingTraderOffers(1,
                offers -> offers.addAll(Trades.wanderingTrades()));
    }

    private static void registerLoot() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            for (LootTableAdditions.Addition addition : LootTableAdditions.ENTITY_DROPS) {
                if (key.equals(addition.target())) {
                    tableBuilder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(addition.addition())));
                }
            }
        });
    }
}
