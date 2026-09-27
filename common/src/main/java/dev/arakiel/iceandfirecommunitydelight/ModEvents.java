package dev.arakiel.iceandfirecommunitydelight;

import dev.arakiel.iceandfirecommunitydelight.util.DelightAdvancements;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.event.events.common.TickEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cross loader listeners.
 *
 * <p>Merges the 1.20.1 {@code ModEventHandler}, {@code EffectRegistry},
 * {@code GrantBookOnFirstJoinConfigProcedure} and the twelve event hijacking procedures.</p>
 */
public final class ModEvents {
    /** Players who currently received flight from the dragon flight effect. */
    private static final Set<UUID> FLIGHT_GRANTED = ConcurrentHashMap.newKeySet();

    private ModEvents() {
    }

    public static void register() {
        EntityEvent.LIVING_HURT.register((entity, source, amount) -> {
            ModCombat.onLivingHurt(entity, source, amount);
            return EventResult.pass();
        });

        PlayerEvent.PLAYER_JOIN.register(player -> {
            if (ModConfig.giveBookOnStartup) {
                DelightAdvancements.award(player, "grant_book_on_first_join_adv");
            }
        });

        TickEvent.PLAYER_POST.register(ModEvents::updateFlight);
    }

    /** Called by the dragon flight effect while it is active. */
    static void markFlight(LivingEntity entity) {
        if (entity instanceof Player player) {
            FLIGHT_GRANTED.add(player.getUUID());
        }
    }

    /**
     * Ice and Fire's mob effect API has no "effect removed" hook any more, so the flight ability is
     * taken back one tick after the effect ran out - the same net result as the 1.20.1
     * {@code removeAttributeModifiers} override.
     */
    private static void updateFlight(Player player) {
        if (player.hasEffect(ModEffects.DRAGON_FLIGHT)) {
            return;
        }
        if (!FLIGHT_GRANTED.remove(player.getUUID())) {
            return;
        }
        if (!player.isCreative() && !player.isSpectator()) {
            player.getAbilities().mayfly = false;
            player.getAbilities().flying = false;
        }
        player.onUpdateAbilities();
    }
}
