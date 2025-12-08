package com.randomperson22.piggymodtest;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class PiggyModConfig {
    public static Configuration config;

    public static void init(File configFile) {
        config = new Configuration(configFile);
        syncConfig();
    }

    public static void syncConfig() {

        if (config.hasChanged()) {
            config.save();
        }
    }
}