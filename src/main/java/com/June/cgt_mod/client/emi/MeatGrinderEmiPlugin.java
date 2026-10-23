package com.June.cgt_mod.client.emi;

import com.June.cgt_mod.block.ModBlocks;
import com.June.cgt_mod.recipe.MeatGrinderRecipe;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.EmiStack;

@EmiEntrypoint
public class MeatGrinderEmiPlugin implements EmiPlugin {
    public static final MeatGrinderEmiCategory CATEGORY = new MeatGrinderEmiCategory();

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(CATEGORY);
        registry.addWorkstation(CATEGORY, EmiStack.of(ModBlocks.MEAT_GRINDER.get()));
        for (MeatGrinderRecipe recipe : registry.getRecipeManager().getAllRecipesFor(MeatGrinderRecipe.TYPE)) {
            registry.addRecipe(new MeatGrinderEmiRecipe(recipe));
        }
    }
}
