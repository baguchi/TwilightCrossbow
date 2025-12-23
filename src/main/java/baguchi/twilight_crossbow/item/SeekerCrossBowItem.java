package baguchi.twilight_crossbow.item;

import baguchi.twilight_crossbow.entity.SeekerFireworkRocketEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import twilightforest.entity.projectile.SeekerArrow;
import twilightforest.util.TFToolMaterials;

public class SeekerCrossBowItem extends CrossbowItem {
    public SeekerCrossBowItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow customArrow(AbstractArrow arrow, ItemStack projectileStack, ItemStack weaponStack) {
        return new SeekerArrow(arrow.level(), (LivingEntity) arrow.getOwner(), projectileStack.copyWithCount(1), weaponStack);
    }

    @Override
    protected Projectile createProjectile(Level level, LivingEntity shooter, ItemStack weapon, ItemStack ammo, boolean isCrit) {
        if (ammo.is(Items.FIREWORK_ROCKET)) {
            return new SeekerFireworkRocketEntity(level, ammo, shooter, shooter.getX(), shooter.getEyeY() - 0.15F, shooter.getZ(), true);
        } else {
            Projectile projectile = super.createProjectile(level, shooter, weapon, ammo, isCrit);
            if (projectile instanceof AbstractArrow abstractarrow) {
                abstractarrow.setSoundEvent(SoundEvents.CROSSBOW_HIT);
            }

            return projectile;
        }
    }


    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repairWith) {
        return TFToolMaterials.STEELEAF.getRepairIngredient().test(repairWith);
    }

    public boolean useOnRelease(ItemStack stack) {
        return true;
    }
}
