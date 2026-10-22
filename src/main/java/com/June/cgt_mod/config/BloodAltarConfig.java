package com.June.cgt_mod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class BloodAltarConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.IntValue TANK_CAPACITY = BUILDER
            .comment("Maximum amount of blood the altar tank can hold, in mB.")
            .defineInRange("tank_capacity", 10_000, 1, Integer.MAX_VALUE);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    private BloodAltarConfig() {
    }
}
