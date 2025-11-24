package com.randomperson22.piggymodtest.init

import com.randomperson22.piggymodtest.PiggyModTest
import com.randomperson22.piggymodtest.blocks.deathblock.BlockInvisibleDeath
import com.randomperson22.piggymodtest.blocks.vent.BlockVent
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Mod.EventBusSubscriber(modid = PiggyModTest.MODID)
object ModBlocks {
    var VENT_BLOCK: Block? = null

    //public static Block DOOR_BLOCK;
    @JvmField
    var INVISIBLE_DEATH_BLOCK: Block? = null // Add this line

    //public static Block DEFAULT_TRAP_BLOCK;
    @SubscribeEvent
    fun registerBlocks(event: RegistryEvent.Register<Block?>) {
        VENT_BLOCK = BlockVent("vent").setLightOpacity(5).setLightLevel(0.0f)
        //DOOR_BLOCK = new BlockDoorBlock("door");
        INVISIBLE_DEATH_BLOCK = BlockInvisibleDeath("invisible_death_block") // Add this line

        //DEFAULT_TRAP_BLOCK = new BlockDefaultTrap("default_trap");
        event.getRegistry().registerAll(
            VENT_BLOCK,  //DOOR_BLOCK,
            INVISIBLE_DEATH_BLOCK //DEFAULT_TRAP_BLOCK
        )


        // Register tile entity for the animated block
        /*GameRegistry.registerTileEntity(
            TileEntityDefaultTrap.class,
            new ResourceLocation(PiggyModTest.MODID, "tileentity_default_trap")
        );
        
        // Register tile entity for the animated block
        GameRegistry.registerTileEntity(
            TileEntityDoor.class,
            new ResourceLocation(PiggyModTest.MODID, "tileentity_door")
        );*/
    }

    @SubscribeEvent
    fun registerItemBlocks(event: RegistryEvent.Register<Item?>) {
        event.getRegistry().register(ItemBlock(VENT_BLOCK).setRegistryName(VENT_BLOCK!!.getRegistryName()))
        // event.getRegistry().register(new ItemBlock(DOOR_BLOCK).setRegistryName(DOOR_BLOCK.getRegistryName()));
        event.getRegistry()
            .register(ItemBlock(INVISIBLE_DEATH_BLOCK).setRegistryName(INVISIBLE_DEATH_BLOCK!!.getRegistryName()))
        //event.getRegistry().register(new ItemBlock(DEFAULT_TRAP_BLOCK).setRegistryName(DEFAULT_TRAP_BLOCK.getRegistryName()));
    }
}

