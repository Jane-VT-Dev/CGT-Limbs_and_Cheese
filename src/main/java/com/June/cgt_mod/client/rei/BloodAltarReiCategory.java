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

public class BloodAltarReiCategory implements DisplayCategory<BloodAltarReiDisplay> {
    private static final int WIDTH = 160;
    private static final int HEIGHT = 82;

    @Override
    public me.shedaniel.rei.api.common.category.CategoryIdentifier<? extends BloodAltarReiDisplay>
    getCategoryIdentifier() {
        return BloodAltarReiDisplay.CATEGORY;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block." + Cgt_mod.MOD_ID + ".blood_altar");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.BLOOD_ALTAR.get());
    }

    @Override
    public int getDisplayWidth(BloodAltarReiDisplay display) {
        return WIDTH;
    }

    @Override
    public int getDisplayHeight() {
        return HEIGHT;
    }

    @Override
    public List<Widget> setupDisplay(BloodAltarReiDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createRecipeBase(bounds));
        addSlot(widgets, bounds, 4, 4, display.getInputEntries().get(0));
        for (int i = 0; i < 4; i++) {
            addSlot(widgets, bounds, 4 + i * 18, 30, display.getInputEntries().get(i + 1));
        }
        widgets.add(Widgets.createArrow(new Point(bounds.x + 91, bounds.y + 30)));
        addSlot(widgets, bounds, 134, 30, display.getOutputEntries().get(0));
        widgets.add(Widgets.createLabel(new Point(bounds.x + 4, bounds.y + 56),
                Component.translatable("recipe." + Cgt_mod.MOD_ID + ".blood_altar.blood", display.getBloodAmount())));
        widgets.add(Widgets.createLabel(new Point(bounds.x + 4, bounds.y + 68),
                Component.translatable("recipe." + Cgt_mod.MOD_ID + ".blood_altar.duration",
                        display.getDuration() / 20.0)));
        return widgets;
    }

    private static void addSlot(List<Widget> widgets, Rectangle bounds, int x, int y,
                                me.shedaniel.rei.api.common.entry.EntryIngredient ingredient) {
        widgets.add(Widgets.createSlot(new Point(bounds.x + x, bounds.y + y)).entries(ingredient));
    }
}
