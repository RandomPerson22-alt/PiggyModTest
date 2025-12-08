package com.randomperson22.piggymodtest;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class PiggyModConfigGui extends GuiConfig {
    public PiggyModConfigGui(GuiScreen parent) {
        super(parent, getConfigElements(), "piggymodtest", false, false, "Piggy Mod Test Config");
    }

    private static List<IConfigElement> getConfigElements() {
        return new ConfigElement(PiggyModConfig.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements();
    }
}
