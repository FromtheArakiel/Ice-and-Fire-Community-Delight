package dev.arakiel.iceandfirecommunitydelight.neoforge;

import dev.arakiel.iceandfirecommunitydelight.IceAndFireDelight;
import dev.arakiel.iceandfirecommunitydelight.Trades;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

/** Villager trades, the NeoForge way. */
@EventBusSubscriber(modid = IceAndFireDelight.MOD_ID)
public final class NeoForgeEvents {
    private NeoForgeEvents() {
    }

    @SubscribeEvent
    public static void onVillagerTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FISHERMAN) {
            event.getTrades().get(4).addAll(Trades.fishermanTrades());
        }
        if (event.getType() == VillagerProfession.BUTCHER) {
            event.getTrades().get(4).addAll(Trades.butcherTrades());
        }
    }

    @SubscribeEvent
    public static void onWandererTrades(WandererTradesEvent event) {
        event.getGenericTrades().addAll(Trades.wanderingTrades());
    }
}
