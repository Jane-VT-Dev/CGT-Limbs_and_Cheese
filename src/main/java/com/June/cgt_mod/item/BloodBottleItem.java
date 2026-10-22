package com.June.cgt_mod.item;

import com.June.cgt_mod.fluid.BloodFluidHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

public class BloodBottleItem extends Item {
    public BloodBottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new BloodFluidHandler(stack, 250, new ItemStack(Items.GLASS_BOTTLE));
    }
}
