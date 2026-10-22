package com.June.cgt_mod.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public final class EffigyConfig {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> HEAD_WHITELIST =
			BUILDER.comment("When not empty, only listed item IDs can be placed in the effigy.")
					.defineList("head_whitelist", List.of(), EffigyConfig::isResourceLocation);
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> HEAD_BLACKLIST =
			BUILDER.comment("Listed item IDs cannot be placed in the effigy. The blacklist takes precedence over the whitelist.")
					.defineList("head_blacklist", List.of(), EffigyConfig::isResourceLocation);

	public static final ForgeConfigSpec SPEC = BUILDER.build();

	private EffigyConfig() {
	}

	public static boolean allowsHead(String itemId) {
		List<? extends String> whitelist = HEAD_WHITELIST.get();
		return !HEAD_BLACKLIST.get().contains(itemId)
				&& (whitelist.isEmpty() || whitelist.contains(itemId));
	}

	private static boolean isResourceLocation(Object value) {
		return value instanceof String id && ResourceLocation.tryParse(id) != null;
	}
}
