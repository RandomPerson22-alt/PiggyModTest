package com.randomperson22.piggymodtest.init;

import com.randomperson22.piggymodtest.items.ItemBaseballBat;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ModItems {
    public static final Item BASEBALL_BAT = new ItemBaseballBat()
            .setRegistryName("baseball_bat")
            .setTranslationKey("baseball_bat")
            .setCreativeTab(ModCreativeTabs.PIGGY_TAB);

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(BASEBALL_BAT);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        ModelLoader.setCustomModelResourceLocation(BASEBALL_BAT, 0,
                new ModelResourceLocation(BASEBALL_BAT.getRegistryName(), "inventory"));
    }
}