package dev.arakiel.iceandfirecommunitydelight.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.List;

/**
 * Farmer's Delight knife with the mod's own attack values and an optional on-hit action.
 *
 * <p>Farmer's Delight changed its knife constructor to take only a {@link Tier} plus item
 * properties, therefore the attack damage and speed of the 1.20.1 mod are re-applied through the
 * attribute component here.</p>
 */
public class OnHitKnifeItem extends KnifeItem {
    private final HitAction onHit;
    private String[] tooltipKeys;

    public OnHitKnifeItem(Tier tier, float attackDamage, float attackSpeed, HitAction onHit) {
        super(tier, new Item.Properties().attributes(createAttributes(tier, attackDamage, attackSpeed)));
        this.onHit = onHit;
    }

    public OnHitKnifeItem tooltip(String... keys) {
        this.tooltipKeys = keys.length == 0 ? null : keys;
        return this;
    }

    public static ItemAttributeModifiers createAttributes(Tier tier, float attackDamage, float attackSpeed) {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(BASE_ATTACK_DAMAGE_ID, attackDamage + tier.getAttackDamageBonus(), AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED,
                        new AttributeModifier(BASE_ATTACK_SPEED_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND)
                .build();
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean result = super.hurtEnemy(stack, target, attacker);
        if (this.onHit != null) {
            this.onHit.hit(target, attacker);
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

    @FunctionalInterface
    public interface HitAction {
        void hit(LivingEntity target, LivingEntity attacker);
    }
}
