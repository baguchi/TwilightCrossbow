package baguchi.twilight_crossbow.item;

import baguchi.twilight_crossbow.entity.IceFireworkRocketEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import twilightforest.entity.projectile.IceArrow;
import twilightforest.util.TFToolMaterials;

public class IceCrossBowItem extends CrossbowItem {
    public IceCrossBowItem(Properties properties) {
        super(properties);
    }

    public AbstractArrow customArrow(AbstractArrow arrow, ItemStack projectileStack, ItemStack weaponStack) {
        return new IceArrow(arrow.level(), (LivingEntity)arrow.getOwner(), projectileStack.copyWithCount(1), weaponStack);
    }

    @Override
    protected Projectile createProjectile(Level level, LivingEntity shooter, ItemStack weapon, ItemStack ammo, boolean isCrit) {
        if (ammo.is(Items.FIREWORK_ROCKET)) {
            return new IceFireworkRocketEntity(level, ammo, shooter, shooter.getX(), shooter.getEyeY() - 0.15F, shooter.getZ(), true);
        } else {
            Projectile projectile = super.createProjectile(level, shooter, weapon, ammo, isCrit);
            if (projectile instanceof AbstractArrow abstractarrow) {
                abstractarrow.setSoundEvent(SoundEvents.CROSSBOW_HIT);
            }

            return projectile;
        }
    }

    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repairWith) {
        return TFToolMaterials.ICE.getRepairIngredient().test(repairWith);
    }

    public boolean useOnRelease(ItemStack stack) {
        return true;
    }
}
