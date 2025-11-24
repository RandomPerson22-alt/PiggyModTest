package com.randomperson22.piggymodtest;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib.GeckoLib;
import com.randomperson22.piggymodtest.init.ModEntities;

@Mod(PiggyModTest.MODID)
public class PiggyModTest
{
    public static final String MODID = "piggymodtest";

    public PiggyModTest(FMLJavaModLoadingContext context)
    {
        // 👇 Important for GeckoLib to work!
        GeckoLib.initialize();

        // Register your entities, items, etc.
        ModEntities.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {}
    }
}