package baguchi.twilight_crossbow.init;

import baguchi.twilight_crossbow.TwilightCrossBow;
import baguchi.twilight_crossbow.entity.IceFireworkRocketEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, TwilightCrossBow.MODID);

    public static final Supplier<EntityType<IceFireworkRocketEntity>> ICE_FIREWORK = ENTITIES.register("ice_firework", () -> EntityType.Builder.<IceFireworkRocketEntity>of(IceFireworkRocketEntity::new, MobCategory.MISC)
            .sized(0.35F, 0.35F).updateInterval(20).build("twilight_crossbow:ice_firework"));

}
