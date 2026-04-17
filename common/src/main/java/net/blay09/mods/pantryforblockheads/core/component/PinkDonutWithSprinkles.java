package net.blay09.mods.pantryforblockheads.core.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.blay09.mods.pantryforblockheads.PantryForBlockheads;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.ConsumableListener;
import net.minecraft.world.level.Level;

public class PinkDonutWithSprinkles implements ConsumableListener {
    public static final PinkDonutWithSprinkles INSTANCE = new PinkDonutWithSprinkles();
    public static final Codec<PinkDonutWithSprinkles> CODEC = MapCodec.unitCodec(INSTANCE);

    public static DataComponentType<PinkDonutWithSprinkles> type() {
        return PantryForBlockheads.dataComponents().pinkDonutWithSprinkles.value();
    }

    @Override
    public void onConsume(Level level, LivingEntity user, ItemStack stack, Consumable consumable) {
        if (PantryForBlockheads.config().foodEffects.pinkDonutWithSprinkles) {
            user.addEffect(new MobEffectInstance(MobEffects.SPEED, 3 * 60 * 20));
            user.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 3 * 60 * 20));
            user.addEffect(new MobEffectInstance(MobEffects.HASTE, 3 * 60 * 20, 1));
        }
    }

    @Override
    @SuppressWarnings("RedundantMethodOverride")
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
