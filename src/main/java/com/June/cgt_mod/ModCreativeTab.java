package com.June.cgt_mod;

import com.June.cgt_mod.item.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab extends CreativeModeTab {
    public ModCreativeTab(int index,String label) {
        super(index,label);
    }
    public static final ModCreativeTab instance = new ModCreativeTab(CreativeModeTab.TABS.length, "cgt_tab");

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ModItems.PILLAGER_HEAD_ON_STICK.get());
    }

    @Override
    public ItemStack getIconItem() {
        int iconIndex = (int) (System.currentTimeMillis() / 1000L % 5);
        return switch (iconIndex) {
            case 0 -> new ItemStack(ModItems.PILLAGER_HEAD_ON_STICK.get());
            case 1 -> new ItemStack(ModItems.PLAYER_HEAD_ON_STICK.get());
            case 2 -> new ItemStack(ModItems.SKELETON_HEAD_ON_STICK.get());
            case 3 -> new ItemStack(ModItems.VILLAGER_HEAD_ON_STICK.get());
            default -> new ItemStack(ModItems.ZOMBIE_HEAD_ON_STICK.get());
        };
    }
}
