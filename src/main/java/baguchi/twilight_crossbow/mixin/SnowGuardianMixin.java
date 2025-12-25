package baguchi.twilight_crossbow.mixin;

import baguchi.twilight_crossbow.init.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RangedCrossbowAttackGoal;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import twilightforest.entity.monster.BaseIceMob;
import twilightforest.entity.monster.SnowGuardian;

@Mixin(SnowGuardian.class)
public class SnowGuardianMixin extends BaseIceMob implements CrossbowAttackMob {
    public SnowGuardianMixin(EntityType<? extends BaseIceMob> type, Level worldIn) {
        super(type, worldIn);
    }

    @Inject(method = "registerGoals", at = @At("TAIL"))
    protected void registerGoals(CallbackInfo ci) {
        this.goalSelector.getAvailableGoals().stream().map(it -> it.getGoal()).filter(it -> it instanceof MeleeAttackGoal).findFirst().ifPresent(goal -> {
            this.goalSelector.removeGoal(goal);
        });
        this.goalSelector.addGoal(1, new RangedCrossbowAttackGoal<>(this, 1.0F, 8.0F));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0F, false) {
            @Override
            public boolean canUse() {
                return !this.mob.isHolding(predicate -> {
                    return predicate.getItem() instanceof CrossbowItem;
                }) && super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                return !this.mob.isHolding(predicate -> {
                    return predicate.getItem() instanceof CrossbowItem;
                }) && super.canContinueToUse();
            }
        });
    }

    @Inject(method = "makeItemForSlot", at = @At("RETURN"), cancellable = true)
    private void makeItemForSlot(EquipmentSlot slot, int type, CallbackInfoReturnable<Item> cir) {
        if (slot == EquipmentSlot.MAINHAND && this.random.nextInt(3) == 0) {
            Item item;
            switch (type) {
                case 1:
                    item = ModItems.SEEKER_CROSSBOW.get();
                    break;
                case 2:
                    item = Items.CROSSBOW;
                    break;
                case 3:
                    item = ModItems.ICE_CROSSBOW.get();
                    break;
                default:
                    item = ModItems.TRIPLE_CROSSBOW.get();
            }
        }
    }

    @Override
    public void setChargingCrossbow(boolean b) {

    }

    @Override
    public void onCrossbowAttackPerformed() {
        this.noActionTime = 0;
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        this.performCrossbowAttack(this, 1.6F);
    }
}
