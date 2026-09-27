package dev.arakiel.iceandfirecommunitydelight.util;

import dev.arakiel.iceandfirecommunitydelight.IceAndFireDelight;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

/**
 * Grants one of the mod's advancements to a player.
 *
 * <p>Replaces the five near identical {@code GetXxxAdvProcedure} classes of the 1.20.1 sources.</p>
 */
public final class DelightAdvancements {
    private DelightAdvancements() {
    }

    public static void award(Entity entity, String path) {
        if (!(entity instanceof ServerPlayer player)) {
            return;
        }

        AdvancementHolder advancement = player.server.getAdvancements().get(IceAndFireDelight.id(path));
        if (advancement == null) {
            return;
        }

        AdvancementProgress progress = player.getAdvancements().getOrStartProgress(advancement);
        if (progress.isDone()) {
            return;
        }

        for (String criterion : progress.getRemainingCriteria()) {
            player.getAdvancements().award(advancement, criterion);
        }
    }
}
