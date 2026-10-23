package com.June.cgt_mod.client.jei;

import com.June.cgt_mod.Cgt_mod;
import com.June.cgt_mod.block.ModBlocks;
import com.June.cgt_mod.recipe.BloodAltarRecipe;
import com.June.cgt_mod.recipe.MeatGrinderRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class BloodAltarJeiPlugin implements IModPlugin {
    public static final RecipeType<BloodAltarRecipe> RECIPE_TYPE =
            new RecipeType<>(new ResourceLocation(Cgt_mod.MOD_ID, "blood_altar"), BloodAltarRecipe.class);
    public static final RecipeType<MeatGrinderRecipe> MEAT_GRINDER_RECIPE_TYPE =
            new RecipeType<>(new ResourceLocation(Cgt_mod.MOD_ID, "meat_grinder"), MeatGrinderRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Cgt_mod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new BloodAltarRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new MeatGrinderRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        var level = Minecraft.getInstance().level;
        if (level != null) {
            registration.addRecipes(RECIPE_TYPE, level.getRecipeManager().getAllRecipesFor(BloodAltarRecipe.TYPE));
            registration.addRecipes(MEAT_GRINDER_RECIPE_TYPE,
                    level.getRecipeManager().getAllRecipesFor(MeatGrinderRecipe.TYPE));
        }
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ModBlocks.BLOOD_ALTAR.get().asItem().getDefaultInstance(), RECIPE_TYPE);
        registration.addRecipeCatalyst(ModBlocks.MEAT_GRINDER.get().asItem().getDefaultInstance(),
                MEAT_GRINDER_RECIPE_TYPE);
    }
}
