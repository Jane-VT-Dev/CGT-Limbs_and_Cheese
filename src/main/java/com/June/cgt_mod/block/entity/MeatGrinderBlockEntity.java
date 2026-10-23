package com.June.cgt_mod.block.entity;

import com.June.cgt_mod.config.MeatGrinderConfig;
import com.June.cgt_mod.fluid.ModFluids;
import com.June.cgt_mod.recipe.MeatGrinderRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.Nullable;

public class MeatGrinderBlockEntity extends BlockEntity {
    private final FluidTank bloodTank = new FluidTank(MeatGrinderConfig.TANK_CAPACITY.get(),
            fluid -> fluid.getFluid() == ModFluids.BLOOD.get()) {
        @Override
        protected void onContentsChanged() {
            MeatGrinderBlockEntity.this.setChanged();
        }
    };
    private LazyOptional<IFluidHandler> fluidHandler = LazyOptional.of(() -> bloodTank);

    public MeatGrinderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MEAT_GRINDER.get(), pos, state);
    }

    public boolean process(ItemStack input, boolean creative) {
        if (level == null || level.isClientSide || input.isEmpty()) {
            return false;
        }

        SimpleContainer container = new SimpleContainer(input);
        MeatGrinderRecipe recipe = level.getRecipeManager()
                .getRecipeFor(MeatGrinderRecipe.TYPE, container, level)
                .orElse(null);
        if (recipe == null || bloodTank.getSpace() < recipe.getBloodAmount()) {
            return false;
        }

        FluidStack blood = new FluidStack(ModFluids.BLOOD.get(), recipe.getBloodAmount());
        if (bloodTank.fill(blood, IFluidHandler.FluidAction.SIMULATE) != recipe.getBloodAmount()) {
            return false;
        }

        if (!creative) {
            input.shrink(1);
        }
        bloodTank.fill(blood, IFluidHandler.FluidAction.EXECUTE);
        return true;
    }

    public FluidStack getBlood() {
        return bloodTank.getFluid().copy();
    }

    public int getCapacity() {
        return MeatGrinderConfig.TANK_CAPACITY.get();
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction side) {
        if (capability == ForgeCapabilities.FLUID_HANDLER) {
            return fluidHandler.cast();
        }
        return super.getCapability(capability, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        fluidHandler.invalidate();
    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
        fluidHandler = LazyOptional.of(() -> bloodTank);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        bloodTank.readFromNBT(tag.getCompound("BloodTank"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("BloodTank", bloodTank.writeToNBT(new CompoundTag()));
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection connection, ClientboundBlockEntityDataPacket packet) {
        CompoundTag tag = packet.getTag();
        if (tag != null) {
            load(tag);
        }
    }
}
