package com.June.cgt_mod.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.network.chat.FormattedText;

import java.util.List;

public final class ClientBookOpener {
    private ClientBookOpener() {
    }

    public static void openBook(List<FormattedText> pages) {
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
