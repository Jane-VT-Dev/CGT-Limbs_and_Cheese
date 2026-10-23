package com.June.cgt_mod.client.emi;

import com.June.cgt_mod.Cgt_mod;
import com.June.cgt_mod.block.ModBlocks;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.resources.ResourceLocation;

public class MeatGrinderEmiCategory extends EmiRecipeCategory {
    public static final ResourceLocation ID = new ResourceLocation(Cgt_mod.MOD_ID, "meat_grinder");

    public MeatGrinderEmiCategory() {
        super(ID, EmiStack.of(ModBlocks.MEAT_GRINDER.get()));
    }
}
