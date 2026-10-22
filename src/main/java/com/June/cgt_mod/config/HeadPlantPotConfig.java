package com.June.cgt_mod.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public final class HeadPlantPotConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> PLANT_WHITELIST =
            BUILDER.comment("Additional plant block IDs that can be placed in any head plant pot.")
                    .defineList("plant_whitelist", List.of(), HeadPlantPotConfig::isResourceLocation);

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> PLANT_BLACKLIST =
            BUILDER.comment("Plant block IDs that cannot be placed in head plant pots. The blacklist takes precedence.")
                    .defineList("plant_blacklist", List.of(), HeadPlantPotConfig::isResourceLocation);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    private HeadPlantPotConfig() {
    }

    public static boolean allowsPlant(String blockId, boolean vanillaPottable) {
        if (PLANT_BLACKLIST.get().contains(blockId)) {
            return false;
        }
        return vanillaPottable || PLANT_WHITELIST.get().contains(blockId);
    }

    private static boolean isResourceLocation(Object value) {
        return value instanceof String id && ResourceLocation.tryParse(id) != null;
    }
}
