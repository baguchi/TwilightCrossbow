package baguchi.twilight_crossbow.init;

import baguchi.twilight_crossbow.TwilightCrossBow;
import baguchi.twilight_crossbow.recipe.RebuildSmithingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, TwilightCrossBow.MODID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, TwilightCrossBow.MODID);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<RebuildSmithingRecipe>> REBUILD_TEMPLATE_SMITHING_SERIALIZER = RECIPE_SERIALIZERS.register("rebuild_template_smithing", RebuildSmithingRecipe.Serializer::new);

}
