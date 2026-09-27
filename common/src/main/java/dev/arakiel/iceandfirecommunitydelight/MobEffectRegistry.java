package dev.arakiel.iceandfirecommunitydelight;

import dev.arakiel.iceandfirecommunitydelight.effect.ConfigurableMobEffect;
import dev.arakiel.iceandfirecommunitydelight.util.Advancements;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import java.util.function.Supplier;

/** The eight mob effects of the mod. */
public final class MobEffectRegistry {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(IceAndFireDelight.MOD_ID, Registries.MOB_EFFECT);

    public static final RegistrySupplier<MobEffect> FIRE_ASPECT =
            register("fire_aspect", () -> new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL, -52686));

    public static final RegistrySupplier<MobEffect> ICE_ASPECT =
            register("ice_aspect", () -> new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL, -13474841));

    public static final RegistrySupplier<MobEffect> LIGHTNING_STRIKE =
            register("lightning_strike", () -> new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL, -1708222));

    public static final RegistrySupplier<MobEffect> POISON_RESISTANCE =
            register("poison_resistance", () -> new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL, -10053376)
                    .onTick((entity, amplifier) -> entity.removeEffect(MobEffects.POISON)));

    public static final RegistrySupplier<MobEffect> WARMING =
            register("warming", () -> new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL, -6710785));

    public static final RegistrySupplier<MobEffect> DRAGON_FLIGHT =
            register("dragon_flight", () -> new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL, -39322)
                    .onTick((entity, amplifier) -> {
                        if (entity instanceof Player player) {
                            if (!player.getAbilities().mayfly) {
                                player.getAbilities().mayfly = true;
                                // The ability flag has to reach the client, otherwise the player cannot fly.
                                player.onUpdateAbilities();
                            }
                            CommonEvents.markFlight(player);
                        }
                    })
                    .onStarted(entity -> Advancements.award(entity, "feel_like_a_dragon_adv")));

    public static final RegistrySupplier<MobEffect> DRAGONS_MIGHT =
            register("dragons_might", () -> new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL, -16737895)
                    .onStarted(entity -> Advancements.award(entity, "power_of_three_dragons_adv")));

    public static final RegistrySupplier<MobEffect> CENTER_OF_WEAKNESS =
            register("center_of_weakness", () -> new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL, -3355444)
                    .onTick(MobEffectRegistry::applyCenterOfWeakness));

    private MobEffectRegistry() {
    }

    private static RegistrySupplier<MobEffect> register(String name, Supplier<MobEffect> supplier) {
        // Registered under the mod namespace so the shipped textures and language files apply.
        return EFFECTS.register(IceAndFireDelight.id(name), supplier);
    }

    public static void register() {
        EFFECTS.register();
    }

    /**
     * Resolves the canonical registry holder of one of the mod's effects.
     *
     * <p>{@code LivingEntity#hasEffect}, {@code #getEffect} and {@code #removeEffect} look the
     * passed holder up in a map that is keyed by the holder the effect was applied with, and they
     * use plain equality for that. A {@code RegistrySupplier} is only <em>a</em> holder
     * implementation - never the one the registry itself created - so it can neither apply nor
     * match an effect. Everything therefore has to go through the holder the registry knows.</p>
     */
    public static Holder<MobEffect> holder(RegistrySupplier<MobEffect> effect) {
        // Note: getRegistryId() is the id of the registry (minecraft:mob_effect); the id of the
        // entry itself is getId(), which is what DeferredSupplier#getKey() is built from.
        return BuiltInRegistries.MOB_EFFECT.getHolderOrThrow(effect.getKey());
    }

    /** {@code entity.hasEffect(...)} for one of the mod's effects, using the canonical holder. */
    public static boolean hasEffect(LivingEntity entity, RegistrySupplier<MobEffect> effect) {
        return entity.hasEffect(holder(effect));
    }

    public static MobEffectInstance instance(RegistrySupplier<MobEffect> effect, int duration) {
        return new MobEffectInstance(holder(effect), duration);
    }

    private static void applyCenterOfWeakness(LivingEntity entity, int amplifier) {
        double range = 10.0D;
        double innerRange = 4.0D;
        AABB area = new AABB(entity.getX() - range, entity.getY() - range, entity.getZ() - range,
                entity.getX() + range, entity.getY() + range, entity.getZ() + range);

        for (Mob mob : entity.level().getEntitiesOfClass(Mob.class, area)) {
            boolean hostile = mob.getTarget() == entity || mob.getLastHurtByMob() == entity || mob instanceof Enemy;
            if (mob.is(entity) || mob.isAlliedTo(entity) || !hostile) {
                continue;
            }

            double distance = entity.distanceTo(mob);
            if (distance <= innerRange) {
                mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 10, 1, false, false));
            } else if (distance <= range) {
                mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 10, 0, false, false));
            }
        }
    }
}
