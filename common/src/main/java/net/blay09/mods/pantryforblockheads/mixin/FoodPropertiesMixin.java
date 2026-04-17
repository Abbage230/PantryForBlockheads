package net.blay09.mods.pantryforblockheads.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.blay09.mods.pantryforblockheads.PantryForBlockheads;
import net.blay09.mods.pantryforblockheads.tag.ModItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FoodProperties.class)
public class FoodPropertiesMixin {
    @WrapOperation(method = "onConsume(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/component/Consumable;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;eat(Lnet/minecraft/world/food/FoodProperties;)V"))
    public void eat(FoodData instance, FoodProperties foodProperties, Operation<Void> original, Level level, LivingEntity user, ItemStack stack) {
        if (user instanceof Player player) {
            final var previousNutrition = player.getFoodData().getFoodLevel();
            final var overflowNutrition = previousNutrition + foodProperties.nutrition();
            original.call(instance, foodProperties);
            final var newNutrition = player.getFoodData().getFoodLevel();
            final var excessNutrition = overflowNutrition - newNutrition;
            PantryForBlockheads.applyAfterEatEffects(player, stack, excessNutrition);
        } else {
            original.call(instance, foodProperties);
        }
    }
}
