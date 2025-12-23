package baguchi.twilight_crossbow.compat.jei;

import baguchi.twilight_crossbow.TwilightCrossBow;
import baguchi.twilight_crossbow.recipe.RebuildSmithingRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    public static final ResourceLocation PLUGIN_ID = ResourceLocation.fromNamespaceAndPath(TwilightCrossBow.MODID, "jei_plugin");

    private static final Minecraft MC = Minecraft.getInstance();

    private static <C extends RecipeInput, T extends Recipe<C>> List<T> findRecipesByType(RecipeType<T> type) {
        return MC.level.getRecipeManager().getAllRecipesFor(type).stream().map(recipeholder -> {
            return recipeholder.value();
        }).toList();
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {

    }

    @Override
    public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
        registration.getSmithingCategory().addExtension(RebuildSmithingRecipe.class, new ReBuildSmithingExtension());
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {

    }

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_ID;
    }
}