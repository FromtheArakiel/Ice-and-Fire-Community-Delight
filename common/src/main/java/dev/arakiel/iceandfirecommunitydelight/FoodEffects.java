package dev.arakiel.iceandfirecommunitydelight;

import dev.architectury.registry.registries.RegistrySupplier;
import dev.arakiel.iceandfirecommunitydelight.util.Advancements;
import dev.arakiel.iceandfirecommunitydelight.util.ExplosionCounter;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.registry.ModEffects;

/**
 * Everything that happens when one of the mod's consumables is finished.
 *
 * <p>Replaces the ~30 {@code *WhenEatedProcedure} / {@code *WhenDrinkedProcedure} classes of the
 * 1.20.1 sources. The original pie blocks duplicated the effect of their own slice through a
 * separate right click handler; here the block and its slice share the same method.</p>
 */
public final class FoodEffects {
    private FoodEffects() {
    }

    // ------------------------------------------------------------------ special pastries

    public static void dragonSpecialPieSlice(LivingEntity entity) {
        dragonPieBase(entity, 9600, 7200);
        explode(entity, Level.ExplosionInteraction.MOB, CommonConfig.enableExplosionsWhenEatingSpecialPieSlice,
                CommonConfig.chanceExplosionsWhenEatingSpecialPieSlice, CommonConfig.deathsBeforeExplosionsStopSpecialPieSlice);
    }

    public static void dragonSpecialPieBlock(LivingEntity entity) {
        dragonPieBase(entity, 9600, 7200);
        explode(entity, Level.ExplosionInteraction.BLOCK, CommonConfig.enableExplosionsWhenEatingSpecialPie,
                CommonConfig.chanceExplosionsWhenEatingSpecialPie, CommonConfig.deathsBeforeExplosionsStopSpecialPie);
    }

    public static void dragonSpecialSausage(LivingEntity entity) {
        dragonPieBase(entity, 24000, 18000);
        explode(entity, Level.ExplosionInteraction.MOB, CommonConfig.enableExplosionsWhenEatingSpecialSausage,
                CommonConfig.chanceExplosionsWhenEatingSpecialSausage, CommonConfig.deathsBeforeExplosionsStopSpecialSausage);
    }

    private static void dragonPieBase(LivingEntity entity, int flight, int might) {
        add(entity, MobEffectRegistry.DRAGON_FLIGHT, flight);
        add(entity, MobEffectRegistry.DRAGONS_MIGHT, might);
        Advancements.award(entity, "almost_4_elements");
    }

    private static void explode(LivingEntity entity, Level.ExplosionInteraction interaction, boolean enabled,
                                double chance, double limit) {
        Level level = entity.level();
        if (level.isClientSide() || !enabled) {
            return;
        }
        if (Math.random() >= chance) {
            return;
        }
        if (ExplosionCounter.get(entity) > limit - 1.0D) {
            return;
        }

        Advancements.award(entity, "too_much_power");
        ExplosionCounter.increment(entity);
        level.explode(null, entity.getX(), entity.getY(), entity.getZ(), 4.0F, false, interaction);
    }

    // ------------------------------------------------------------------ dragon pastries

