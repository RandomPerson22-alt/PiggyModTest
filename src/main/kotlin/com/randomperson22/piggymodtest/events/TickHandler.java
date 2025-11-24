package com.randomperson22.piggymodtest.events;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TickHandler {

    public static void register() {
        MinecraftForge.EVENT_BUS.register(new TickHandler());
    }
}
