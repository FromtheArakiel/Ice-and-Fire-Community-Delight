package dev.arakiel.iceandfirecommunitydelight.neoforge;

import com.mojang.serialization.MapCodec;
import dev.arakiel.iceandfirecommunitydelight.IceAndFireDelight;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

/**
 * NeoForge entry point.
 *
 * <p>NeoForge keeps Forge's global loot modifiers, so the six "add another loot table" entries are
 * registered as a loot modifier serializer whose json files live in
 * {@code data/iceandfirecommunitydelight/loot_modifiers/}.</p>
 */
@Mod(IceAndFireDelight.MOD_ID)
public final class IceAndFireDelightNeoForge {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, IceAndFireDelight.MOD_ID);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_TABLE =
            LOOT_MODIFIERS.register("iceandfirecommunitydelight_loot_modifier", NeoForgeLootModifier.CODEC);

    public IceAndFireDelightNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        IceAndFireDelight.init();
        LOOT_MODIFIERS.register(modEventBus);
    }
}
