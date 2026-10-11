package com.June.cgt_mod.menu;

import com.June.cgt_mod.block.ModBlocks;
import com.June.cgt_mod.block.entity.EffigyBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class EffigyMenu extends AbstractContainerMenu {
	private final Container effigy;
	private final ContainerLevelAccess access;

	public EffigyMenu(int containerId, Inventory playerInventory, FriendlyByteBuf data) {
		this(containerId, playerInventory, getEffigy(playerInventory, data));
	}

	private static EffigyBlockEntity getEffigy(Inventory playerInventory, FriendlyByteBuf data) {
		if (playerInventory.player.level.getBlockEntity(data.readBlockPos()) instanceof EffigyBlockEntity effigy) {
			return effigy;
		}
		throw new IllegalStateException("Effigy block entity is missing");
	}

	public EffigyMenu(int containerId, Inventory playerInventory, EffigyBlockEntity effigy) {
		super(ModMenus.EFFIGY_MENU.get(), containerId);
		this.effigy = effigy;
		this.access = ContainerLevelAccess.create(effigy.getLevel(), effigy.getBlockPos());
		effigy.startOpen(playerInventory.player);

		addSlot(new HeadSlot(effigy, 0, 25, 47));
		addSlot(new HeadSlot(effigy, 1, 80, 18));
		addSlot(new HeadSlot(effigy, 2, 135, 47));

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
		return stillValid(access, player, ModBlocks.EFFIGY_EMPTY_BLOCK.get());
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack result = ItemStack.EMPTY;
		Slot slot = slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack stack = slot.getItem();
			result = stack.copy();
			if (index < 3) {
				if (!moveItemStackTo(stack, 3, slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (!EffigyBlockEntity.isHeadItem(stack) || !moveItemStackTo(stack, 0, 3, false)) {
					return ItemStack.EMPTY;
				}
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
		effigy.stopOpen(player);
	}

	private static class HeadSlot extends Slot {
		private HeadSlot(Container container, int index, int x, int y) {
			super(container, index, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return EffigyBlockEntity.isHeadItem(stack);
		}

		@Override
		public int getMaxStackSize() {
			return 1;
		}
	}
}