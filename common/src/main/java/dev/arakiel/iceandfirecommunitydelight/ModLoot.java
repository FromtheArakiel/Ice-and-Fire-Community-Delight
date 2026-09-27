package dev.arakiel.iceandfirecommunitydelight;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.List;

/**
 * Describes "add the drops of table B to table A".
 *
 * <p>The 1.20.1 mod shipped one global loot modifier per entity plus a {@code forge:loot_table_id}
 * condition. The data is the same, but Fabric has no global loot modifiers, so the list is shared
 * and each loader applies it in its own way.</p>
 */
public final class ModLoot {
    public static final List<Addition> ENTITY_DROPS = List.of(
            new Addition(iceAndFire("entities/cyclops"), ours("entities/cyclops_loot_table")),
            new Addition(iceAndFire("entities/hydra"), ours("entities/hydra_loot_table")),
            new Addition(iceAndFire("entities/sea_serpent"), ours("entities/sea_serpent_loot_table")),
            new Addition(iceAndFire("entities/troll_forest"), ours("entities/troll_forest_loot_table")),
            new Addition(iceAndFire("entities/troll_frost"), ours("entities/troll_frost_loot_table")),
            new Addition(iceAndFire("entities/troll_mountain"), ours("entities/troll_mountain_loot_table")));

    private ModLoot() {
    }

    private static ResourceKey<LootTable> iceAndFire(String path) {
        return ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath("iceandfire", path));
    }

    private static ResourceKey<LootTable> ours(String path) {
        return ResourceKey.create(Registries.LOOT_TABLE, IceAndFireDelight.id(path));
    }

    public record Addition(ResourceKey<LootTable> target, ResourceKey<LootTable> addition) {
    }
}
