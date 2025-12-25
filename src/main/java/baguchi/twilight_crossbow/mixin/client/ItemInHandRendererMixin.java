package baguchi.twilight_crossbow.mixin.client;

import baguchi.twilight_crossbow.init.ModItems;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {

    @Inject(method = "isChargedCrossbow", at = @At("HEAD"), cancellable = true)
    private static void isChargedCrossbow(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.is(ModItems.TRIPLE_CROSSBOW) || stack.is(ModItems.ICE_CROSSBOW) || stack.is(ModItems.SEEKER_CROSSBOW)) {
            cir.setReturnValue(CrossbowItem.isCharged(stack));
        }
    }
}
