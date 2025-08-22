package com.June.cgt_mod.block;

import com.June.cgt_mod.Cgt_mod;
import com.June.cgt_mod.ModCreativeTab;
import com.June.cgt_mod.block.custom.*;
import com.June.cgt_mod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static net.minecraftforge.registries.ForgeRegistries.BLOCKS;

public class ModBlocks {
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,Cgt_mod.MOD_ID);

        public static final RegistryObject<Block> EFFIGY_EMPTY_BLOCK = registerBlock("effigy_empty_block",
                () -> new EffigyEmptyBlock(BlockBehaviour.Properties.of(Material.WOOD)
                        .strength(6f).requiresCorrectToolForDrops()));

        public static final RegistryObject<Block> EFFIGY_FULL_ZOMBIE = registerBlock("effigy_full_zombie",
                () -> new EffigyFZBlock(BlockBehaviour.Properties.of(Material.WOOD)
                        .strength(6f).requiresCorrectToolForDrops()));

        public static final RegistryObject<Block> EFFIGY_FULL_SKELETON = registerBlock("effigy_full_skeleton",
                () -> new EffigyFSBlock(BlockBehaviour.Properties.of(Material.WOOD)
                        .strength(6f).requiresCorrectToolForDrops()));

        public static final RegistryObject<Block> EFFIGY_FULL_PILLAGER = registerBlock("effigy_full_pillager",
                () -> new EffigyFPBlock(BlockBehaviour.Properties.of(Material.WOOD)
                        .strength(6f).requiresCorrectToolForDrops()));

        public static final RegistryObject<Block> EFFIGY_FULL_VILLAGER = registerBlock("effigy_full_villager",
                () -> new EffigyFVBlock(BlockBehaviour.Properties.of(Material.WOOD)
                        .strength(6f).requiresCorrectToolForDrops()));

        public static final RegistryObject<Block> EFFIGY_FULL_PLAYER = registerBlock("effigy_full_player",
                () -> new EffigyFPlBlock(BlockBehaviour.Properties.of(Material.WOOD)
                        .strength(6f).requiresCorrectToolForDrops()));

        public static final RegistryObject<Block> ZOMBIE_HEAD_JAR = registerBlock("zombie_head_jar",
                () -> new Block(BlockBehaviour.Properties.of(Material.GLASS)));

        private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
                RegistryObject<T> toReturn = BLOCKS.register(name, block);
                registerBlockItem(name, toReturn);
                return toReturn;
        }

        private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
                return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModCreativeTab.instance)));
        }

        public static void register(IEventBus eventBus) {
                BLOCKS.register(eventBus);
        }
}
