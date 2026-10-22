package com.June.cgt_mod.fluid;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;

public class BloodFluidHandler extends FluidHandlerItemStack {
    public BloodFluidHandler(ItemStack container, int capacity, ItemStack emptyContainer) {
        super(container, capacity);
        this.emptyContainer = emptyContainer;

        if (getFluid().isEmpty()) {
            setFluid(new FluidStack(ModFluids.BLOOD.get(), capacity));
        }
    }

    private final ItemStack emptyContainer;

    @Override
    public boolean canFillFluidType(FluidStack fluid) {
        return fluid.getFluid() == ModFluids.BLOOD.get();
    }

    @Override
    public boolean canDrainFluidType(FluidStack fluid) {
        return fluid.getFluid() == ModFluids.BLOOD.get();
    }

    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        if (maxDrain < getFluid().getAmount()) {
            return FluidStack.EMPTY;
        }
        return super.drain(maxDrain, action);
    }

    @Override
    protected void setContainerToEmpty() {
        super.setContainerToEmpty();
        if (emptyContainer.isEmpty()) {
            container.shrink(1);
        } else {
            container = emptyContainer.copy();
        }
    }
}