    public static void fieryHot(LivingEntity entity) {
        add(entity, MobEffectRegistry.FIRE_ASPECT, 4800);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 2400);
    }

    public static void frost(LivingEntity entity) {
        add(entity, MobEffectRegistry.ICE_ASPECT, 4800);
        add(entity, MobEffectRegistry.WARMING, 2400);
    }

    public static void electric(LivingEntity entity) {
        add(entity, MobEffectRegistry.LIGHTNING_STRIKE, 4800);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 2400);
    }

    // ------------------------------------------------------------------ meals

    public static void fireDragonRamen(LivingEntity entity) {
        add(entity, MobEffectRegistry.FIRE_ASPECT, 1000);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 1000);
    }

    public static void fireHeart(LivingEntity entity) {
        add(entity, MobEffectRegistry.DRAGON_FLIGHT, 6000);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 18000);
        add(entity, MobEffectRegistry.FIRE_ASPECT, 6000);
    }

    public static void iceHeart(LivingEntity entity) {
        add(entity, MobEffectRegistry.DRAGON_FLIGHT, 6000);
        add(entity, MobEffectRegistry.WARMING, 18000);
        add(entity, MobEffectRegistry.ICE_ASPECT, 6000);
    }

    public static void lightningHeart(LivingEntity entity) {
        add(entity, MobEffectRegistry.DRAGON_FLIGHT, 6000);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 18000);
        add(entity, MobEffectRegistry.LIGHTNING_STRIKE, 6000);
    }

    public static void coolSandwich(LivingEntity entity) {
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, (int) (Math.random() * 500.0D + 700.0D));
    }

    public static void lightningDragonHotdog(LivingEntity entity) {
        add(entity, MobEffectRegistry.LIGHTNING_STRIKE, 16000);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 8000);
    }

    public static void eyeChowder(LivingEntity entity) {
        add(entity, MobEffectRegistry.CENTER_OF_WEAKNESS, 21000);
    }

    public static void honeyGlazedCyclopsEye(LivingEntity entity) {
        add(entity, MobEffectRegistry.CENTER_OF_WEAKNESS, 18000);
    }

    public static void friedDragonEgg(LivingEntity entity) {
        if (!entity.level().isClientSide()) {
            entity.addEffect(new MobEffectInstance(ModEffects.NOURISHMENT, 9000));
        }
    }

    // ------------------------------------------------------------------ sausages & meat

    public static void fireSausage(LivingEntity entity) {
        add(entity, MobEffectRegistry.FIRE_ASPECT, 12000);
    }

    public static void iceSausage(LivingEntity entity) {
        add(entity, MobEffectRegistry.ICE_ASPECT, 12000);
    }

    public static void lightningSausage(LivingEntity entity) {
        add(entity, MobEffectRegistry.LIGHTNING_STRIKE, 12000);
    }

    public static void hydraMeat(LivingEntity entity) {
        if (Math.random() < 0.7D) {
            addVanilla(entity, MobEffects.POISON, 200);
        } else {
            add(entity, MobEffectRegistry.POISON_RESISTANCE, 700);
        }
    }

    // ------------------------------------------------------------------ drinks

    public static void fieryHotCocktail(LivingEntity entity) {
        add(entity, MobEffectRegistry.FIRE_ASPECT, 10000);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 8000);
    }

    public static void frostCocktail(LivingEntity entity) {
        add(entity, MobEffectRegistry.ICE_ASPECT, 10000);
        add(entity, MobEffectRegistry.WARMING, 8000);
    }

    public static void electricCocktail(LivingEntity entity) {
        add(entity, MobEffectRegistry.LIGHTNING_STRIKE, 10000);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 8000);
    }

    public static void specialCocktail(LivingEntity entity) {
        Advancements.award(entity, "almost_4_elements");
        add(entity, MobEffectRegistry.DRAGONS_MIGHT, 14000);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 9000);
        add(entity, MobEffectRegistry.WARMING, 9000);
    }

    public static void fireLilyExtract(LivingEntity entity) {
        add(entity, MobEffectRegistry.FIRE_ASPECT, 500);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 500);
    }

    public static void frostLilyExtract(LivingEntity entity) {
        add(entity, MobEffectRegistry.ICE_ASPECT, 500);
        add(entity, MobEffectRegistry.WARMING, 500);
    }

    public static void lightningLilyExtract(LivingEntity entity) {
        add(entity, MobEffectRegistry.LIGHTNING_STRIKE, 500);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 500);
    }

    public static void hydraVenomSoup(LivingEntity entity) {
        if (Math.random() < 0.9D) {
            add(entity, MobEffectRegistry.POISON_RESISTANCE, 18000);
        } else {
            addVanilla(entity, MobEffects.POISON, 1800);
        }
    }

    public static void spicyChips(LivingEntity entity) {
        add(entity, MobEffectRegistry.FIRE_ASPECT, 200);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 600);
    }

    // ------------------------------------------------------------------ helpers

    private static void add(LivingEntity entity, RegistrySupplier<MobEffect> effect, int duration) {
        if (!entity.level().isClientSide()) {
            entity.addEffect(new MobEffectInstance(MobEffectRegistry.holder(effect), duration));
        }
    }

    private static void addVanilla(LivingEntity entity, Holder<MobEffect> effect, int duration) {
        if (!entity.level().isClientSide()) {
            entity.addEffect(new MobEffectInstance(effect, duration));
        }
    }
}
