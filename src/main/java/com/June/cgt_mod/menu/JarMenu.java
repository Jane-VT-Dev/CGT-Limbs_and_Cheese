package com.June.cgt_mod.menu;

import com.June.cgt_mod.block.ModBlocks;
import com.June.cgt_mod.block.entity.JarBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class JarMenu extends AbstractContainerMenu {
    private final Container jar;
    private final ContainerLevelAccess access;

    public JarMenu(int containerId, Inventory playerInventory, FriendlyByteBuf data) {
        this(containerId, playerInventory, getJar(playerInventory, data));
    }

    private static JarBlockEntity getJar(Inventory playerInventory, FriendlyByteBuf data) {
        if (playerInventory.player.level.getBlockEntity(data.readBlockPos()) instanceof JarBlockEntity jar) {
            return jar;
        }
        throw new IllegalStateException("Jar block entity is missing");
    }

    public JarMenu(int containerId, Inventory playerInventory, JarBlockEntity jar) {
        super(ModMenus.JAR_MENU.get(), containerId);
        this.jar = jar;
        this.access = ContainerLevelAccess.create(jar.getLevel(), jar.getBlockPos());
        jar.startOpen(playerInventory.player);

        addSlot(new Slot(jar, 0, 80, 18));
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(playerInventory, column + row * 9 + 9, 8 + column * 18, 84 + row * 18));
            }
        }
        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(playerInventory, column, 8 + column * 18, 142));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(access, player, ModBlocks.JAR.get());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            result = stack.copy();
            if (index == 0) {
                if (!moveItemStackTo(stack, 1, slots.size(), true)) {
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
        jar.stopOpen(player);
    }

}
