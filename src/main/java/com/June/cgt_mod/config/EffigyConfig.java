package com.June.cgt_mod.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public final class EffigyConfig {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	private static final List<String> DEFAULT_HEADS = List.of(
			"minecraft:skeleton_skull",
			"minecraft:wither_skeleton_skull",
			"minecraft:player_head",
			"minecraft:zombie_head",
			"minecraft:creeper_head",
			"minecraft:dragon_head",
			"cgt_mod_cheese_limbs:pillager_head",
			"cgt_mod_cheese_limbs:villager_head");

	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> HEAD_WHITELIST =
			BUILDER.comment("When not empty, only listed item IDs can be placed in the effigy.")
					.defineList("head_whitelist", DEFAULT_HEADS, EffigyConfig::isResourceLocation);
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> HEAD_BLACKLIST =
			BUILDER.comment("Listed item IDs cannot be placed in the effigy. The blacklist takes precedence over the whitelist.")
					.defineList("head_blacklist", List.of(), EffigyConfig::isResourceLocation);
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> HEAD_ROTATIONS =
			BUILDER.comment("Per-head rotation offsets in degrees, formatted as item_id=degrees. Offsets are added to the effigy's facing rotation.")
					.defineList("head_rotations", List.of(
							"minecraft:skeleton_skull=0",
							"minecraft:wither_skeleton_skull=0",
							"minecraft:player_head=0",
							"minecraft:zombie_head=0",
							"minecraft:creeper_head=0",
							"minecraft:dragon_head=0",
							"cgt_mod_cheese_limbs:pillager_head=0",
							"cgt_mod_cheese_limbs:villager_head=0"), EffigyConfig::isRotationEntry);

	public static final ForgeConfigSpec SPEC = BUILDER.build();

	private EffigyConfig() {
	}

	public static boolean allowsHead(String itemId) {
		List<? extends String> whitelist = HEAD_WHITELIST.get();
		return !HEAD_BLACKLIST.get().contains(itemId)
				&& (whitelist.isEmpty() || whitelist.contains(itemId));
	}

	public static float getHeadRotation(String itemId) {
		for (String entry : HEAD_ROTATIONS.get()) {
			int separator = entry.lastIndexOf('=');
			if (separator > 0 && entry.substring(0, separator).equals(itemId)) {
				return Float.parseFloat(entry.substring(separator + 1));
			}
		}
		return 0.0F;
	}

	private static boolean isResourceLocation(Object value) {
		return value instanceof String id && ResourceLocation.tryParse(id) != null;
	}

	private static boolean isRotationEntry(Object value) {
		if (!(value instanceof String entry)) {
			return false;
		}
		int separator = entry.lastIndexOf('=');
		if (separator <= 0 || ResourceLocation.tryParse(entry.substring(0, separator)) == null) {
			return false;
		}
		try {
			float degrees = Float.parseFloat(entry.substring(separator + 1));
			return Float.isFinite(degrees) && degrees >= -360.0F && degrees <= 360.0F;
		} catch (NumberFormatException exception) {
			return false;
		}
	}
}
