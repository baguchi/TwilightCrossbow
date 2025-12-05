package baguchi.twilight_crossbow.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Unit;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripleCrossBowItem extends CrossbowItem {
    private static final CrossbowItem.ChargingSounds DEFAULT_SOUNDS = new CrossbowItem.ChargingSounds(
            Optional.of(SoundEvents.CROSSBOW_LOADING_START), Optional.of(SoundEvents.CROSSBOW_LOADING_MIDDLE), Optional.of(SoundEvents.CROSSBOW_LOADING_END)
    );

    public TripleCrossBowItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
        int i = this.getUseDuration(stack, entityLiving) - timeLeft;
        float f = getPowerForTime(i, stack, entityLiving);
        if (f >= 1.0F && !isCharged(stack) && tryLoadProjectiles(entityLiving, stack)) {
            ChargingSounds crossbowitem$chargingsounds = this.getChargingSounds(stack);
            crossbowitem$chargingsounds.end().ifPresent((p_352852_) -> level.playSound((Player)null, entityLiving.getX(), entityLiving.getY(), entityLiving.getZ(), (SoundEvent)p_352852_.value(), entityLiving.getSoundSource(), 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F));
        }

    }

    ChargingSounds getChargingSounds(ItemStack stack) {
        return (ChargingSounds)EnchantmentHelper.pickHighestLevel(stack, EnchantmentEffectComponents.CROSSBOW_CHARGING_SOUNDS).orElse(DEFAULT_SOUNDS);
    }

    private static float getPowerForTime(int timeLeft, ItemStack stack, LivingEntity shooter) {
        float f = (float)timeLeft / (float)getChargeDuration(stack, shooter);
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    protected static List<ItemStack> draw(ItemStack weapon, ItemStack ammo, LivingEntity shooter) {
        if (ammo.isEmpty()) {
            return List.of();
        } else {
            Level var5 = shooter.level();
            int var10000;
            if (var5 instanceof ServerLevel) {
                ServerLevel serverlevel = (ServerLevel)var5;
                var10000 = EnchantmentHelper.processProjectileCount(serverlevel, weapon, shooter, 3);
            } else {
                var10000 = 3;
            }

            int i = var10000;
            List<ItemStack> list = new ArrayList(i);
            ItemStack itemstack1 = ammo.copy();

            for(int j = 0; j < i; ++j) {
                ItemStack itemstack = useAmmo(weapon, j == 0 ? ammo : itemstack1, shooter, j > 0);
                if (!itemstack.isEmpty()) {
                    list.add(itemstack);
                }
            }

            return list;
        }
    }

    private static boolean tryLoadProjectiles(LivingEntity shooter, ItemStack crossbowStack) {
        List<ItemStack> list = draw(crossbowStack, shooter.getProjectile(crossbowStack), shooter);
        if (!list.isEmpty()) {
            crossbowStack.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.of(list));
            return true;
        } else {
            return false;
        }
    }

    public boolean useOnRelease(ItemStack stack) {
        return true;
    }
}
