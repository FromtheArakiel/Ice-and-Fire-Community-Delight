package dev.arakiel.iceandfirecommunitydelight;

import dev.arakiel.iceandfirecommunitydelight.util.IafCompat;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * All on-hit logic of the mod.
 *
 * <p>Merges {@code ModEventHandler}, {@code Effect_Fire_Aspect_When_ActiveProcedure},
 * {@code EffectDragonsMightWhenActiveProcedure}, {@code EffectLightningStrikeWhenActiveProcedure},
 * {@code HydraFangKnifeWhenAttackProcedure} and {@code SilverKnifeWhenAttackProcedure} into a
 * single hurt listener.</p>
 */
public final class ModCombat {
    private ModCombat() {
    }

    /**
     * Runs for every living entity that is about to take damage. Effects only trigger for melee
     * hits with a direct attacker, exactly like the 1.20.1 event handlers did.
     */
    public static void onLivingHurt(LivingEntity victim, DamageSource source, float amount) {
        Entity attackerEntity = source.getEntity();
        if (!(attackerEntity instanceof LivingEntity attacker) || attacker == victim) {
            return;
        }

        if (attacker.hasEffect(ModEffects.FIRE_ASPECT)) {
            victim.igniteForSeconds(15);
        }

        if (attacker.hasEffect(ModEffects.DRAGONS_MIGHT)) {
            victim.igniteForSeconds(15);
            victim.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300, 2));
            strikeWithLightning(victim);
        }

        if (attacker.hasEffect(ModEffects.LIGHTNING_STRIKE)) {
            strikeWithLightning(victim);
            victim.igniteForSeconds(3);
        }

        if (attacker.hasEffect(ModEffects.ICE_ASPECT)) {
            frostbite(victim, attacker);
        }

        if (attacker.isHolding(ModItems.HYDRA_FANG_KNIFE.get())) {
            victim.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 0));
        }

        // 1.21 移除了 MobType 枚举，isInvertedHealAndHarm() 就是原版的"亡灵"判定。
        if (attacker.isHolding(ModItems.SILVER_KNIFE.get()) && victim.isInvertedHealAndHarm()) {
            victim.hurt(damageSource(victim.level(), DamageTypes.GENERIC), 2.0F);
        }
    }

    /** Ice aspect and the dragonsteel ice knife: deep freeze, slow and a knockback. */
    public static void frostbite(LivingEntity victim, LivingEntity attacker) {
        if (victim.hasEffect(ModEffects.WARMING)) {
            return;
        }
        IafCompat.freeze(victim, 300);
        victim.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300, 2));
        victim.knockback(1.0F, attacker.getX() - victim.getX(), attacker.getZ() - victim.getZ());
    }

    /** Dragonsteel fire knife. */
    public static void igniteFromKnife(LivingEntity victim, LivingEntity attacker) {
        victim.igniteForSeconds(15);
    }

    /** Dragonsteel lightning knife. */
    public static void lightningFromKnife(LivingEntity victim, LivingEntity attacker) {
        strikeWithLightning(victim);
    }

    /** Spawns a purely cosmetic bolt and deals the matching lightning damage. */
    private static void strikeWithLightning(LivingEntity victim) {
        Level level = victim.level();
        if (level instanceof ServerLevel serverLevel) {
            LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(serverLevel);
            if (bolt != null) {
                bolt.moveTo(Vec3.atBottomCenterOf(victim.blockPosition()));
                bolt.setVisualOnly(true);
                serverLevel.addFreshEntity(bolt);
            }
        }
        victim.hurt(damageSource(level, DamageTypes.LIGHTNING_BOLT), 10.0F);
    }

    private static DamageSource damageSource(Level level, ResourceKey<DamageType> type) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type));
    }

}
