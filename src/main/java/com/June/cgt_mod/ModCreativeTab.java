package com.June.cgt_mod;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModCreativeTab extends CreativeModeTab {
    public ModCreativeTab(int index,String label) {
        super(index,label);
    }
    public static final ModCreativeTab instance = new ModCreativeTab(CreativeModeTab.TABS.length, "cgt_tab");

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Items.BARRIER);
    }
}
