package dev.arakiel.iceandfirecommunitydelight.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * One configurable effect implementation for all eight mod effects.
 *
 * <p>Replaces the eight separate {@code *MobEffect} classes of the original mod.</p>
 */
public class ConfigurableMobEffect extends MobEffect {
    private BiConsumer<LivingEntity, Integer> tickAction;
    private Consumer<LivingEntity> startAction;

    public ConfigurableMobEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    public ConfigurableMobEffect onTick(BiConsumer<LivingEntity, Integer> action) {
        this.tickAction = action;
        return this;
    }

    public ConfigurableMobEffect onStarted(Consumer<LivingEntity> action) {
        this.startAction = action;
        return this;
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (this.tickAction != null) {
            this.tickAction.accept(entity, amplifier);
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return this.tickAction != null;
    }

    @Override
    public void onEffectStarted(LivingEntity entity, int amplifier) {
        if (this.startAction != null) {
            this.startAction.accept(entity);
        }
    }
}
