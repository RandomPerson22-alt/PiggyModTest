package com.randomperson22.piggymodtest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import software.bernie.geckolib3.GeckoLib;

@Mod(
	    modid = PiggyModTest.MODID,
	    name = PiggyModTest.NAME,
	    version = PiggyModTest.VERSION,
	    guiFactory = "com.randomperson22.piggymodtest.PiggyModGuiFactory"
	)

public class PiggyModTest {
    public static final String MODID = "piggymodtest";
    public static final String NAME = "Piggy Mod Test";
    public static final String VERSION = "1.0";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    @Mod.Instance
    public static PiggyModTest instance;

    @SidedProxy(clientSide = "com.randomperson22.piggymodtest.ClientProxy",
                serverSide = "com.randomperson22.piggymodtest.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        GeckoLib.initialize();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}

