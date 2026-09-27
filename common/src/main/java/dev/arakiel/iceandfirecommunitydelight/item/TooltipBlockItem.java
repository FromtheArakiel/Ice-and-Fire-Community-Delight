package dev.arakiel.iceandfirecommunitydelight.item;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

/**
 * Block item that adds the mod's "developer comment" style tooltip lines.
 *
 * <p>The lines are only revealed while shift is held, exactly like the 1.20.1 tooltips which were
 * driven by an item tooltip event.</p>
 */
public class TooltipBlockItem extends BlockItem {
    private static final String WARNING = "item.iceandfirecommunitydelight.tooltip.warning";
    private static final String SPOILER = "item.iceandfirecommunitydelight.tooltip.the_spriter_asked";
    private static final String TEASER = "item.iceandfirecommunitydelight.tooltip.developer_comment";

    public TooltipBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        int index = Math.min(1, tooltip.size());
        if (Screen.hasShiftDown()) {
            tooltip.add(index++, Component.translatable(WARNING));
            tooltip.add(index, Component.translatable(SPOILER));
        } else {
            tooltip.add(index, Component.translatable(TEASER));
        }
    }
}
