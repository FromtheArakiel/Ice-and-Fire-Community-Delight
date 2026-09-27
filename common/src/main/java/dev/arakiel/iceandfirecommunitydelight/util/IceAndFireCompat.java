package dev.arakiel.iceandfirecommunitydelight.util;

import com.iafenvoy.iceandfire.entity.GhostSwordEntity;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Optional;

/**
 * The only place that talks to Ice and Fire.
 *
 * <p>The Fabric build of Ice and Fire uses Architectury's {@code RegistrySupplier} while the
 * NeoForge build uses NeoForge's {@code DeferredHolder}. Referencing the Ice and Fire registry
 * <em>fields</em> from common code would therefore break one of the two loaders at runtime, so
 * everything is resolved lazily from the built-in registries by id instead. The ids are stable
 * data level ids that are also used by the shipped recipes, and the only Ice and Fire class we
 * touch, {@link GhostSwordEntity}, exists with an identical constructor in both builds.</p>
 */
public final class IceAndFireCompat {
    public static final String NAMESPACE = "iceandfire";

    private static Holder<MobEffect> frozenEffect;
    private static boolean frozenEffectResolved;

    private IceAndFireCompat() {
    }

    public static ResourceLocation key(String path) {
        return ResourceLocation.fromNamespaceAndPath(NAMESPACE, path);
    }

    public static ItemStack stack(String id) {
        Item item = item(id);
        return item == null ? ItemStack.EMPTY : new ItemStack(item);
    }

    public static Ingredient itemIngredient(String id) {
        Item item = item(id);
        return item == null ? Ingredient.EMPTY : Ingredient.of(item);
    }

    /** Applies Ice and Fire's {@code iceandfire:frozen} effect when Ice and Fire is installed. */
    public static void freeze(LivingEntity target, int ticks) {
        if (!frozenEffectResolved) {
            frozenEffect = BuiltInRegistries.MOB_EFFECT
                    .getHolder(ResourceKey.create(Registries.MOB_EFFECT, key("frozen")))
                    .orElse(null);
            frozenEffectResolved = true;
        }
        if (frozenEffect != null) {
            // The registry holder itself, so the effect counts as active for Ice and Fire too.
            target.addEffect(new MobEffectInstance(frozenEffect, ticks));
        }
    }

    /** @return the {@code iceandfire:ghost_sword} entity type, or {@code null} when unavailable. */
    @SuppressWarnings("unchecked")
    public static EntityType<GhostSwordEntity> ghostSwordType() {
        Optional<EntityType<?>> type = BuiltInRegistries.ENTITY_TYPE
                .getHolder(ResourceKey.create(Registries.ENTITY_TYPE, key("ghost_sword")))
                .map(Holder::value);
        return (EntityType<GhostSwordEntity>) type.orElse(null);
    }

    private static Item item(String id) {
        return BuiltInRegistries.ITEM
                .getHolder(ResourceKey.create(Registries.ITEM, ResourceLocation.parse(id)))
                .map(Holder::value)
                .orElse(null);
    }
}
