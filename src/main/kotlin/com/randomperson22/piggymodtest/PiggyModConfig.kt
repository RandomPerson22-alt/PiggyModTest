package com.randomperson22.piggymodtest

import net.minecraftforge.common.config.Configuration
import java.io.File

object PiggyModConfig {
    @JvmField
    var config: Configuration? = null

    fun init(configFile: File?) {
        config = Configuration(configFile)
        syncConfig()
    }

    fun syncConfig() {
        if (config!!.hasChanged()) {
            config!!.save()
        }
    }
}