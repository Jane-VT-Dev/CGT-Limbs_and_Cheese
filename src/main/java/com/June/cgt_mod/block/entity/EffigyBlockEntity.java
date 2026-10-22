package com.June.cgt_mod.block.entity;

import com.June.cgt_mod.config.EffigyConfig;
import com.June.cgt_mod.menu.EffigyMenu;
import com.June.cgt_mod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ContainerHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class EffigyBlockEntity extends BlockEntity implements Container, MenuProvider {
	private static final int SLOT_COUNT = 3;
	private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);

	public EffigyBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.EFFIGY_EMPTY.get(), pos, state);
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("container.cgt_mod.effigy");
	}

	@Override
	public EffigyMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new EffigyMenu(containerId, playerInventory, this);
	}

	@Override
	public int getContainerSize() {
		return SLOT_COUNT;
	}

	@Override
	public boolean isEmpty() {
		return items.stream().allMatch(ItemStack::isEmpty);
	}

	@Override
	public ItemStack getItem(int slot) {
		return items.get(slot);
	}

	@Override
	public ItemStack removeItem(int slot, int amount) {
		ItemStack result = ContainerHelper.removeItem(items, slot, amount);
		if (!result.isEmpty()) {
			setChanged();
		}
		return result;
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return ContainerHelper.takeItem(items, slot);
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		if (!stack.isEmpty() && !isAllowedHeadItem(stack)) {
			return;
		}
		ItemStack stored = stack.copy();
		stored.setCount(1);
		items.set(slot, stored);
		setChanged();
	}

	public static boolean isHeadItem(ItemStack stack) {
		return stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof SkullBlock
				|| stack.is(ModItems.PILLAGER_HEAD.get())
				|| stack.is(ModItems.VILLAGER_HEAD.get());
	}

	public static boolean isAllowedHeadItem(ItemStack stack) {
		return isHeadItem(stack)
				&& EffigyConfig.allowsHead(ForgeRegistries.ITEMS.getKey(stack.getItem()).toString());
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
		}
	}

	@Override
	public boolean stillValid(Player player) {
		return level != null && level.getBlockEntity(worldPosition) == this
				&& player.distanceToSqr(worldPosition.getX() + 0.5, worldPosition.getY() + 0.5, worldPosition.getZ() + 0.5) <= 64.0;
	}

	@Override
	public void clearContent() {
		items.clear();
		setChanged();
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
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
		}
	}

	@Override
	public void startOpen(Player player) {
	}

	@Override
	public void stopOpen(Player player) {
	}
}