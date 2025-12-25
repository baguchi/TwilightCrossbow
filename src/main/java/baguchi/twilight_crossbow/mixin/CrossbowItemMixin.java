package baguchi.twilight_crossbow.mixin;

import baguchi.twilight_crossbow.item.KnightCrossBowItem;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin extends ProjectileWeaponItem {
    public CrossbowItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "getChargeDuration", at = @At("HEAD"), cancellable = true)
    private static void getChargeDuration(ItemStack stack, LivingEntity shooter, CallbackInfoReturnable<Integer> cir) {
        if (stack.getItem() instanceof KnightCrossBowItem) {
            float f = EnchantmentHelper.modifyCrossbowChargingTime(stack, shooter, 1.25F);
            cir.setReturnValue(Mth.floor(f * 30.0F));
        }
    }
}
