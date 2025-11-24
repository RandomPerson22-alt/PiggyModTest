package com.randomperson22.piggymodtest

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.fml.client.IModGuiFactory

class PiggyModGuiFactory : IModGuiFactory {
    override fun initialize(minecraftInstance: Minecraft?) {}

    override fun hasConfigGui(): Boolean {
        return true
    }

    override fun createConfigGui(parentScreen: GuiScreen?): GuiScreen {
        return PiggyModConfigGui(parentScreen)
    }

    override fun runtimeGuiCategories(): MutableSet<IModGuiFactory.RuntimeOptionCategoryElement?>? {
        return null
    }
}
