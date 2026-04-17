package net.blay09.mods.pantryforblockheads.core.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.blay09.mods.balm.Balm;
import net.blay09.mods.pantryforblockheads.PantryForBlockheads;
import net.blay09.mods.pantryforblockheads.network.ShowFortuneMessagePayload;
import net.blay09.mods.pantryforblockheads.platform.attachment.PlayerFortune;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Util;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.ConsumableListener;
import net.minecraft.world.level.Level;

import java.util.List;

public class FortuneCookie implements ConsumableListener {

    public static final int FORTUNE_COOLDOWN_TICKS = 40 * 60 * 20;
    public static final int FORTUNE_EFFECT_TICKS = 30 * 60 * 20;

    public record FortuneEffect(Component message, Holder<MobEffect> mobEffect, IntProvider duration,
                                IntProvider amplifier, boolean showParticles) {
    }

    public static final List<Component> COOLDOWN_MESSAGES = List.of(
            Component.translatable("chat.pantryforblockheads.fortune_cookie.blank"),
            Component.translatable("chat.pantryforblockheads.fortune_cookie.no_rerolls"),
            Component.translatable("chat.pantryforblockheads.fortune_cookie.stolen_by_fox"),
            Component.translatable("chat.pantryforblockheads.fortune_cookie.stop_eating_cookies"),
            Component.translatable("chat.pantryforblockheads.fortune_cookie.lemons"),
            Component.translatable("chat.pantryforblockheads.fortune_cookie.accept_cookies"),
            Component.translatable("chat.pantryforblockheads.fortune_cookie.passage_of_time"),
            Component.translatable("chat.pantryforblockheads.fortune_cookie.adventure_not_found"),
            Component.translatable("chat.pantryforblockheads.fortune_cookie.unable_to_print"),
            Component.translatable("chat.pantryforblockheads.fortune_cookie.milk_cures"),
            Component.translatable("chat.pantryforblockheads.fortune_cookie.create_fortune"),
            Component.translatable("chat.pantryforblockheads.fortune_cookie.slow_down"),
            Component.translatable("chat.pantryforblockheads.fortune_cookie.hesitate"),
            Component.translatable("chat.pantryforblockheads.fortune_cookie.prisoner")
    );

    public static final List<FortuneEffect> FORTUNES = List.of(
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.speed"), MobEffects.SPEED, ConstantInt.of(FORTUNE_EFFECT_TICKS), UniformInt.of(0, 2), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.slowness"), MobEffects.SLOWNESS, ConstantInt.of(FORTUNE_EFFECT_TICKS), UniformInt.of(0, 1), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.haste"), MobEffects.HASTE, ConstantInt.of(FORTUNE_EFFECT_TICKS), UniformInt.of(0, 3), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.mining_fatigue"), MobEffects.MINING_FATIGUE, ConstantInt.of(FORTUNE_EFFECT_TICKS), ConstantInt.of(0), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.strength"), MobEffects.STRENGTH, ConstantInt.of(FORTUNE_EFFECT_TICKS), UniformInt.of(0, 3), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.jump_boost"), MobEffects.JUMP_BOOST, ConstantInt.of(FORTUNE_EFFECT_TICKS), UniformInt.of(0, 2), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.regeneration"), MobEffects.REGENERATION, ConstantInt.of(FORTUNE_EFFECT_TICKS), UniformInt.of(0, 2), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.resistance"), MobEffects.RESISTANCE, ConstantInt.of(FORTUNE_EFFECT_TICKS), UniformInt.of(0, 3), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.fire_resistance"), MobEffects.FIRE_RESISTANCE, ConstantInt.of(FORTUNE_EFFECT_TICKS), ConstantInt.of(0), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.water_breathing"), MobEffects.WATER_BREATHING, ConstantInt.of(FORTUNE_EFFECT_TICKS), ConstantInt.of(0), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.night_vision"), MobEffects.NIGHT_VISION, ConstantInt.of(FORTUNE_EFFECT_TICKS), ConstantInt.of(0), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.hunger"), MobEffects.HUNGER, ConstantInt.of(FORTUNE_EFFECT_TICKS), UniformInt.of(0, 3), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.weakness"), MobEffects.WEAKNESS, ConstantInt.of(FORTUNE_EFFECT_TICKS), ConstantInt.of(0), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.poison"), MobEffects.POISON, ConstantInt.of(FORTUNE_EFFECT_TICKS), ConstantInt.of(0), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.wither"), MobEffects.WITHER, ConstantInt.of(FORTUNE_EFFECT_TICKS), ConstantInt.of(0), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.health_boost"), MobEffects.HEALTH_BOOST, ConstantInt.of(FORTUNE_EFFECT_TICKS), UniformInt.of(0, 4), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.absorption"), MobEffects.ABSORPTION, ConstantInt.of(FORTUNE_EFFECT_TICKS), UniformInt.of(0, 4), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.saturation"), MobEffects.SATURATION, ConstantInt.of(FORTUNE_EFFECT_TICKS), ConstantInt.of(0), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.glowing"), MobEffects.GLOWING, ConstantInt.of(FORTUNE_EFFECT_TICKS), ConstantInt.of(0), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.luck"), MobEffects.LUCK, ConstantInt.of(FORTUNE_EFFECT_TICKS), UniformInt.of(0, 4), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.unluck"), MobEffects.UNLUCK, ConstantInt.of(FORTUNE_EFFECT_TICKS), UniformInt.of(0, 4), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.bad_omen"), MobEffects.BAD_OMEN, ConstantInt.of(FORTUNE_EFFECT_TICKS), ConstantInt.of(0), false),
            new FortuneEffect(Component.translatable("chat.pantryforblockheads.fortune_cookie.oozing"), MobEffects.OOZING, ConstantInt.of(FORTUNE_EFFECT_TICKS), ConstantInt.of(0), true)
    );

    public static final FortuneCookie INSTANCE = new FortuneCookie();
    public static final Codec<FortuneCookie> CODEC = MapCodec.unitCodec(INSTANCE);

    public static DataComponentType<FortuneCookie> type() {
        return PantryForBlockheads.dataComponents().fortuneCookie.value();
    }

    @Override
    public void onConsume(Level level, LivingEntity livingEntity, ItemStack itemStack, Consumable consumable) {
        if (level.isClientSide()) {
            return;
        }

        final var playerFortune = PlayerFortune.lookup().getOrCreate(livingEntity);
        final var random = livingEntity.getRandom();
        if (playerFortune.getFortuneCooldownTicks() > 0) {
            if (livingEntity instanceof Player player) {
                final var message = Util.getRandom(COOLDOWN_MESSAGES, random);
                Balm.networking().sendTo(player, new ShowFortuneMessagePayload(message));
            }
            return;
        }

        final var fortune = Util.getRandom(FORTUNES, random);
        if (livingEntity instanceof Player player) {
            Balm.networking().sendTo(player, new ShowFortuneMessagePayload(fortune.message()));
        }
        if (PantryForBlockheads.config().foodEffects.fortuneCookies) {
            livingEntity.addEffect(new MobEffectInstance(fortune.mobEffect(), fortune.duration().sample(random), fortune.amplifier().sample(random), false, fortune.showParticles(), false));
        }
        playerFortune.setFortuneCooldownTicks(FORTUNE_COOLDOWN_TICKS);
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
