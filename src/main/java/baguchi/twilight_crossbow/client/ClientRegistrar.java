package baguchi.twilight_crossbow.client;

import baguchi.twilight_crossbow.TwilightCrossBow;
import baguchi.twilight_crossbow.init.ModEntities;
import baguchi.twilight_crossbow.init.ModItems;
import net.minecraft.client.renderer.entity.FireworkEntityRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;

@EventBusSubscriber(modid = TwilightCrossBow.MODID, value = Dist.CLIENT)
public class ClientRegistrar {

    @SubscribeEvent
    public static void modelBake(ModelEvent.ModifyBakingResult event) {
        ItemProperties.register(
                ModItems.TRIPLE_CROSSBOW.asItem(),
                ResourceLocation.withDefaultNamespace("pull"),
                (p_351682_, p_351683_, p_351684_, p_351685_) -> {
                    if (p_351684_ == null) {
                        return 0.0F;
                    } else {
                        return CrossbowItem.isCharged(p_351682_)
                                ? 0.0F
                                : (float)(p_351682_.getUseDuration(p_351684_) - p_351684_.getUseItemRemainingTicks())
                                / (float)CrossbowItem.getChargeDuration(p_351682_, p_351684_);
                    }
                }
        );
        ItemProperties.register(
                ModItems.TRIPLE_CROSSBOW.asItem(),
                ResourceLocation.withDefaultNamespace("pulling"),
                (p_174605_, p_174606_, p_174607_, p_174608_) -> p_174607_ != null
                        && p_174607_.isUsingItem()
                        && p_174607_.getUseItem() == p_174605_
                        && !CrossbowItem.isCharged(p_174605_)
                        ? 1.0F
                        : 0.0F
        );
        ItemProperties.register(
                ModItems.TRIPLE_CROSSBOW.asItem(),
                ResourceLocation.withDefaultNamespace("charged"),
                (p_275891_, p_275892_, p_275893_, p_275894_) -> CrossbowItem.isCharged(p_275891_) ? 1.0F : 0.0F
        );
        ItemProperties.register(ModItems.TRIPLE_CROSSBOW.asItem(), ResourceLocation.withDefaultNamespace("firework"), (p_329796_, p_329797_, p_329798_, p_329799_) -> {
            ChargedProjectiles chargedprojectiles = p_329796_.get(DataComponents.CHARGED_PROJECTILES);
            return chargedprojectiles != null && chargedprojectiles.contains(Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
        });

        ItemProperties.register(
                ModItems.ICE_CROSSBOW.asItem(),
                ResourceLocation.withDefaultNamespace("pull"),
                (p_351682_, p_351683_, p_351684_, p_351685_) -> {
                    if (p_351684_ == null) {
                        return 0.0F;
                    } else {
                        return CrossbowItem.isCharged(p_351682_)
                                ? 0.0F
                                : (float)(p_351682_.getUseDuration(p_351684_) - p_351684_.getUseItemRemainingTicks())
                                / (float)CrossbowItem.getChargeDuration(p_351682_, p_351684_);
                    }
                }
        );
        ItemProperties.register(
                ModItems.ICE_CROSSBOW.asItem(),
                ResourceLocation.withDefaultNamespace("pulling"),
                (p_174605_, p_174606_, p_174607_, p_174608_) -> p_174607_ != null
                        && p_174607_.isUsingItem()
                        && p_174607_.getUseItem() == p_174605_
                        && !CrossbowItem.isCharged(p_174605_)
                        ? 1.0F
                        : 0.0F
        );
        ItemProperties.register(
                ModItems.ICE_CROSSBOW.asItem(),
                ResourceLocation.withDefaultNamespace("charged"),
                (p_275891_, p_275892_, p_275893_, p_275894_) -> CrossbowItem.isCharged(p_275891_) ? 1.0F : 0.0F
        );
        ItemProperties.register(ModItems.ICE_CROSSBOW.asItem(), ResourceLocation.withDefaultNamespace("firework"), (p_329796_, p_329797_, p_329798_, p_329799_) -> {
            ChargedProjectiles chargedprojectiles = p_329796_.get(DataComponents.CHARGED_PROJECTILES);
            return chargedprojectiles != null && chargedprojectiles.contains(Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
        });
    }

    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.ICE_FIREWORK.get(), IceFireworkEntityRenderer::new);
    }
}
