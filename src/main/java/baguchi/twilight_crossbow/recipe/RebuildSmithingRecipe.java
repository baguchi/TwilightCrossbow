package baguchi.twilight_crossbow.recipe;

import baguchi.twilight_crossbow.init.ModRecipes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.item.crafting.SmithingRecipeInput;
import net.minecraft.world.level.Level;

import java.util.stream.Stream;

public class RebuildSmithingRecipe implements SmithingRecipe {
    final Ingredient template;
    final Ingredient base;
    final Ingredient addition;
    final ItemStack result;

    public RebuildSmithingRecipe(Ingredient template, Ingredient base, Ingredient addition, ItemStack result) {
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    public boolean matches(SmithingRecipeInput input, Level level) {
        return this.template.test(input.template()) && this.base.test(input.base()) && this.addition.test(input.addition());
    }

    public ItemStack assemble(SmithingRecipeInput input, HolderLookup.Provider registries) {
        ItemStack itemstack = this.result.copy();
        return itemstack;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return this.result;
    }

    @Override
    public boolean isTemplateIngredient(ItemStack stack) {
        return this.template.test(stack);
    }

    @Override
    public boolean isBaseIngredient(ItemStack stack) {
        return this.base.test(stack);
    }

    @Override
    public boolean isAdditionIngredient(ItemStack stack) {
        return this.addition.test(stack);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.REBUILD_TEMPLATE_SMITHING_SERIALIZER.get();
    }

    @Override
    public boolean isIncomplete() {
        return Stream.of(this.template, this.base, this.addition).anyMatch(Ingredient::hasNoItems);
    }

    public static class Serializer implements RecipeSerializer<RebuildSmithingRecipe> {
        private static final MapCodec<RebuildSmithingRecipe> CODEC = RecordCodecBuilder.mapCodec(
                p_340782_ -> p_340782_.group(
                                Ingredient.CODEC.fieldOf("template").forGetter(p_301310_ -> p_301310_.template),
                                Ingredient.CODEC.fieldOf("base").forGetter(p_300938_ -> p_300938_.base),
                                Ingredient.CODEC.fieldOf("addition").forGetter(p_301153_ -> p_301153_.addition),
                                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(p_300935_ -> p_300935_.result)
                        )
                        .apply(p_340782_, RebuildSmithingRecipe::new)
        );
        public static final StreamCodec<RegistryFriendlyByteBuf, RebuildSmithingRecipe> STREAM_CODEC = StreamCodec.of(
                RebuildSmithingRecipe.Serializer::toNetwork, RebuildSmithingRecipe.Serializer::fromNetwork
        );

        @Override
        public MapCodec<RebuildSmithingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, RebuildSmithingRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static RebuildSmithingRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            Ingredient ingredient1 = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            Ingredient ingredient2 = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
            return new RebuildSmithingRecipe(ingredient, ingredient1, ingredient2, itemstack);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, RebuildSmithingRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.template);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.base);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.addition);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
        }
    }
}
