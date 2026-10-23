package com.June.cgt_mod.client.emi;

import com.June.cgt_mod.fluid.ModFluids;
import com.June.cgt_mod.recipe.MeatGrinderRecipe;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class MeatGrinderEmiRecipe implements EmiRecipe {
    private final MeatGrinderRecipe recipe;
    private final List<EmiIngredient> inputs;
    private final List<EmiStack> outputs;

    public MeatGrinderEmiRecipe(MeatGrinderRecipe recipe) {
        this.recipe = recipe;
        inputs = List.of(EmiIngredient.of(recipe.getInput()));
        outputs = List.of(EmiStack.of(ModFluids.BLOOD.get()).setAmount(recipe.getBloodAmount()));
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return MeatGrinderEmiPlugin.CATEGORY;
    }

    @Override
    public ResourceLocation getId() {
        return recipe.getId();
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return inputs;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return outputs;
    }

    @Override
    public int getDisplayWidth() {
        return 90;
    }

    @Override
    public int getDisplayHeight() {
        return 26;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addSlot(inputs.get(0), 0, 4);
        widgets.addFillingArrow(24, 5, 0);
        widgets.addSlot(outputs.get(0), 58, 4).recipeContext(this);
    }
}
