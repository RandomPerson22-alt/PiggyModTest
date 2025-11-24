package com.randomperson22.piggymodtest

import com.randomperson22.piggymodtest.init.ModEntities
import com.randomperson22.piggymodtest.init.ModNetwork
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

@Suppress("UNUSED_PARAMETER")
open class CommonProxy {
    open fun preInit(event: FMLPreInitializationEvent?) {
        ModEntities.register()
        ModNetwork.registerPackets()
    }

    open fun init(event: FMLInitializationEvent?) {
        // Possibly general setup, nothing client-side here
    }

    open fun postInit(event: FMLPostInitializationEvent?) {
        // Optional cleanup or mod interactions
    }
}