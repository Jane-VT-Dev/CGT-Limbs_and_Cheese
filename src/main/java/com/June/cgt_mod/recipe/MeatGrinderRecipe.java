package com.June.cgt_mod.recipe;

import com.June.cgt_mod.Cgt_mod;
import com.June.cgt_mod.config.MeatGrinderConfig;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MeatGrinderRecipe implements Recipe<Container> {
    public static final RecipeType<MeatGrinderRecipe> TYPE = new RecipeType<>() {
        @Override
        public String toString() {
            return Cgt_mod.MOD_ID + ":meat_grinder";
        }
    };
    private static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Cgt_mod.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Cgt_mod.MOD_ID);
    public static final RegistryObject<RecipeSerializer<MeatGrinderRecipe>> SERIALIZER =
            SERIALIZERS.register("meat_grinder", Serializer::new);

    static {
        TYPES.register("meat_grinder", () -> TYPE);
    }

    public static void register(net.minecraftforge.eventbus.api.IEventBus eventBus) {
        TYPES.register(eventBus);
        SERIALIZERS.register(eventBus);
    }

    private final ResourceLocation id;
    private final Ingredient input;
    private final int bloodAmount;

    public MeatGrinderRecipe(ResourceLocation id, Ingredient input, int bloodAmount) {
        this.id = id;
        this.input = input;
        this.bloodAmount = bloodAmount;
    }

    public Ingredient getInput() {
        return input;
    }

    public int getBloodAmount() {
        return bloodAmount;
    }

    @Override
    public boolean matches(Container container, Level level) {
        return container.getContainerSize() > 0 && input.test(container.getItem(0));
    }

    @Override
    public ItemStack assemble(Container container) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width > 0 && height > 0;
    }

    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return TYPE;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(input);
        return ingredients;
    }

    public static class Serializer implements RecipeSerializer<MeatGrinderRecipe> {
        @Override
        public MeatGrinderRecipe fromJson(ResourceLocation id, com.google.gson.JsonObject json) {
            Ingredient input = Ingredient.fromJson(json.get("input"));
            int bloodAmount = GsonHelper.getAsInt(json, "blood", 1_000);
            if (bloodAmount <= 0 || bloodAmount > MeatGrinderConfig.TANK_CAPACITY.get()) {
                throw new com.google.gson.JsonSyntaxException("Meat grinder recipe blood amount must be between 1 and "
                        + MeatGrinderConfig.TANK_CAPACITY.get() + " mB");
            }
            return new MeatGrinderRecipe(id, input, bloodAmount);
        }

        @Override
        public MeatGrinderRecipe fromNetwork(ResourceLocation id, net.minecraft.network.FriendlyByteBuf buffer) {
            return new MeatGrinderRecipe(id, Ingredient.fromNetwork(buffer), buffer.readVarInt());
        }

        @Override
        public void toNetwork(net.minecraft.network.FriendlyByteBuf buffer, MeatGrinderRecipe recipe) {
            recipe.input.toNetwork(buffer);
            buffer.writeVarInt(recipe.bloodAmount);
        }
    }
}
