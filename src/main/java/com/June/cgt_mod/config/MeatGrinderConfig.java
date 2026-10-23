package com.June.cgt_mod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class MeatGrinderConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.IntValue TANK_CAPACITY = BUILDER
            .comment("Maximum amount of blood the meat grinder tank can hold, in mB.")
            .defineInRange("tank_capacity", 1_000, 1_000, Integer.MAX_VALUE);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    private MeatGrinderConfig() {
    }
}
