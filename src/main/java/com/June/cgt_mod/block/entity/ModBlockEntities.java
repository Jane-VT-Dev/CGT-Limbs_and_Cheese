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

	public static void register(IEventBus eventBus) {
		BLOCK_ENTITIES.register(eventBus);
	}
}