package com.June.cgt_mod.block;

import com.June.cgt_mod.Cgt_mod;
import com.June.cgt_mod.ModCreativeTab;
import com.June.cgt_mod.block.custom.EffigyEmptyBlock;
import com.June.cgt_mod.block.custom.JarBlock;
import com.June.cgt_mod.block.custom.PlayerHeadPotBlock;
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

public class ModBlocks {
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,Cgt_mod.MOD_ID);

        public static final RegistryObject<Block> EFFIGY_EMPTY_BLOCK = registerBlock("effigy_empty_block",
                () -> new EffigyEmptyBlock(BlockBehaviour.Properties.of(Material.WOOD)
                        .strength(6f).requiresCorrectToolForDrops()));

        public static final RegistryObject<Block> JAR = registerBlock("jar",
                () -> new JarBlock(BlockBehaviour.Properties.of(Material.GLASS)
                        .strength(0.5f).noOcclusion()));

        public static final RegistryObject<Block> PLAYER_HEAD_POT = registerBlock("player_head_pot",
                () -> new PlayerHeadPotBlock(BlockBehaviour.Properties.of(Material.STONE)
                        .strength(1.5f).noOcclusion()));

        public static final RegistryObject<Block> PILLAGER_HEAD_POT = registerBlock("pillager_head_pot",
                () -> new PlayerHeadPotBlock(BlockBehaviour.Properties.of(Material.STONE)
                        .strength(1.5f).noOcclusion()));

        public static final RegistryObject<Block> VILLAGER_HEAD_POT = registerBlock("villager_head_pot",
                () -> new PlayerHeadPotBlock(BlockBehaviour.Properties.of(Material.STONE)
                        .strength(1.5f).noOcclusion()));

        public static final RegistryObject<Block> ZOMBIE_HEAD_POT = registerBlock("zombie_head_pot",
                () -> new PlayerHeadPotBlock(BlockBehaviour.Properties.of(Material.STONE)
                        .strength(1.5f).noOcclusion()));

        public static final RegistryObject<Block> SKELETON_HEAD_POT = registerBlock("skeleton_head_pot",
                () -> new PlayerHeadPotBlock(BlockBehaviour.Properties.of(Material.STONE)
                        .strength(1.5f).noOcclusion()));

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
