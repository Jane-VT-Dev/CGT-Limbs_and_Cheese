package com.June.cgt_mod.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public final class JarConfig {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> ITEM_DENY_LIST =
			BUILDER.comment("Item IDs that cannot be placed in jars.")
					.defineList("item_deny_list", List.of(), JarConfig::isResourceLocation);

	public static final ForgeConfigSpec SPEC = BUILDER.build();

	private JarConfig() {
	}

	public static boolean allowsItem(String itemId) {
		return !ITEM_DENY_LIST.get().contains(itemId);
	}

	private static boolean isResourceLocation(Object value) {
		return value instanceof String id && ResourceLocation.tryParse(id) != null;
	}
}
