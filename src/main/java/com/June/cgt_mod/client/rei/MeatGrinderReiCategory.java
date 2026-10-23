package com.June.cgt_mod.client.rei;

import com.June.cgt_mod.Cgt_mod;
import com.June.cgt_mod.block.ModBlocks;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class MeatGrinderReiCategory implements DisplayCategory<MeatGrinderReiDisplay> {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 36;

    @Override
    public me.shedaniel.rei.api.common.category.CategoryIdentifier<? extends MeatGrinderReiDisplay>
    getCategoryIdentifier() {
        return MeatGrinderReiDisplay.CATEGORY;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block." + Cgt_mod.MOD_ID + ".meat_grinder");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.MEAT_GRINDER.get());
    }

    @Override
    public int getDisplayWidth(MeatGrinderReiDisplay display) {
        return WIDTH;
    }

    @Override
    public int getDisplayHeight() {
        return HEIGHT;
    }

    @Override
    public List<Widget> setupDisplay(MeatGrinderReiDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createSlot(new Point(bounds.x + 4, bounds.y + 4))
                .entries(display.getInputEntries().get(0)));
        widgets.add(Widgets.createArrow(new Point(bounds.x + 37, bounds.y + 4)));
        widgets.add(Widgets.createSlot(new Point(bounds.x + 64, bounds.y + 4))
                .entries(display.getOutputEntries().get(0)));
        widgets.add(Widgets.createLabel(new Point(bounds.x + 4, bounds.y + 21),
                Component.translatable("recipe." + Cgt_mod.MOD_ID + ".meat_grinder.blood",
                        display.getBloodAmount())));
        return widgets;
    }
}
