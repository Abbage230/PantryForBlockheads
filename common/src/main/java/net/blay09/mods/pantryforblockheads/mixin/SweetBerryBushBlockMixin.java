package net.blay09.mods.pantryforblockheads.mixin;

import net.blay09.mods.pantryforblockheads.PantryForBlockheads;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SweetBerryBushBlock.class)
public class SweetBerryBushBlockMixin {

    @Inject(method = "entityInside(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/InsideBlockEffectApplier;Z)V", at = @At("HEAD"), cancellable = true)
    private void init(CallbackInfo ci) {
        if (PantryForBlockheads.config().vanillaModifications.removeSweetBerryBushDamage) {
            ci.cancel();
        }
    }
}
