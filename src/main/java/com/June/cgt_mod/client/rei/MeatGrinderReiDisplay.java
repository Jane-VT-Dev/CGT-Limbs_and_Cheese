package com.June.cgt_mod.client.rei;

import com.June.cgt_mod.Cgt_mod;
import com.June.cgt_mod.fluid.ModFluids;
import com.June.cgt_mod.recipe.MeatGrinderRecipe;
import dev.architectury.fluid.FluidStack;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MeatGrinderReiDisplay implements Display {
    public static final CategoryIdentifier<MeatGrinderReiDisplay> CATEGORY =
            CategoryIdentifier.of(Cgt_mod.MOD_ID, "meat_grinder");
    private final MeatGrinderRecipe recipe;

    public MeatGrinderReiDisplay(MeatGrinderRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return List.of(EntryIngredient.of(Arrays.stream(recipe.getInput().getItems()).map(EntryStacks::of).toList()));
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return List.of(EntryIngredient.of(EntryStacks.of(
                FluidStack.create(ModFluids.BLOOD.get(), recipe.getBloodAmount()))));
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
}
