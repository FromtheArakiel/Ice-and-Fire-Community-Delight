package dev.arakiel.iceandfirecommunitydelight.integration;

import dev.arakiel.iceandfirecommunitydelight.IceAndFireDelight;
import dev.arakiel.iceandfirecommunitydelight.util.Advancements;
import dev.architectury.platform.Platform;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import vazkii.patchouli.api.PatchouliAPI;

/**
 * Patchouli integration.
 *
 * <p>Patchouli is a soft dependency: the library is only on the compile class path and every entry
 * point of this class is guarded by a mod loaded check, so the mod runs fine without it. The
 * cookbook content itself ({@code data/iceandfirecommunitydelight/patchouli_books/}) and its
 * crafting recipe are plain data and stay where they are.</p>
 *
 * <p>Replaces the 1.20.1 {@code grant_book_on_first_join.mcfunction}. That function ran
 * {@code give @s patchouli:guide_book[patchouli:book=...]}, which fails to compile without
 * Patchouli, so handing out the book moved into code. The former reward function of
 * {@code grant_book_on_first_join_adv} is gone; the advancement is now only the persistent
 * "already received the book" marker, which is what the function relied on anyway.</p>
 */
public final class PatchouliIntegration {
    /**
     * Id of the mod this class integrates with.
     *
     * <p>It is a compile time constant, so callers can compare it against
     * {@code Platform#isModLoaded} without pulling this class - and with it Patchouli - into the
     * class loader.</p>
     */
    public static final String MOD_ID = "patchouli";

    private static final String COOKBOOK_ADVANCEMENT = "grant_book_on_first_join_adv";

    private PatchouliIntegration() {
    }

    /** Hands out the cookbook the first time a player joins, if Patchouli is installed. */
    public static void grantCookbookOnFirstJoin(Player player) {
        if (!Platform.isModLoaded(MOD_ID) || !(player instanceof ServerPlayer serverPlayer)) {
            return;
        }

        AdvancementHolder marker = serverPlayer.server.getAdvancements()
                .get(IceAndFireDelight.id(COOKBOOK_ADVANCEMENT));
        if (marker == null || serverPlayer.getAdvancements().getOrStartProgress(marker).isDone()) {
            return;
        }

        // Built by Patchouli itself, so the book component is always correct for the loaded version.
        ItemStack cookbook = PatchouliAPI.get().getBookStack(IceAndFireDelight.id("cookbook"));
        if (cookbook.isEmpty()) {
            return;
        }
        if (!serverPlayer.getInventory().add(cookbook)) {
            serverPlayer.drop(cookbook, false);
        }
        Advancements.award(serverPlayer, COOKBOOK_ADVANCEMENT);
    }
}
