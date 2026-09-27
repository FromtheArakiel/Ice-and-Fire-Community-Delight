package dev.arakiel.iceandfirecommunitydelight;

import dev.architectury.registry.registries.RegistrySupplier;
import dev.arakiel.iceandfirecommunitydelight.util.DelightAdvancements;
import dev.arakiel.iceandfirecommunitydelight.util.Overload;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

/**
 * Everything that happens when one of the mod's consumables is finished.
 *
 * <p>Replaces the ~30 {@code *WhenEatedProcedure} / {@code *WhenDrinkedProcedure} classes of the
 * 1.20.1 sources. The original pie blocks duplicated the effect of their own slice through a
 * separate right click handler; here the block and its slice share the same method.</p>
 */
public final class ModFoodEffects {
    private ModFoodEffects() {
    }

    // ------------------------------------------------------------------ special pastries

    public static void dragonSpecialPieSlice(LivingEntity entity) {
        dragonPieBase(entity, 9600, 7200);
        explode(entity, Level.ExplosionInteraction.MOB, ModConfig.enableExplosionsWhenEatingSpecialPieSlice,
                ModConfig.chanceExplosionsWhenEatingSpecialPieSlice, ModConfig.deathsBeforeExplosionsStopSpecialPieSlice);
    }

    public static void dragonSpecialPieBlock(LivingEntity entity) {
        dragonPieBase(entity, 9600, 7200);
        explode(entity, Level.ExplosionInteraction.BLOCK, ModConfig.enableExplosionsWhenEatingSpecialPie,
                ModConfig.chanceExplosionsWhenEatingSpecialPie, ModConfig.deathsBeforeExplosionsStopSpecialPie);
    }

    public static void dragonSpecialSausage(LivingEntity entity) {
        dragonPieBase(entity, 24000, 18000);
        explode(entity, Level.ExplosionInteraction.MOB, ModConfig.enableExplosionsWhenEatingSpecialSausage,
                ModConfig.chanceExplosionsWhenEatingSpecialSausage, ModConfig.deathsBeforeExplosionsStopSpecialSausage);
    }

    private static void dragonPieBase(LivingEntity entity, int flight, int might) {
        add(entity, ModEffects.DRAGON_FLIGHT, flight);
        add(entity, ModEffects.DRAGONS_MIGHT, might);
        DelightAdvancements.award(entity, "almost_4_elements");
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
        if (Overload.get(entity) > limit - 1.0D) {
            return;
        }

        DelightAdvancements.award(entity, "too_much_power");
        Overload.increment(entity);
        level.explode(null, entity.getX(), entity.getY(), entity.getZ(), 4.0F, false, interaction);
    }

    // ------------------------------------------------------------------ dragon pastries

