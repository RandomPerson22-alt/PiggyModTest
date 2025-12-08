package com.randomperson22.piggymodtest;

import com.randomperson22.piggymodtest.init.ModEntities;
import com.randomperson22.piggymodtest.init.ModNetwork;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        ModEntities.register();
    }

    public void init(FMLInitializationEvent event) {
        // Possibly general setup, nothing client-side here
    }

    public void postInit(FMLPostInitializationEvent event) {
        // Optional cleanup or mod interactions
    }
}