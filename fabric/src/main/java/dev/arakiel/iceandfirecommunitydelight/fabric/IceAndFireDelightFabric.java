package dev.arakiel.iceandfirecommunitydelight.fabric;

import dev.arakiel.iceandfirecommunitydelight.IceAndFireDelight;
import dev.arakiel.iceandfirecommunitydelight.ModLoot;
import dev.arakiel.iceandfirecommunitydelight.ModTrades;
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
                offers -> offers.addAll(ModTrades.fishermanTrades()));
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.BUTCHER, 4,
                offers -> offers.addAll(ModTrades.butcherTrades()));
        TradeOfferHelper.registerWanderingTraderOffers(1,
                offers -> offers.addAll(ModTrades.wanderingTrades()));
    }

    private static void registerLoot() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            for (ModLoot.Addition addition : ModLoot.ENTITY_DROPS) {
                if (key.equals(addition.target())) {
                    tableBuilder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(addition.addition())));
                }
            }
        });
    }
}
