package com.June.cgt_mod.client.rei;

import com.June.cgt_mod.recipe.BloodAltarRecipe;
import com.June.cgt_mod.recipe.MeatGrinderRecipe;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.forge.REIPluginClient;

@REIPluginClient
public class BloodAltarReiPlugin implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new BloodAltarReiCategory());
        registry.add(new MeatGrinderReiCategory());
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(BloodAltarRecipe.class, BloodAltarRecipe.TYPE, BloodAltarReiDisplay::new);
        registry.registerRecipeFiller(MeatGrinderRecipe.class, MeatGrinderRecipe.TYPE, MeatGrinderReiDisplay::new);
    }
}
