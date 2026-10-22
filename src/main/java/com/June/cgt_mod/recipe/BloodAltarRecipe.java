package com.June.cgt_mod.recipe;

import com.June.cgt_mod.Cgt_mod;
import com.June.cgt_mod.block.entity.BloodAltarBlockEntity;
import com.June.cgt_mod.config.BloodAltarConfig;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BloodAltarRecipe implements Recipe<Container> {
    public static final RecipeType<BloodAltarRecipe> TYPE = new RecipeType<>() {
        @Override
        public String toString() {
            return Cgt_mod.MOD_ID + ":blood_altar";
        }
    };
    private static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Cgt_mod.MOD_ID);
    static {
        TYPES.register("blood_altar", () -> TYPE);
    }

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Cgt_mod.MOD_ID);
    public static final RegistryObject<RecipeSerializer<BloodAltarRecipe>> SERIALIZER =
            SERIALIZERS.register("blood_altar", BloodAltarRecipe.Serializer::new);

    public static void register(net.minecraftforge.eventbus.api.IEventBus eventBus) {
        TYPES.register(eventBus);
        SERIALIZERS.register(eventBus);
    }

    private final ResourceLocation id;
    private final Ingredient core;
    private final NonNullList<Ingredient> pedestals;
    private final int bloodAmount;
    private final int duration;
    private final ItemStack result;

    public BloodAltarRecipe(ResourceLocation id, Ingredient core, NonNullList<Ingredient> pedestals,
                            int bloodAmount, int duration, ItemStack result) {
        this.id = id;
        this.core = core;
        this.pedestals = pedestals;
        this.bloodAmount = bloodAmount;
        this.duration = duration;
        this.result = result;
    }

    public boolean matches(BloodAltarBlockEntity altar, ItemStack[] pedestalItems) {
        if (pedestalItems.length != 4 || altar.getItem(0).isEmpty()
                || altar.getFluidAmount() < bloodAmount || !core.test(altar.getItem(0))) {
            return false;
        }
        return matchesPedestals(pedestalItems, new boolean[pedestalItems.length], 0);
    }

    private boolean matchesPedestals(ItemStack[] pedestalItems, boolean[] used, int ingredientIndex) {
        if (ingredientIndex == pedestals.size()) {
            return true;
        }
        Ingredient ingredient = pedestals.get(ingredientIndex);
        for (int itemIndex = 0; itemIndex < pedestalItems.length; itemIndex++) {
            if (!used[itemIndex] && !pedestalItems[itemIndex].isEmpty()
                    && ingredient.test(pedestalItems[itemIndex])) {
                used[itemIndex] = true;
                if (matchesPedestals(pedestalItems, used, ingredientIndex + 1)) {
                    return true;
                }
                used[itemIndex] = false;
            }
        }
        return false;
    }

    public int getBloodAmount() {
        return bloodAmount;
    }

    public int getDuration() {
        return duration;
    }

    public Ingredient getCoreIngredient() {
        return core;
    }

    public NonNullList<Ingredient> getPedestalIngredients() {
        return pedestals;
    }

    @Override
    public boolean matches(Container container, Level level) {
        return container.getContainerSize() > 0 && core.test(container.getItem(0));
    }

    @Override
    public ItemStack assemble(Container container) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return result;
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
        NonNullList<Ingredient> ingredients = NonNullList.withSize(5, Ingredient.EMPTY);
        ingredients.set(0, core);
        for (int i = 0; i < 4; i++) {
            ingredients.set(i + 1, pedestals.get(i));
        }
        return ingredients;
    }

    public static class Serializer implements RecipeSerializer<BloodAltarRecipe> {
        @Override
        public BloodAltarRecipe fromJson(ResourceLocation id, com.google.gson.JsonObject json) {
            Ingredient core = Ingredient.fromJson(json.get("core"));
            com.google.gson.JsonArray pedestalJson = json.getAsJsonArray("pedestals");
            if (pedestalJson.size() != 4) {
                throw new com.google.gson.JsonSyntaxException("Blood altar recipes require exactly four pedestal ingredients");
            }
            NonNullList<Ingredient> pedestals = NonNullList.withSize(4, Ingredient.EMPTY);
            for (int i = 0; i < 4; i++) {
                pedestals.set(i, Ingredient.fromJson(pedestalJson.get(i)));
            }
            int bloodAmount = net.minecraft.util.GsonHelper.getAsInt(json, "blood", 0);
            if (bloodAmount <= 0 || bloodAmount > BloodAltarConfig.TANK_CAPACITY.get()) {
                throw new com.google.gson.JsonSyntaxException("Blood altar recipe blood amount must be between 1 and "
                        + BloodAltarConfig.TANK_CAPACITY.get() + " mB");
            }
            int duration = net.minecraft.util.GsonHelper.getAsInt(json, "duration", 0);
            if (duration <= 0) {
                throw new com.google.gson.JsonSyntaxException("Blood altar recipe duration must be greater than 0 ticks");
            }
            ItemStack result = net.minecraft.world.item.crafting.ShapedRecipe.itemStackFromJson(
                    net.minecraft.util.GsonHelper.getAsJsonObject(json, "result"));
            if (result.isEmpty()) {
                throw new com.google.gson.JsonSyntaxException("Blood altar recipe result cannot be empty");
            }
            return new BloodAltarRecipe(id, core, pedestals, bloodAmount, duration, result);
        }

        @Override
        public BloodAltarRecipe fromNetwork(ResourceLocation id, net.minecraft.network.FriendlyByteBuf buffer) {
            Ingredient core = Ingredient.fromNetwork(buffer);
            NonNullList<Ingredient> pedestals = NonNullList.withSize(4, Ingredient.EMPTY);
            for (int i = 0; i < 4; i++) {
                pedestals.set(i, Ingredient.fromNetwork(buffer));
            }
            int bloodAmount = buffer.readVarInt();
            int duration = buffer.readVarInt();
            ItemStack result = buffer.readItem();
            return new BloodAltarRecipe(id, core, pedestals, bloodAmount, duration, result);
        }

        @Override
        public void toNetwork(net.minecraft.network.FriendlyByteBuf buffer, BloodAltarRecipe recipe) {
            recipe.core.toNetwork(buffer);
            for (Ingredient ingredient : recipe.pedestals) {
                ingredient.toNetwork(buffer);
            }
            buffer.writeVarInt(recipe.bloodAmount);
            buffer.writeVarInt(recipe.duration);
            buffer.writeItem(recipe.result);
        }
    }
}
