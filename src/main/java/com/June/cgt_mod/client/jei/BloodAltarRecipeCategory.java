package com.June.cgt_mod.client.jei;

import com.June.cgt_mod.Cgt_mod;
import com.June.cgt_mod.block.ModBlocks;
import com.June.cgt_mod.recipe.BloodAltarRecipe;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class BloodAltarRecipeCategory implements IRecipeCategory<BloodAltarRecipe> {
    private static final int WIDTH = 160;
    private static final int HEIGHT = 82;
    private final IDrawable background;
    private final IDrawable icon;

    public BloodAltarRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createBlankDrawable(WIDTH, HEIGHT);
        icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                ModBlocks.BLOOD_ALTAR.get().asItem().getDefaultInstance());
    }

    @Override
    public RecipeType<BloodAltarRecipe> getRecipeType() {
        return BloodAltarJeiPlugin.RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block." + Cgt_mod.MOD_ID + ".blood_altar");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, BloodAltarRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(mezz.jei.api.recipe.RecipeIngredientRole.INPUT, 4, 4)
                .addIngredients(recipe.getCoreIngredient());
        for (int i = 0; i < recipe.getPedestalIngredients().size(); i++) {
            builder.addSlot(mezz.jei.api.recipe.RecipeIngredientRole.INPUT, 4 + i * 18, 28)
                    .addIngredients(recipe.getPedestalIngredients().get(i));
        }
        builder.addSlot(mezz.jei.api.recipe.RecipeIngredientRole.OUTPUT, 112, 28)
                .addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(BloodAltarRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack,
                     double mouseX, double mouseY) {
        var font = Minecraft.getInstance().font;
        font.draw(poseStack, Component.translatable("recipe." + Cgt_mod.MOD_ID + ".blood_altar.blood",
                recipe.getBloodAmount()), 0, 56, 0x404040);
        font.draw(poseStack, Component.translatable("recipe." + Cgt_mod.MOD_ID + ".blood_altar.duration",
                recipe.getDuration() / 20.0), 0, 68, 0x404040);
    }
}
