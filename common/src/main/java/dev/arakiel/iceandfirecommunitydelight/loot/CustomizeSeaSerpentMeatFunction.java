package dev.arakiel.iceandfirecommunitydelight.loot;

import com.iafenvoy.iceandfire.entity.SeaSerpentEntity;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.arakiel.iceandfirecommunitydelight.ItemRegistry;
import dev.arakiel.iceandfirecommunitydelight.LootFunctions;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;

/**
 * Scales the sea serpent meat drop with the size of the killed serpent.
 *
 * <p>Ported from the new 1.20.1 sources ({@code CustomizeToSeaSerpentMeat}). The original used the
 * removed json serializer API; the same logic is expressed with the 1.21 codec based loot function
 * API here.</p>
 */
public class CustomizeSeaSerpentMeatFunction extends LootItemConditionalFunction {
    public static final MapCodec<CustomizeSeaSerpentMeatFunction> CODEC = RecordCodecBuilder.mapCodec(instance ->
            commonFields(instance).apply(instance, CustomizeSeaSerpentMeatFunction::new));

    protected CustomizeSeaSerpentMeatFunction(List<LootItemCondition> conditions) {
        super(conditions);
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext context) {
        if (stack.isEmpty()) {
            return stack;
        }
        if (!(context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof SeaSerpentEntity serpent)) {
            return stack;
        }
        if (!stack.is(ItemRegistry.SEA_SERPENT_MEAT.get()) && !stack.is(ItemRegistry.COOKED_SEA_SERPENT_MEAT.get())) {
            return stack;
        }

        int ancientModifier = serpent.isAncient() ? 2 : 1;
        int maximum = (int) Math.ceil(serpent.getSeaSerpentScale() * 3.0D * ancientModifier);
        stack.setCount(1 + serpent.getRandom().nextInt(1 + maximum));
        return stack;
    }

    @Override
    public LootItemFunctionType<? extends LootItemConditionalFunction> getType() {
        return LootFunctions.CUSTOMIZE_TO_SEA_SERPENT_MEAT.get();
    }
}
