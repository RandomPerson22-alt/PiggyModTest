package com.randomperson22.piggymodtest.init;

import com.randomperson22.piggymodtest.network.PacketJumpscare;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModNetwork {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("piggymod");

    public static void registerPackets() {
        int id = 0;
        INSTANCE.registerMessage(PacketJumpscare.Handler.class, PacketJumpscare.class, id++, Side.CLIENT);
    }
}