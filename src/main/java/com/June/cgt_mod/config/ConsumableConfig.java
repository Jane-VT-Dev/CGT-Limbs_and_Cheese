package com.June.cgt_mod.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class ConsumableConfig {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	private static final Map<String, EffectSettings> EFFECT_SETTINGS = new HashMap<>();

	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> CONSUMABLE_ITEMS;
	public static final ForgeConfigSpec SPEC;

	static {
		BUILDER.push("consumables");
		CONSUMABLE_ITEMS = BUILDER
				.comment("Reference list of the mod's edible items. Editing this list does not change which items are registered.")
				.defineList("items", List.of(
						"cgt_mod_cheese_limbs:human_flesh",
						"cgt_mod_cheese_limbs:guts",
						"cgt_mod_cheese_limbs:cooked_zombie_arm",
						"cgt_mod_cheese_limbs:cooked_villager_arm",
						"cgt_mod_cheese_limbs:cooked_pillager_arm",
						"cgt_mod_cheese_limbs:cooked_player_arm",
						"cgt_mod_cheese_limbs:cooked_zombie_leg",
						"cgt_mod_cheese_limbs:cooked_villager_leg",
						"cgt_mod_cheese_limbs:cooked_pillager_leg",
						"cgt_mod_cheese_limbs:cooked_player_leg",
						"cgt_mod_cheese_limbs:cooked_zombie_torso",
						"cgt_mod_cheese_limbs:cooked_villager_torso",
						"cgt_mod_cheese_limbs:cooked_pillager_torso",
						"cgt_mod_cheese_limbs:cooked_player_torso",
						"cgt_mod_cheese_limbs:soup_de_cannibal",
						"cgt_mod_cheese_limbs:zombie_head_on_stick",
						"cgt_mod_cheese_limbs:skeleton_head_on_stick",
						"cgt_mod_cheese_limbs:pillager_head_on_stick",
						"cgt_mod_cheese_limbs:villager_head_on_stick",
						"cgt_mod_cheese_limbs:player_head_on_stick",
						"cgt_mod_cheese_limbs:emmental",
						"cgt_mod_cheese_limbs:camembert",
						"cgt_mod_cheese_limbs:fromage_de_chevre",
						"cgt_mod_cheese_limbs:epoisses",
						"cgt_mod_cheese_limbs:tomme_du_jura",
						"cgt_mod_cheese_limbs:comte",
						"cgt_mod_cheese_limbs:morbier",
						"cgt_mod_cheese_limbs:raclette",
						"cgt_mod_cheese_limbs:bria_savarin",
						"cgt_mod_cheese_limbs:casu_marzu",
						"cgt_mod_cheese_limbs:cheddar",
						"cgt_mod_cheese_limbs:raclette_plate",
						"cgt_mod_cheese_limbs:sheep_milk_bowl",
						"cgt_mod_cheese_limbs:milk_bowl",
						"cgt_mod_cheese_limbs:sheep_milk_basket",
						"cgt_mod_cheese_limbs:highshroom",
						"cgt_mod_cheese_limbs:ieiunium_highshroom",
						"cgt_mod_cheese_limbs:fortis_highshroom",
						"cgt_mod_cheese_limbs:glowius_highshroom"), ConsumableConfig::isResourceLocation);
		BUILDER.pop();

		BUILDER.push("effects");
		defineEffect("human_flesh", "damage_boost", 6000, 0);
		defineEffect("human_flesh", "poison", 3000, 3);
		defineEffect("human_flesh", "confusion", 600, 9);
		defineEffect("guts", "movement_speed", 6000, 0);
		defineEffect("guts", "poison", 3000, 3);
		defineEffect("guts", "confusion", 3000, 9);
		defineEffect("epoisses", "damage_resistance", 3000, 0);
		defineEffect("tomme_du_jura", "fire_resistance", 3000, 0);
		defineEffect("morbier", "movement_speed", 3000, 0);
		defineEffect("raclette", "damage_boost", 1000, 0);
		defineEffect("bria_savarin", "slow_falling_1", 3000, 0);
		defineEffect("bria_savarin", "slow_falling_2", 3000, 0);
		defineEffect("casu_marzu", "confusion", 200, 5);
		defineEffect("raclette_plate", "damage_boost", 3000, 1);
		defineEffect("highshroom", "confusion", 400, 10);
		defineEffect("highshroom", "hunger", 400, 5);
		defineEffect("ieiunium_highshroom", "confusion", 400, 10);
		defineEffect("ieiunium_highshroom", "movement_speed", 400, 5);
		defineEffect("ieiunium_highshroom", "hunger", 400, 5);
		defineEffect("fortis_highshroom", "confusion", 1000, 10);
		defineEffect("fortis_highshroom", "movement_slowdown", 3000, 1);
		defineEffect("fortis_highshroom", "damage_resistance", 3000, 1);
		defineEffect("fortis_highshroom", "hunger", 400, 5);
		defineEffect("glowius_highshroom", "glowing", 1000, 1);
		defineEffect("glowius_highshroom", "confusion", 1000, 10);
		defineEffect("glowius_highshroom", "movement_slowdown", 3000, 1);
		defineEffect("glowius_highshroom", "hunger", 400, 5);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

	private ConsumableConfig() {
	}

	public static Supplier<MobEffectInstance> effect(String itemId, String effectName, MobEffect effect) {
		EffectSettings settings = EFFECT_SETTINGS.get(itemId + "." + effectName);
		if (settings == null) {
			throw new IllegalArgumentException("No consumable effect config for " + itemId + "." + effectName);
		}
		return () -> new MobEffectInstance(effect, settings.duration.get(), settings.strength.get());
	}

	private static void defineEffect(String itemId, String effectName, int defaultDuration, int defaultStrength) {
		BUILDER.push(itemId);
		ForgeConfigSpec.IntValue duration = BUILDER
				.comment("Effect duration in ticks.")
				.defineInRange(effectName + "_duration", defaultDuration, 0, Integer.MAX_VALUE);
		ForgeConfigSpec.IntValue strength = BUILDER
				.comment("Effect strength (Minecraft amplifier: 0 is level I, 1 is level II).")
				.defineInRange(effectName + "_strength", defaultStrength, 0, 255);
		BUILDER.pop();
		EFFECT_SETTINGS.put(itemId + "." + effectName, new EffectSettings(duration, strength));
	}

	private static boolean isResourceLocation(Object value) {
		return value instanceof String id && ResourceLocation.tryParse(id) != null;
	}

	private record EffectSettings(ForgeConfigSpec.IntValue duration, ForgeConfigSpec.IntValue strength) {
	}
}
