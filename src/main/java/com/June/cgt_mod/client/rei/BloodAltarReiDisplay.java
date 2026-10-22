package com.June.cgt_mod.client.rei;

import com.June.cgt_mod.Cgt_mod;
import com.June.cgt_mod.recipe.BloodAltarRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BloodAltarReiDisplay implements Display {
    public static final CategoryIdentifier<BloodAltarReiDisplay> CATEGORY =
            CategoryIdentifier.of(Cgt_mod.MOD_ID, "blood_altar");

    private final BloodAltarRecipe recipe;
    private final List<EntryIngredient> inputs;

    public BloodAltarReiDisplay(BloodAltarRecipe recipe) {
        this.recipe = recipe;
        inputs = new ArrayList<>();
        inputs.add(toEntryIngredient(recipe.getCoreIngredient()));
        recipe.getPedestalIngredients().forEach(ingredient -> inputs.add(toEntryIngredient(ingredient)));
    }

    private static EntryIngredient toEntryIngredient(net.minecraft.world.item.crafting.Ingredient ingredient) {
        return EntryIngredient.of(Arrays.stream(ingredient.getItems()).map(EntryStacks::of).toList());
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return inputs;
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return List.of(EntryIngredient.of(EntryStacks.of(recipe.getResultItem())));
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CATEGORY;
    }

    @Override
    public Optional<ResourceLocation> getDisplayLocation() {
        return Optional.of(recipe.getId());
    }

    public int getBloodAmount() {
        return recipe.getBloodAmount();
    }

    public int getDuration() {
        return recipe.getDuration();
    }
}
