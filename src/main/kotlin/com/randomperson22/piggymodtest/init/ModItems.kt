package com.randomperson22.piggymodtest.init

import com.randomperson22.piggymodtest.items.ItemBaseballBat
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@Mod.EventBusSubscriber
object ModItems {
    @JvmField
    val BASEBALL_BAT: Item = ItemBaseballBat()
        .setRegistryName("baseball_bat")
        .setTranslationKey("baseball_bat")
        .setCreativeTab(ModCreativeTabs.PIGGY_TAB)

    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item?>) {
        event.getRegistry().register(BASEBALL_BAT)
    }

    @SideOnly(Side.CLIENT)
    fun registerModels() {
        ModelLoader.setCustomModelResourceLocation(
            BASEBALL_BAT, 0,
            ModelResourceLocation(BASEBALL_BAT.getRegistryName(), "inventory")
        )
    }
}