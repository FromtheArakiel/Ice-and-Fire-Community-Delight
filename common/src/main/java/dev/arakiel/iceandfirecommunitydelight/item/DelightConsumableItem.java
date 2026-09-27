package dev.arakiel.iceandfirecommunitydelight.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Shared implementation for every edible and drinkable item of the mod.
 *
 * <p>Replaces the ~45 one-class-per-food items of the 1.20.1 sources. Besides the food component
 * defined in {@link dev.arakiel.iceandfirecommunitydelight.ModItems} an item can carry an "on eat"
 * action, a container that is handed back after use and optional tooltip lines.</p>
 */
public class DelightConsumableItem extends Item {
    private final Consumer<LivingEntity> onEaten;
    private final Supplier<Item> container;
    private final boolean drink;
    private final int useDuration;
    private String[] tooltipKeys;

    public DelightConsumableItem(Properties properties, Consumer<LivingEntity> onEaten, Supplier<Item> container,
                                 boolean drink, int useDuration) {
        super(properties);
        this.onEaten = onEaten;
        this.container = container;
        this.drink = drink;
        this.useDuration = useDuration;
    }

    public DelightConsumableItem tooltip(String... keys) {
        this.tooltipKeys = keys.length == 0 ? null : keys;
        return this;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return this.drink ? UseAnim.DRINK : super.getUseAnimation(stack);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return this.useDuration > 0 ? this.useDuration : super.getUseDuration(stack, entity);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        ItemStack result = super.finishUsingItem(stack, level, consumer);

        if (this.onEaten != null && !level.isClientSide()) {
            this.onEaten.accept(consumer);
        }

        if (this.container == null) {
            return result;
        }

        ItemStack remainder = new ItemStack(this.container.get());
        if (result.isEmpty()) {
            return remainder;
        }
        if (consumer instanceof Player player && !player.getAbilities().instabuild) {
            if (!player.getInventory().add(remainder)) {
                player.drop(remainder, false);
            }
        }
        return result;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        if (this.tooltipKeys != null) {
            int index = Math.min(1, tooltip.size());
            for (String key : this.tooltipKeys) {
                tooltip.add(index++, Component.translatable(key));
            }
        }
    }
}
