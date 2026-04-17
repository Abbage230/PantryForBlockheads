package net.blay09.mods.pantryforblockheads.core.component;

import net.blay09.mods.balm.core.component.BalmDataComponentTypeRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;

public class ModDataComponents {
    public final Holder<DataComponentType<FortuneCookie>> fortuneCookie;
    public final Holder<DataComponentType<PinkDonutWithSprinkles>> pinkDonutWithSprinkles;

    public ModDataComponents(BalmDataComponentTypeRegistrar registrar) {
        fortuneCookie = registrar.register("fortune_cookie", FortuneCookie.CODEC).asHolder();
        pinkDonutWithSprinkles = registrar.register("pink_donut_with_sprinkles", PinkDonutWithSprinkles.CODEC).asHolder();
    }
}
