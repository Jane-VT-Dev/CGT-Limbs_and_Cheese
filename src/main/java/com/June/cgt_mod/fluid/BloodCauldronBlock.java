package com.June.cgt_mod.fluid;

import com.June.cgt_mod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

import java.util.Map;

public class BloodCauldronBlock extends LayeredCauldronBlock {
    public BloodCauldronBlock(BlockBehaviour.Properties properties) {
        super(properties, precipitation -> false, bloodCauldronInteractions());
    }

    private static Map<Item, CauldronInteraction> bloodCauldronInteractions() {
        Object2ObjectOpenHashMap<Item, CauldronInteraction> interactions = new Object2ObjectOpenHashMap<>();
        interactions.putAll(CauldronInteraction.WATER);
        interactions.defaultReturnValue(CauldronInteraction.WATER.get(Items.AIR));
        interactions.remove(Items.BUCKET);
        interactions.remove(Items.GLASS_BOTTLE);
        interactions.remove(Items.POTION);
        interactions.remove(Items.WATER_BUCKET);
        interactions.remove(Items.LAVA_BUCKET);
        interactions.remove(Items.POWDER_SNOW_BUCKET);
        return interactions;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getItemInHand(hand);
        int currentLevel = state.getValue(LEVEL);

        if (heldItem.is(Items.GLASS_BOTTLE) && currentLevel > 0) {
            if (!level.isClientSide) {
                player.setItemInHand(hand, ItemUtils.createFilledResult(
                        heldItem, player, new ItemStack(ModItems.BLOOD_BOTTLE.get())));
                lowerFillLevel(state, level, pos);
                player.awardStat(Stats.USE_CAULDRON);
                level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        if (heldItem.is(ModItems.BLOOD_BUCKET.get()) && currentLevel < 3) {
            if (!level.isClientSide) {
                player.setItemInHand(hand, ItemUtils.createFilledResult(
                        heldItem, player, new ItemStack(Items.BUCKET)));
                BlockState filledState = state.setValue(LEVEL, 3);
                level.setBlockAndUpdate(pos, filledState);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(filledState));
                player.awardStat(Stats.FILL_CAULDRON);
                level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        if (heldItem.is(Items.BUCKET) && currentLevel == 3) {
            if (!level.isClientSide) {
                player.setItemInHand(hand, ItemUtils.createFilledResult(
                        heldItem, player, new ItemStack(ModItems.BLOOD_BUCKET.get())));
                level.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos,
                        GameEvent.Context.of(Blocks.CAULDRON.defaultBlockState()));
                player.awardStat(Stats.USE_CAULDRON);
                level.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return super.use(state, level, pos, player, hand, hit);
    }
}