    public static void fieryHot(LivingEntity entity) {
        add(entity, ModEffects.FIRE_ASPECT, 4800);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 2400);
    }

    public static void frost(LivingEntity entity) {
        add(entity, ModEffects.ICE_ASPECT, 4800);
        add(entity, ModEffects.WARMING, 2400);
    }

    public static void electric(LivingEntity entity) {
        add(entity, ModEffects.LIGHTNING_STRIKE, 4800);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 2400);
    }

    // ------------------------------------------------------------------ meals

    public static void fireDragonRamen(LivingEntity entity) {
        add(entity, ModEffects.FIRE_ASPECT, 1000);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 1000);
    }

    public static void fireHeart(LivingEntity entity) {
        add(entity, ModEffects.DRAGON_FLIGHT, 6000);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 18000);
        add(entity, ModEffects.FIRE_ASPECT, 6000);
    }

    public static void iceHeart(LivingEntity entity) {
        add(entity, ModEffects.DRAGON_FLIGHT, 6000);
        add(entity, ModEffects.WARMING, 18000);
        add(entity, ModEffects.ICE_ASPECT, 6000);
    }

    public static void lightningHeart(LivingEntity entity) {
        add(entity, ModEffects.DRAGON_FLIGHT, 6000);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 18000);
        add(entity, ModEffects.LIGHTNING_STRIKE, 6000);
    }

    public static void coolSandwich(LivingEntity entity) {
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, (int) (Math.random() * 500.0D + 700.0D));
    }

    public static void lightningDragonHotdog(LivingEntity entity) {
        add(entity, ModEffects.LIGHTNING_STRIKE, 16000);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 8000);
    }

    public static void eyeChowder(LivingEntity entity) {
        add(entity, ModEffects.CENTER_OF_WEAKNESS, 21000);
    }

    public static void honeyGlazedCyclopsEye(LivingEntity entity) {
        add(entity, ModEffects.CENTER_OF_WEAKNESS, 18000);
    }

    public static void friedDragonEgg(LivingEntity entity) {
        if (!entity.level().isClientSide()) {
            entity.addEffect(new MobEffectInstance(vectorwing.farmersdelight.common.registry.ModEffects.NOURISHMENT, 9000));
        }
    }

    // ------------------------------------------------------------------ sausages & meat

    public static void fireSausage(LivingEntity entity) {
        add(entity, ModEffects.FIRE_ASPECT, 12000);
    }

    public static void iceSausage(LivingEntity entity) {
        add(entity, ModEffects.ICE_ASPECT, 12000);
    }

    public static void lightningSausage(LivingEntity entity) {
        add(entity, ModEffects.LIGHTNING_STRIKE, 12000);
    }

    public static void hydraMeat(LivingEntity entity) {
        if (Math.random() < 0.7D) {
            addVanilla(entity, MobEffects.POISON, 200);
        } else {
            add(entity, ModEffects.POISON_RESISTANCE, 700);
        }
    }

    public static void cookedHydraMeat(LivingEntity entity) {
        if (Math.random() <= 0.1D) {
            addVanilla(entity, MobEffects.POISON, 200);
        } else {
            add(entity, ModEffects.POISON_RESISTANCE, 1000);
        }
    }

    // ------------------------------------------------------------------ drinks

    public static void fieryHotCocktail(LivingEntity entity) {
        add(entity, ModEffects.FIRE_ASPECT, 10000);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 8000);
    }

    public static void frostCocktail(LivingEntity entity) {
        add(entity, ModEffects.ICE_ASPECT, 10000);
        add(entity, ModEffects.WARMING, 8000);
    }

    public static void electricCocktail(LivingEntity entity) {
        add(entity, ModEffects.LIGHTNING_STRIKE, 10000);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 8000);
    }

    public static void specialCocktail(LivingEntity entity) {
        DelightAdvancements.award(entity, "almost_4_elements");
        add(entity, ModEffects.DRAGONS_MIGHT, 14000);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 9000);
        add(entity, ModEffects.WARMING, 9000);
    }

    public static void fireLilyExtract(LivingEntity entity) {
        add(entity, ModEffects.FIRE_ASPECT, 500);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 500);
    }

    public static void frostLilyExtract(LivingEntity entity) {
        add(entity, ModEffects.ICE_ASPECT, 500);
        add(entity, ModEffects.WARMING, 500);
    }

    public static void lightningLilyExtract(LivingEntity entity) {
        add(entity, ModEffects.LIGHTNING_STRIKE, 500);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 500);
    }

    public static void hydraVenomSoup(LivingEntity entity) {
        if (Math.random() < 0.9D) {
            add(entity, ModEffects.POISON_RESISTANCE, 18000);
        } else {
            addVanilla(entity, MobEffects.POISON, 1800);
        }
    }

    public static void spicyChips(LivingEntity entity) {
        add(entity, ModEffects.FIRE_ASPECT, 200);
        addVanilla(entity, MobEffects.FIRE_RESISTANCE, 600);
    }

    // ------------------------------------------------------------------ helpers

    private static void add(LivingEntity entity, RegistrySupplier<MobEffect> effect, int duration) {
        if (!entity.level().isClientSide()) {
            entity.addEffect(new MobEffectInstance(ModEffects.holder(effect), duration));
        }
    }

    private static void addVanilla(LivingEntity entity, Holder<MobEffect> effect, int duration) {
        if (!entity.level().isClientSide()) {
            entity.addEffect(new MobEffectInstance(effect, duration));
        }
    }
}
