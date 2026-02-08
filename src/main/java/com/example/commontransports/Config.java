package com.example.commontransports;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
        private static final int DEFAULT_MAX_UPGRADES_PER_TYPE = 8;

        private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

        public static final ModConfigSpec.IntValue MAX_UPGRADES_PER_TYPE = BUILDER
                .comment("Maximum installed upgrades per type (speed/efficiency) for processing machines.")
                .defineInRange("maxUpgradesPerType", DEFAULT_MAX_UPGRADES_PER_TYPE, 1, 64);

        static final ModConfigSpec SPEC = BUILDER.build();

        public static int maxUpgradesPerType() {
                return MAX_UPGRADES_PER_TYPE.get();
        }
}
