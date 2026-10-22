package com.June.cgt_mod.item;

import com.June.cgt_mod.fluid.BloodFluidHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

public class BloodBlobItem extends BlockItem {
    public BloodBlobItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new BloodFluidHandler(stack, 250, ItemStack.EMPTY);
    }
}
