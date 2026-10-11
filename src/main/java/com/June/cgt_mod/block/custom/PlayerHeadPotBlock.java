package com.June.cgt_mod.block.custom;

import com.June.cgt_mod.block.entity.PlayerHeadPotBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class PlayerHeadPotBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final VoxelShape SHAPE = Block.box(4, 0, 4, 12, 8, 12);

    public PlayerHeadPotBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PlayerHeadPotBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
                                 BlockHitResult hit) {
        if (!(level.getBlockEntity(pos) instanceof PlayerHeadPotBlockEntity pot)) {
            return InteractionResult.PASS;
        }

        ItemStack heldStack = player.getItemInHand(hand);
        if (pot.hasPlant()) {
            if (!heldStack.isEmpty()) {
                return InteractionResult.CONSUME;
            }

            ItemStack plant = pot.removePlant();
            if (!level.isClientSide) {
                if (!player.addItem(plant)) {
                    player.drop(plant, false);
                }
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        if (heldStack.getItem() instanceof BlockItem blockItem) {
            FlowerPotBlock flowerPot = (FlowerPotBlock) Blocks.FLOWER_POT;
            ResourceLocation plantId = ForgeRegistries.BLOCKS.getKey(blockItem.getBlock());
            if (plantId != null) {
                var pottedPlant = flowerPot.getFullPotsView().get(plantId);
                if (pottedPlant != null) {
                    if (!level.isClientSide) {
                        pot.setPlant(heldStack);
                        if (!player.getAbilities().instabuild) {
                            heldStack.shrink(1);
                        }
                    }
                    return InteractionResult.sidedSuccess(level.isClientSide);
                }
            }
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> drops = new ArrayList<>(super.getDrops(state, builder));
        if (drops.isEmpty()) {
            drops.add(new ItemStack(this));
        }

        BlockEntity blockEntity = builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockEntity instanceof PlayerHeadPotBlockEntity pot && pot.hasPlant()) {
            drops.add(pot.getPlant().copy());
        }
        return drops;
    }
}
