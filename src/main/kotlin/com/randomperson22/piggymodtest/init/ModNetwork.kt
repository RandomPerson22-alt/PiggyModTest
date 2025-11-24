package com.randomperson22.piggymodtest.init

import com.randomperson22.piggymodtest.network.PacketJumpscare
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
import net.minecraftforge.fml.relauncher.Side

object ModNetwork {
    @JvmField
    val INSTANCE: SimpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("piggymod")

    fun registerPackets() {
        var id = 0
        INSTANCE.registerMessage(
            PacketJumpscare.Handler::class.java,
            PacketJumpscare::class.java,
            id++,
            Side.CLIENT
        )
        // INSTANCE.registerMessage(PacketAnimation.Handler::class.java, PacketAnimation::class.java, id++, Side.CLIENT) // not used yet
    }
}
