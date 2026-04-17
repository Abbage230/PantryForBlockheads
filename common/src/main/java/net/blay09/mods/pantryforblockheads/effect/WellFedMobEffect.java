package net.blay09.mods.pantryforblockheads.effect;

import net.blay09.mods.balm.world.effect.CustomMobEffect;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class WellFedMobEffect extends CustomMobEffect {
    public WellFedMobEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        return entity.getAbsorptionAmount() > 0.0F;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onEffectStarted(LivingEntity entity, int amplifier) {
        super.onEffectStarted(entity, amplifier);
        entity.setAbsorptionAmount(Math.max(entity.getAbsorptionAmount(), (1 + amplifier)));
    }
}
