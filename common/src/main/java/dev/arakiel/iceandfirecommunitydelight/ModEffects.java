package dev.arakiel.iceandfirecommunitydelight;

import dev.arakiel.iceandfirecommunitydelight.effect.DelightMobEffect;
import dev.arakiel.iceandfirecommunitydelight.util.DelightAdvancements;
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
public final class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(IceAndFireDelight.MOD_ID, Registries.MOB_EFFECT);

    public static final RegistrySupplier<MobEffect> FIRE_ASPECT =
            register("fire_aspect", () -> new DelightMobEffect(MobEffectCategory.BENEFICIAL, -52686));

    public static final RegistrySupplier<MobEffect> ICE_ASPECT =
            register("ice_aspect", () -> new DelightMobEffect(MobEffectCategory.BENEFICIAL, -13474841));

    public static final RegistrySupplier<MobEffect> LIGHTNING_STRIKE =
            register("lightning_strike", () -> new DelightMobEffect(MobEffectCategory.BENEFICIAL, -1708222));

    public static final RegistrySupplier<MobEffect> POISON_RESISTANCE =
            register("poison_resistance", () -> new DelightMobEffect(MobEffectCategory.BENEFICIAL, -10053376)
                    .onTick((entity, amplifier) -> entity.removeEffect(MobEffects.POISON)));

    public static final RegistrySupplier<MobEffect> WARMING =
            register("warming", () -> new DelightMobEffect(MobEffectCategory.BENEFICIAL, -6710785));

    public static final RegistrySupplier<MobEffect> DRAGON_FLIGHT =
            register("dragon_flight", () -> new DelightMobEffect(MobEffectCategory.BENEFICIAL, -39322)
                    .onTick((entity, amplifier) -> {
                        if (entity instanceof Player player) {
                            player.getAbilities().mayfly = true;
                            ModEvents.markFlight(player);
                        }
                    })
                    .onStarted(entity -> DelightAdvancements.award(entity, "feel_like_a_dragon_adv")));

    public static final RegistrySupplier<MobEffect> DRAGONS_MIGHT =
            register("dragons_might", () -> new DelightMobEffect(MobEffectCategory.BENEFICIAL, -16737895)
                    .onStarted(entity -> DelightAdvancements.award(entity, "power_of_three_dragons_adv")));

    public static final RegistrySupplier<MobEffect> CENTER_OF_WEAKNESS =
            register("center_of_weakness", () -> new DelightMobEffect(MobEffectCategory.BENEFICIAL, -3355444)
                    .onTick(ModEffects::applyCenterOfWeakness));

    private ModEffects() {
    }

    private static RegistrySupplier<MobEffect> register(String name, Supplier<MobEffect> supplier) {
        // Registered under the mod namespace so the shipped textures and language files apply.
        return EFFECTS.register(IceAndFireDelight.id(name), supplier);
    }

    public static void register() {
        EFFECTS.register();
    }

    /** {@code RegistrySupplier} -> {@code Holder} so the effects can be applied like vanilla ones. */
    public static Holder<MobEffect> holder(RegistrySupplier<MobEffect> effect) {
        return BuiltInRegistries.MOB_EFFECT.wrapAsHolder(effect.get());
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
