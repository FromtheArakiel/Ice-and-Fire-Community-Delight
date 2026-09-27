package dev.arakiel.iceandfirecommunitydelight.neoforge;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;

import java.util.function.Supplier;

/**
 * Adds the contents of another loot table to the loot table it is attached to.
 *
 * <p>This is the direct 1.21 equivalent of the old {@code IceAndFireDelightModLootTableModifier}.
 * It is built on NeoForge's own {@link AddTableLootModifier}, so only the codec has to be provided
 * to give the modifier its own id.</p>
 */
public class NeoForgeLootModifier extends AddTableLootModifier {
    public static final Supplier<MapCodec<NeoForgeLootModifier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.mapCodec(instance -> codecStart(instance)
                    .and(ResourceKey.codec(Registries.LOOT_TABLE).fieldOf("lootTable")
                            .forGetter(modifier -> modifier.lootTable))
                    .apply(instance, NeoForgeLootModifier::new)));

    private final ResourceKey<LootTable> lootTable;

    public NeoForgeLootModifier(LootItemCondition[] conditions, ResourceKey<LootTable> lootTable) {
        super(conditions, lootTable);
        this.lootTable = lootTable;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
