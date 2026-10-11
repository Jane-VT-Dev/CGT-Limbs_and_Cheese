package com.June.cgt_mod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PlayerHeadPotBlockEntity extends BlockEntity {
    private static final String PLANT_TAG = "Plant";
    private ItemStack plant = ItemStack.EMPTY;

    public PlayerHeadPotBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PLAYER_HEAD_POT.get(), pos, state);
    }

    public boolean hasPlant() {
        return !plant.isEmpty();
    }

    public ItemStack getPlant() {
        return plant;
    }

    public void setPlant(ItemStack stack) {
        plant = stack.copy();
        plant.setCount(1);
        setChanged();
        sync();
    }

    public ItemStack removePlant() {
        ItemStack removed = plant;
        plant = ItemStack.EMPTY;
        setChanged();
        sync();
        return removed;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        plant = tag.contains(PLANT_TAG) ? ItemStack.of(tag.getCompound(PLANT_TAG)) : ItemStack.EMPTY;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (!plant.isEmpty()) {
            tag.put(PLANT_TAG, plant.save(new CompoundTag()));
        }
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

    private void sync() {
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }
}
