package dev.arakiel.iceandfirecommunitydelight.item;

import com.iafenvoy.iceandfire.entity.GhostSwordEntity;
import dev.arakiel.iceandfirecommunitydelight.ModTiers;
import dev.arakiel.iceandfirecommunitydelight.util.IafCompat;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * Phantasmal blade: attacking throws a ghost sword projectile.
 *
 * <p>The 1.20.1 version listened to a Forge right click event and ran the spawn logic on both
 * sides. The item now handles its own use and only spawns the projectile on the server.</p>
 */
public class PhantomKnifeItem extends SwordItem {
    public PhantomKnifeItem() {
        super(ModTiers.PHANTASMAL, new Properties().attributes(SwordItem.createAttributes(ModTiers.PHANTASMAL, 5, -1.0F)));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.getCooldowns().isOnCooldown(stack.getItem())) {
            return InteractionResultHolder.fail(stack);
        }
        if (!level.isClientSide()) {
            spawnGhostSword(stack, player);
        }
        return InteractionResultHolder.success(stack);
    }

    private static void spawnGhostSword(ItemStack stack, Player player) {
        EntityType<GhostSwordEntity> type = IafCompat.ghostSwordType();
        if (type == null) {
            return;
        }

        double totalDamage = 0.0D;
        ItemAttributeModifiers modifiers = stack.get(DataComponents.ATTRIBUTE_MODIFIERS);
        if (modifiers != null) {
            for (ItemAttributeModifiers.Entry entry : modifiers.modifiers()) {
                if (entry.attribute().equals(Attributes.ATTACK_DAMAGE)) {
                    totalDamage += entry.modifier().amount();
                }
            }
        }

        player.playSound(SoundEvents.ZOMBIE_INFECT, 1.0F, 1.0F);
        GhostSwordEntity sword = new GhostSwordEntity(type, player.level(), player, (float) (totalDamage * 0.5D), stack);
        sword.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.0F, 0.5F);
        player.level().addFreshEntity(sword);
        stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
        player.getCooldowns().addCooldown(stack.getItem(), 10);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        int index = Math.min(1, tooltip.size());
        tooltip.add(index++, Component.translatable("item.iceandfire.legendary_weapon.desc"));
        tooltip.add(index++, Component.translatable("item.iceandfirecommunitydelight.tooltip.phantom_knife_line1"));
        if (Screen.hasShiftDown()) {
            tooltip.add(index++, Component.translatable("item.iceandfirecommunitydelight.tooltip.warning"));
            tooltip.add(index, Component.translatable("item.iceandfirecommunitydelight.tooltip.the_spriter_asked"));
        } else {
            tooltip.add(index, Component.translatable("item.iceandfirecommunitydelight.tooltip.developer_comment"));
        }
    }
}
