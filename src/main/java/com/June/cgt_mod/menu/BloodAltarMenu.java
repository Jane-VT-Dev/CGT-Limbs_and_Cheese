package com.June.cgt_mod.menu;

import com.June.cgt_mod.block.ModBlocks;
import com.June.cgt_mod.block.entity.BloodAltarBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class BloodAltarMenu extends AbstractContainerMenu {
    private final Container altar;
    private final ContainerLevelAccess access;
    private final ContainerData data;

    public BloodAltarMenu(int containerId, Inventory playerInventory, FriendlyByteBuf buffer) {
        this(containerId, playerInventory, getAltar(playerInventory, buffer));
    }

    private static BloodAltarBlockEntity getAltar(Inventory playerInventory, FriendlyByteBuf buffer) {
        if (playerInventory.player.level.getBlockEntity(buffer.readBlockPos()) instanceof BloodAltarBlockEntity altar) {
            return altar;
        }
        throw new IllegalStateException("Blood altar block entity is missing");
    }

    public BloodAltarMenu(int containerId, Inventory playerInventory, BloodAltarBlockEntity altar) {
        super(ModMenus.BLOOD_ALTAR_MENU.get(), containerId);
        this.altar = altar;
        this.access = ContainerLevelAccess.create(altar.getLevel(), altar.getBlockPos());
        this.data = new ContainerData() {
            private final int[] syncedData = new int[2];

            @Override
            public int get(int index) {
                if (altar.getLevel() == null || altar.getLevel().isClientSide) {
                    return syncedData[index];
                }
                return switch (index) {
                    case 0 -> altar.getFluidAmount();
                    case 1 -> altar.getCraftProgressScaled();
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                syncedData[index] = value;
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
        altar.startOpen(playerInventory.player);

        addSlot(new Slot(altar, 0, 80, 18));
        addSlot(new Slot(altar, 1, 80, 54) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(playerInventory, column + row * 9 + 9, 8 + column * 18, 120 + row * 18));
            }
        }
        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(playerInventory, column, 8 + column * 18, 178));
        }
        addDataSlots(data);
    }

    public int getFluidAmount() {
        return data.get(0);
    }

    public int getCraftProgress() {
        return data.get(1);
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(access, player, ModBlocks.BLOOD_ALTAR.get());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        if (index < 0 || index >= slots.size()) {
            return ItemStack.EMPTY;
        }
        ItemStack result = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            result = stack.copy();
            if (index < 2) {
                if (!moveItemStackTo(stack, 2, slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!moveItemStackTo(stack, 0, 1, false)) {
                return ItemStack.EMPTY;
            }
            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
            slot.onTake(player, stack);
        }
        return result;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        altar.stopOpen(player);
    }
}
