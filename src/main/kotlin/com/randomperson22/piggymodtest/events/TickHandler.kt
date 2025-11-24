package com.randomperson22.piggymodtest.events

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
object TickHandler {

    fun register() {
        MinecraftForge.EVENT_BUS.register(this)
    }
}
