package com.June.cgt_mod.block.entity;

import com.June.cgt_mod.fluid.ModFluids;
import com.June.cgt_mod.block.custom.BloodAltarBlock;
import com.June.cgt_mod.config.BloodAltarConfig;
import com.June.cgt_mod.menu.BloodAltarMenu;
import com.June.cgt_mod.recipe.BloodAltarRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.Nullable;

public class BloodAltarBlockEntity extends BlockEntity implements Container, MenuProvider {
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    private static final Direction[] PEDESTAL_ORDER = {
            Direction.NORTH, Direction.WEST, Direction.EAST, Direction.SOUTH
    };

    private final NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);
    private final FluidTank bloodTank = new FluidTank(BloodAltarConfig.TANK_CAPACITY.get(),
            fluid -> fluid.getFluid() == ModFluids.BLOOD.get()) {
        @Override
        protected void onContentsChanged() {
            BloodAltarBlockEntity.this.setChanged();
        }
    };
    private final LazyOptional<IFluidHandler> fluidHandler = LazyOptional.of(() -> bloodTank);
    private int craftProgress;
    private int craftDuration;
    private ItemStack pendingOutput = ItemStack.EMPTY;

    public BloodAltarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLOOD_ALTAR.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.cgt_mod.blood_altar");
    }

    @Override
    public BloodAltarMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new BloodAltarMenu(containerId, playerInventory, this);
    }

    @Override
    public int getContainerSize() {
        return items.size();
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
        ItemStack removed = ContainerHelper.removeItem(items, slot, amount);
        if (!removed.isEmpty()) {
            cancelCraftingForItemRemoval();
            setChanged();
        }
        return removed;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        ItemStack removed = ContainerHelper.takeItem(items, slot);
        if (!removed.isEmpty()) {
            cancelCraftingForItemRemoval();
        }
        return removed;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        if (slot == OUTPUT_SLOT) {
            return;
        }
        ItemStack stored = stack.copy();
        if (!stored.isEmpty()) {
            stored.setCount(Math.min(stored.getCount(), getMaxStackSize()));
        }
        ItemStack current = items.get(slot);
        if (!current.isEmpty()
                && (stored.isEmpty()
                || !ItemStack.isSameItemSameTags(current, stored)
                || stored.getCount() < current.getCount())) {
            cancelCraftingForItemRemoval();
        }
        items.set(slot, stored);
        setChanged();
    }

    public int getFluidAmount() {
        return bloodTank.getFluidAmount();
    }

    public FluidStack getBlood() {
        return bloodTank.getFluid().copy();
    }

    public int getCapacity() {
        return BloodAltarConfig.TANK_CAPACITY.get();
    }

    public ItemStack[] getPedestalItems() {
        ItemStack[] result = new ItemStack[4];
        if (level == null) {
            return result;
        }
        for (int i = 0; i < PEDESTAL_ORDER.length; i++) {
            BlockPos pedestalPos = worldPosition.relative(PEDESTAL_ORDER[i], 2);
            BlockEntity blockEntity = level.getBlockEntity(pedestalPos);
            result[i] = blockEntity instanceof PedestalBlockEntity pedestal
                    ? pedestal.getItem(0)
                    : ItemStack.EMPTY;
        }
        return result;
    }

    public void onPedestalChanged() {
        setChanged();
    }

    private void updateActiveState(boolean active) {
        if (level == null || level.isClientSide) {
            return;
        }
        BlockState state = getBlockState();
        if (state.hasProperty(BloodAltarBlock.ACTIVE) && state.getValue(BloodAltarBlock.ACTIVE) != active) {
            level.setBlock(worldPosition, state.setValue(BloodAltarBlock.ACTIVE, active), 3);
        }
    }

    public boolean startRecipe() {
        if (level == null || level.isClientSide || isCrafting()) {
            return false;
        }
        ItemStack[] pedestalItems = getPedestalItems();
        if (pedestalItems.length != 4) {
            return false;
        }

        BloodAltarRecipe recipe = level.getRecipeManager()
                .getAllRecipesFor(BloodAltarRecipe.TYPE)
                .stream()
                .filter(candidate -> candidate.matches(this, pedestalItems))
                .findFirst()
                .orElse(null);
        if (recipe == null) {
            return false;
        }

        ItemStack output = recipe.getResultItem().copy();
        ItemStack currentOutput = items.get(OUTPUT_SLOT);
        if (!currentOutput.isEmpty()
                && (!ItemStack.isSameItemSameTags(currentOutput, output)
                || currentOutput.getCount() + output.getCount() > Math.min(getMaxStackSize(), output.getMaxStackSize()))) {
            return false;
        }

        items.get(INPUT_SLOT).shrink(1);
        for (Direction direction : PEDESTAL_ORDER) {
            BlockEntity blockEntity = level.getBlockEntity(worldPosition.relative(direction, 2));
            if (blockEntity instanceof PedestalBlockEntity pedestal) {
                pedestal.removeItem(0, 1);
            }
        }
        bloodTank.drain(recipe.getBloodAmount(), IFluidHandler.FluidAction.EXECUTE);
        pendingOutput = output;
        craftProgress = 0;
        craftDuration = recipe.getDuration();
        updateActiveState(true);
        strikeLightning(level, worldPosition);
        setChanged();
        return true;
    }

    public void cancelCraftingForItemRemoval() {
        if (!isCrafting() || level == null || level.isClientSide) {
            return;
        }
        pendingOutput = ItemStack.EMPTY;
        craftProgress = 0;
        craftDuration = 0;
        updateActiveState(false);
        level.playSound(null, worldPosition, SoundEvents.ANVIL_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
        setChanged();
    }

    public boolean isCrafting() {
        return craftDuration > 0 && !pendingOutput.isEmpty();
    }

    public int getCraftProgress() {
        return craftProgress;
    }

    public int getCraftDuration() {
        return craftDuration;
    }

    public int getCraftProgressScaled() {
        return isCrafting() ? (int) ((long) craftProgress * 1000 / craftDuration) : 0;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, BloodAltarBlockEntity altar) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }
        altar.updateActiveState(altar.isCrafting());
        if (!altar.isCrafting()) {
            return;
        }
        if (altar.craftProgress % 20 == 0) {
            serverLevel.playSound(null, pos, SoundEvents.BEEHIVE_DRIP, SoundSource.BLOCKS,
                    1.0F, 0.9F + serverLevel.random.nextFloat() * 0.2F);
        }
        if (altar.craftProgress % 3 == 0) {
            spawnRedstoneParticles(serverLevel, pos);
        }
        altar.craftProgress++;
        if (altar.craftProgress >= altar.craftDuration) {
            ItemStack currentOutput = altar.items.get(OUTPUT_SLOT);
            if (currentOutput.isEmpty()) {
                altar.items.set(OUTPUT_SLOT, altar.pendingOutput.copy());
            } else {
                currentOutput.grow(altar.pendingOutput.getCount());
            }
            altar.pendingOutput = ItemStack.EMPTY;
            altar.craftProgress = 0;
            altar.craftDuration = 0;
            altar.updateActiveState(false);
            for (Direction direction : PEDESTAL_ORDER) {
                BlockEntity blockEntity = level.getBlockEntity(pos.relative(direction, 2));
                if (blockEntity instanceof PedestalBlockEntity pedestal) {
                    pedestal.setChanged();
                }
            }
            strikeLightning(level, pos);
            altar.setChanged();
        } else {
            altar.markCraftProgressChanged();
            if (altar.craftProgress % 10 == 0) {
                level.sendBlockUpdated(pos, state, state, 3);
            }
        }
    }

    private static void strikeLightning(Level level, BlockPos pos) {
        LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
        if (lightning != null) {
            lightning.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            lightning.setVisualOnly(true);
            level.addFreshEntity(lightning);
        }
    }

    private static void spawnRedstoneParticles(ServerLevel level, BlockPos pos) {
        for (int i = 0; i < 2; i++) {
            double x = pos.getX() + level.random.nextDouble() * 1.4 - 0.2;
            double y = pos.getY() + 0.25 + level.random.nextDouble() * 1.2;
            double z = pos.getZ() + level.random.nextDouble() * 1.4 - 0.2;
            level.sendParticles(DustParticleOptions.REDSTONE, x, y, z, 1, 0.0, 0.0, 0.0, 0.0);
        }
    }

    private void markCraftProgressChanged() {
        super.setChanged();
    }

    public ItemStack getPendingOutput() {
        return pendingOutput.copy();
    }

    @Override
    public int getMaxStackSize() {
        return 64;
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
                && player.distanceToSqr(worldPosition.getX() + 0.5, worldPosition.getY() + 0.5,
                worldPosition.getZ() + 0.5) <= 64.0;
    }

    @Override
    public void clearContent() {
        if (items.stream().anyMatch(stack -> !stack.isEmpty())) {
            cancelCraftingForItemRemoval();
        }
        items.clear();
        bloodTank.setFluid(FluidStack.EMPTY);
        setChanged();
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        items.replaceAll(stack -> ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, items);
        bloodTank.readFromNBT(tag.getCompound("BloodTank"));
        craftProgress = tag.getInt("CraftProgress");
        craftDuration = tag.getInt("CraftDuration");
        pendingOutput = tag.contains("PendingOutput")
                ? ItemStack.of(tag.getCompound("PendingOutput"))
                : ItemStack.EMPTY;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, items);
        tag.put("BloodTank", bloodTank.writeToNBT(new CompoundTag()));
        tag.putInt("CraftProgress", craftProgress);
        tag.putInt("CraftDuration", craftDuration);
        if (!pendingOutput.isEmpty()) {
            tag.put("PendingOutput", pendingOutput.save(new CompoundTag()));
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
            if (level != null) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            }
        }
    }

    @Override
    public void startOpen(Player player) {
    }

    @Override
    public void stopOpen(Player player) {
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
}
