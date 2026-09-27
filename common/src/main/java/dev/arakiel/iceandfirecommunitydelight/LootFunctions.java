package dev.arakiel.iceandfirecommunitydelight;

import dev.arakiel.iceandfirecommunitydelight.loot.CustomizeSeaSerpentMeatFunction;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;

/** Loot functions used by the mod's loot tables. */
public final class LootFunctions {
    public static final DeferredRegister<LootItemFunctionType<?>> LOOT_FUNCTIONS =
            DeferredRegister.create(IceAndFireDelight.MOD_ID, Registries.LOOT_FUNCTION_TYPE);

    public static final RegistrySupplier<LootItemFunctionType<CustomizeSeaSerpentMeatFunction>> CUSTOMIZE_TO_SEA_SERPENT_MEAT =
            LOOT_FUNCTIONS.register(IceAndFireDelight.id("customize_to_sea_serpent_meat"),
                    () -> new LootItemFunctionType<>(CustomizeSeaSerpentMeatFunction.CODEC));

    private LootFunctions() {
    }

    public static void register() {
        LOOT_FUNCTIONS.register();
    }
}
