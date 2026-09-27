package dev.arakiel.iceandfirecommunitydelight.util;

import net.minecraft.world.entity.LivingEntity;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Counts how often the "too much power" explosion triggered for an entity.
 *
 * <p>The 1.20.1 mod stored this in a custom {@code number_of_explosions} player attribute that had
 * to be attached to the player entity through loader specific events. The counter is purely
 * internal, so it is kept in memory instead. It is intentionally not persisted: the mechanics
 * (explosions stop once the configured amount was reached) stay the same for a session.</p>
 */
public final class Overload {
    private static final Map<UUID, Double> COUNTS = new ConcurrentHashMap<>();

    private Overload() {
    }

    public static double get(LivingEntity entity) {
        return COUNTS.getOrDefault(entity.getUUID(), 0.0D);
    }

    public static void increment(LivingEntity entity) {
        COUNTS.merge(entity.getUUID(), 1.0D, Double::sum);
    }
}
