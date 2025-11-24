package com.randomperson22.piggymodtest

import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.common.config.ConfigElement
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.client.config.GuiConfig
import net.minecraftforge.fml.client.config.IConfigElement

class PiggyModConfigGui(parent: GuiScreen?) : GuiConfig(
    parent,
    configElements, "piggymodtest", false, false, "Piggy Mod Test Config"
) {
    companion object {
        private val configElements: MutableList<IConfigElement?>?
            get() = ConfigElement(PiggyModConfig.config!!.getCategory(Configuration.CATEGORY_GENERAL))
                .getChildElements()
    }
}
