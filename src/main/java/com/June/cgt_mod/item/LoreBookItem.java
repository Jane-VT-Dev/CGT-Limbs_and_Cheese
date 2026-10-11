package com.June.cgt_mod.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

import java.util.List;

public class LoreBookItem extends Item {
    private final List<FormattedText> pages;

    public LoreBookItem(Properties properties, List<FormattedText> pages) {
        super(properties);
        this.pages = pages;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide()) {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> openBook(pages));
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    private static void openBook(List<FormattedText> pages) {
        Minecraft.getInstance().setScreen(new BookViewScreen(new BookViewScreen.BookAccess() {
            @Override
            public int getPageCount() {
                return pages.size();
            }

            @Override
            public FormattedText getPageRaw(int index) {
                return pages.get(index);
            }
        }));
    }
}