package com.June.cgt_mod.block.entity;

import com.June.cgt_mod.Cgt_mod;
import com.June.cgt_mod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
			DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Cgt_mod.MOD_ID);

	public static final RegistryObject<BlockEntityType<EffigyBlockEntity>> EFFIGY_EMPTY = BLOCK_ENTITIES.register(
			"effigy_empty", () -> BlockEntityType.Builder.of(EffigyBlockEntity::new,
					ModBlocks.EFFIGY_EMPTY_BLOCK.get()).build(null));

	public static final RegistryObject<BlockEntityType<PlayerHeadPotBlockEntity>> PLAYER_HEAD_POT = BLOCK_ENTITIES.register(
			"player_head_pot", () -> BlockEntityType.Builder.of(PlayerHeadPotBlockEntity::new,
					ModBlocks.PLAYER_HEAD_POT.get(),
					ModBlocks.PILLAGER_HEAD_POT.get(),
					ModBlocks.VILLAGER_HEAD_POT.get(),
					ModBlocks.ZOMBIE_HEAD_POT.get(),
					ModBlocks.SKELETON_HEAD_POT.get()).build(null));

	public static final RegistryObject<BlockEntityType<JarBlockEntity>> JAR = BLOCK_ENTITIES.register(
			"jar", () -> BlockEntityType.Builder.of(JarBlockEntity::new, ModBlocks.JAR.get()).build(null));

	public static void register(IEventBus eventBus) {
		BLOCK_ENTITIES.register(eventBus);
	}
}