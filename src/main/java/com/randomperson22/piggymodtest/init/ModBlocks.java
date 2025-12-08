package com.randomperson22.piggymodtest.init;

import com.randomperson22.piggymodtest.PiggyModTest;
import com.randomperson22.piggymodtest.blocks.deathblock.BlockInvisibleDeath;
import com.randomperson22.piggymodtest.blocks.default_trap.BlockDefaultTrap;
import com.randomperson22.piggymodtest.blocks.default_trap.TileEntityDefaultTrap;
import com.randomperson22.piggymodtest.blocks.door.BlockDoorBlock;
import com.randomperson22.piggymodtest.blocks.door.TileEntityDoor;
import com.randomperson22.piggymodtest.blocks.vent.BlockVent;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.util.ResourceLocation;

@Mod.EventBusSubscriber(modid = PiggyModTest.MODID)
public class ModBlocks {
    public static Block VENT_BLOCK;
    public static Block DOOR_BLOCK;
    public static Block INVISIBLE_DEATH_BLOCK; // Add this line
    public static Block DEFAULT_TRAP_BLOCK;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        VENT_BLOCK = new BlockVent("vent").setLightOpacity(5).setLightLevel(0.0f);
        DOOR_BLOCK = new BlockDoorBlock("door");
        INVISIBLE_DEATH_BLOCK = new BlockInvisibleDeath("invisible_death_block"); // Add this line
        DEFAULT_TRAP_BLOCK = new BlockDefaultTrap("default_trap");

        event.getRegistry().registerAll(
            VENT_BLOCK,
            DOOR_BLOCK,
            INVISIBLE_DEATH_BLOCK,
            DEFAULT_TRAP_BLOCK
        );


        // Register tile entity for the animated block
        GameRegistry.registerTileEntity(
            TileEntityDefaultTrap.class,
            new ResourceLocation(PiggyModTest.MODID, "tileentity_default_trap")
        );
        
        // Register tile entity for the animated block
        GameRegistry.registerTileEntity(
            TileEntityDoor.class,
            new ResourceLocation(PiggyModTest.MODID, "tileentity_door")
        );
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(VENT_BLOCK).setRegistryName(VENT_BLOCK.getRegistryName()));
        event.getRegistry().register(new ItemBlock(DOOR_BLOCK).setRegistryName(DOOR_BLOCK.getRegistryName()));
        event.getRegistry().register(new ItemBlock(INVISIBLE_DEATH_BLOCK).setRegistryName(INVISIBLE_DEATH_BLOCK.getRegistryName()));
        event.getRegistry().register(new ItemBlock(DEFAULT_TRAP_BLOCK).setRegistryName(DEFAULT_TRAP_BLOCK.getRegistryName()));
    }

}

