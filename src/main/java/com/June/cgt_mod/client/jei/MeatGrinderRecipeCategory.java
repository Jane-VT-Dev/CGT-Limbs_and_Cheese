package com.June.cgt_mod.client.jei;

import com.June.cgt_mod.Cgt_mod;
import com.June.cgt_mod.block.ModBlocks;
import com.June.cgt_mod.fluid.ModFluids;
import com.June.cgt_mod.recipe.MeatGrinderRecipe;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraftforge.fluids.FluidStack;

public class MeatGrinderRecipeCategory implements IRecipeCategory<MeatGrinderRecipe> {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 36;
    private final IDrawable background;
    private final IDrawable icon;

    public MeatGrinderRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createBlankDrawable(WIDTH, HEIGHT);
        icon = guiHelper.createDrawableIngredient(mezz.jei.api.constants.VanillaTypes.ITEM_STACK,
                ModBlocks.MEAT_GRINDER.get().asItem().getDefaultInstance());
    }

    @Override
    public mezz.jei.api.recipe.RecipeType<MeatGrinderRecipe> getRecipeType() {
        return BloodAltarJeiPlugin.MEAT_GRINDER_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block." + Cgt_mod.MOD_ID + ".meat_grinder");
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
    public void setRecipe(IRecipeLayoutBuilder builder, MeatGrinderRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(mezz.jei.api.recipe.RecipeIngredientRole.INPUT, 4, 4)
                .addIngredients(recipe.getInput());
        builder.addSlot(mezz.jei.api.recipe.RecipeIngredientRole.OUTPUT, 64, 4)
                .setFluidRenderer(recipe.getBloodAmount(), false, 16, 24)
                .addIngredient(ForgeTypes.FLUID_STACK,
                        new FluidStack(ModFluids.BLOOD.get(), recipe.getBloodAmount()));
    }

    @Override
    public void draw(MeatGrinderRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack,
                     double mouseX, double mouseY) {
        net.minecraft.client.Minecraft.getInstance().font.draw(poseStack,
                Component.translatable("recipe." + Cgt_mod.MOD_ID + ".meat_grinder.blood",
                        recipe.getBloodAmount()), 26, 10, 0x404040);
    }
}
