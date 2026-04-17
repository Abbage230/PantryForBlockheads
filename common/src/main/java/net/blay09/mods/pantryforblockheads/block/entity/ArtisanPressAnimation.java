package net.blay09.mods.pantryforblockheads.block.entity;

import net.minecraft.util.Mth;

public final class ArtisanPressAnimation {
    public static final int CYCLE_LENGTH = 20;
    public static final int DROP_TICKS = 4;
    public static final int IMPACT_HOLD_TICKS = 3;
    public static final int LIFT_TICKS = CYCLE_LENGTH - DROP_TICKS - IMPACT_HOLD_TICKS;
    public static final float ANVIL_TRAVEL = 0.4f;
    public static final float CHAIN_ROTATION_DEGREES = 360f;

    private ArtisanPressAnimation() {
    }

    public static AnimationState sample(boolean processing, int animationTick, float partialTick) {
        if (!processing) {
            return AnimationState.IDLE;
        }

        final float currentAnimationTick = Math.max(0f, animationTick + partialTick);
        final float cycleTick = currentAnimationTick % CYCLE_LENGTH;
        if (cycleTick < DROP_TICKS) {
            final float progressInPhase = cycleTick / DROP_TICKS;
            return new AnimationState(true, easeInCubic(progressInPhase) * ANVIL_TRAVEL, 0f, false);
        }

        if (cycleTick < DROP_TICKS + IMPACT_HOLD_TICKS) {
            return new AnimationState(true, ANVIL_TRAVEL, 0f, true);
        }

        final float liftTick = cycleTick - DROP_TICKS - IMPACT_HOLD_TICKS;
        final float progressInPhase = liftTick / LIFT_TICKS;
        final float easedProgress = easeOutSine(progressInPhase);
        return new AnimationState(true, (1f - easedProgress) * ANVIL_TRAVEL, easedProgress * CHAIN_ROTATION_DEGREES, false);
    }

    public static boolean isImpactTick(int animationTick) {
        return animationTick % CYCLE_LENGTH == DROP_TICKS;
    }

    private static float easeInCubic(float value) {
        final float clamped = Mth.clamp(value, 0f, 1f);
        return clamped * clamped * clamped;
    }

    private static float easeOutSine(float value) {
        return Mth.sin(Mth.clamp(value, 0f, 1f) * Mth.HALF_PI);
    }

    public record AnimationState(boolean active, float anvilOffset, float chainRotation, boolean impactPhase) {
        private static final AnimationState IDLE = new AnimationState(false, 0f, 0f, false);
    }
}
