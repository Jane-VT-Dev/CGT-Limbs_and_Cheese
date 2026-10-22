package com.June.cgt_mod.block.entity;

import com.June.cgt_mod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PedestalBlockEntity extends BlockEntity implements Container {
    private final NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

    public PedestalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PEDESTAL.get(), pos, state);
    }

    @Override
    public int getContainerSize() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return items.get(0).isEmpty();
    }

    @Override
    public ItemStack getItem(int slot) {
        return items.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack removed = ContainerHelper.removeItem(items, slot, amount);
        if (!removed.isEmpty()) {
            notifyAltarItemRemoved();
            setChanged();
        }
        return removed;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        ItemStack removed = ContainerHelper.takeItem(items, slot);
        if (!removed.isEmpty()) {
            notifyAltarItemRemoved();
        }
        return removed;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        ItemStack stored = stack.copy();
        if (!stored.isEmpty()) {
            stored.setCount(1);
        }
        ItemStack current = items.get(slot);
        if (!current.isEmpty()
                && (stored.isEmpty() || !ItemStack.isSameItemSameTags(current, stored))) {
            notifyAltarItemRemoved();
        }
        items.set(slot, stored);
        setChanged();
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            BlockPos altarPos = getAltarPos();
            if (altarPos != null && level.getBlockEntity(altarPos) instanceof BloodAltarBlockEntity altar) {
                altar.onPedestalChanged();
            }
        }
    }

    private BlockPos getAltarPos() {
        if (level == null) {
            return null;
        }
        for (net.minecraft.core.Direction direction : net.minecraft.core.Direction.Plane.HORIZONTAL) {
            BlockPos candidate = worldPosition.relative(direction, 2);
            if (level.getBlockState(candidate).is(ModBlocks.BLOOD_ALTAR.get())) {
                return candidate;
            }
        }
        return null;
    }

    private void notifyAltarItemRemoved() {
        if (level == null || level.isClientSide) {
            return;
        }
        BlockPos altarPos = getAltarPos();
        if (altarPos != null && level.getBlockEntity(altarPos) instanceof BloodAltarBlockEntity altar) {
            altar.cancelCraftingForItemRemoval();
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return level != null && level.getBlockEntity(worldPosition) == this
                && player.distanceToSqr(worldPosition.getX() + 0.5, worldPosition.getY() + 0.5,
                worldPosition.getZ() + 0.5) <= 64.0;
    }

    @Override
    public void clearContent() {
        if (!isEmpty()) {
            notifyAltarItemRemoved();
        }
        items.clear();
        setChanged();
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        for (int slot = 0; slot < items.size(); slot++) {
            items.set(slot, ItemStack.EMPTY);
        }
        ContainerHelper.loadAllItems(tag, items);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, items);
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
            if (level != null) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            }
        }
    }
}
