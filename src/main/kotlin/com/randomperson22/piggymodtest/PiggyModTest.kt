package com.randomperson22.piggymodtest

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.event.FMLServerStartingEvent
import software.bernie.geckolib3.GeckoLib

@Mod(
    modid = PiggyModTest.MODID,
    name = PiggyModTest.NAME,
    version = PiggyModTest.VERSION,
    guiFactory = "com.randomperson22.piggymodtest.PiggyModGuiFactory"
)
class PiggyModTest {

    @Suppress("UNUSED")
    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        GeckoLib.initialize()
        proxy!!.preInit(event)

    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        proxy!!.init(event)
    }

    @Suppress("UNUSED")
    @Mod.EventHandler
    fun serverStarting(event: FMLServerStartingEvent) {}

    @Suppress("UNUSED")
    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        proxy!!.postInit(event)
    }

    companion object {
        const val MODID = "piggymodtest"
        const val NAME = "Piggy Mod"
        const val VERSION = "1.0"

        @Mod.Instance
        @JvmStatic
        var instance: PiggyModTest? = null

        @SidedProxy(
            clientSide = "com.randomperson22.piggymodtest.ClientProxy",
            serverSide = "com.randomperson22.piggymodtest.CommonProxy"
        )
        @JvmStatic
        var proxy: CommonProxy? = null
   }
}