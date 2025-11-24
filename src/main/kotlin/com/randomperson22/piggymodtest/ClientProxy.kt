package com.randomperson22.piggymodtest

import com.randomperson22.piggymodtest.blocks.default_trap.RenderDefaultTrap
import com.randomperson22.piggymodtest.blocks.default_trap.TileEntityDefaultTrap
import com.randomperson22.piggymodtest.blocks.door.RenderDoorBlock
import com.randomperson22.piggymodtest.blocks.door.TileEntityDoor
import com.randomperson22.piggymodtest.entities.badgy.BadgyRenderer
import com.randomperson22.piggymodtest.entities.badgy.EntityBadgy
import com.randomperson22.piggymodtest.entities.bela.BelaRenderer
import com.randomperson22.piggymodtest.entities.bela.EntityBela
import com.randomperson22.piggymodtest.entities.billy.BillyRenderer
import com.randomperson22.piggymodtest.entities.billy.EntityBilly
import com.randomperson22.piggymodtest.entities.dinopiggy.DinopiggyRenderer
import com.randomperson22.piggymodtest.entities.dinopiggy.EntityDinopiggy
import com.randomperson22.piggymodtest.entities.distorted_penny.DistortedPennyRenderer
import com.randomperson22.piggymodtest.entities.distorted_penny.EntityDistortedPenny
import com.randomperson22.piggymodtest.entities.doggy.DoggyRenderer
import com.randomperson22.piggymodtest.entities.doggy.EntityDoggy
import com.randomperson22.piggymodtest.entities.duocara.DuocaraRenderer
import com.randomperson22.piggymodtest.entities.duocara.EntityDuocara
import com.randomperson22.piggymodtest.entities.father.EntityFather
import com.randomperson22.piggymodtest.entities.father.FatherRenderer
import com.randomperson22.piggymodtest.entities.frostiggy.EntityFrostiggy
import com.randomperson22.piggymodtest.entities.frostiggy.FrostiggyRenderer
import com.randomperson22.piggymodtest.entities.georgie.EntityGeorgie
import com.randomperson22.piggymodtest.entities.georgie.GeorgieRenderer
import com.randomperson22.piggymodtest.entities.glitch_memory.EntityGlitchMemory
import com.randomperson22.piggymodtest.entities.glitch_memory.GlitchMemoryRenderer
import com.randomperson22.piggymodtest.entities.grandmother.EntityGrandmother
import com.randomperson22.piggymodtest.entities.grandmother.GrandmotherRenderer
import com.randomperson22.piggymodtest.entities.kitty.EntityKitty
import com.randomperson22.piggymodtest.entities.kitty.KittyRenderer
import com.randomperson22.piggymodtest.entities.kona.EntityKona
import com.randomperson22.piggymodtest.entities.kona.KonaRenderer
import com.randomperson22.piggymodtest.entities.memory.EntityMemory
import com.randomperson22.piggymodtest.entities.memory.MemoryRenderer
import com.randomperson22.piggymodtest.entities.mimi.EntityMimi
import com.randomperson22.piggymodtest.entities.mimi.MimiRenderer
import com.randomperson22.piggymodtest.entities.mother.EntityMother
import com.randomperson22.piggymodtest.entities.mother.MotherRenderer
import com.randomperson22.piggymodtest.entities.mr_p.EntityMrP
import com.randomperson22.piggymodtest.entities.mr_p.MrPRenderer
import com.randomperson22.piggymodtest.entities.mr_stitchy.EntityMrStitchy
import com.randomperson22.piggymodtest.entities.mr_stitchy.MrStitchyRenderer
import com.randomperson22.piggymodtest.entities.owell.EntityOwell
import com.randomperson22.piggymodtest.entities.owell.OwellRenderer
import com.randomperson22.piggymodtest.entities.pandy.EntityPandy
import com.randomperson22.piggymodtest.entities.pandy.PandyRenderer
import com.randomperson22.piggymodtest.entities.piggy.EntityPiggy
import com.randomperson22.piggymodtest.entities.piggy.PiggyRenderer
import com.randomperson22.piggymodtest.entities.piggyexe.EntityPiggyExe
import com.randomperson22.piggymodtest.entities.piggyexe.PiggyExeRenderer
import com.randomperson22.piggymodtest.entities.reindessa.EntityReindessa
import com.randomperson22.piggymodtest.entities.reindessa.ReindessaRenderer
import com.randomperson22.piggymodtest.entities.robby.EntityRobby
import com.randomperson22.piggymodtest.entities.robby.RobbyRenderer
import com.randomperson22.piggymodtest.entities.sentinels.*
import com.randomperson22.piggymodtest.entities.sheepy.EntitySheepy
import com.randomperson22.piggymodtest.entities.sheepy.SheepyRenderer
import com.randomperson22.piggymodtest.entities.teacher.EntityTeacher
import com.randomperson22.piggymodtest.entities.teacher.TeacherRenderer
import com.randomperson22.piggymodtest.entities.umbra.EntityUmbra
import com.randomperson22.piggymodtest.entities.umbra.UmbraRenderer
import com.randomperson22.piggymodtest.entities.zizzy.EntityZizzy
import com.randomperson22.piggymodtest.entities.zizzy.ZizzyRenderer
import com.randomperson22.piggymodtest.entities.zompiggy.EntityZompiggy
import com.randomperson22.piggymodtest.entities.zompiggy.ZompiggyRenderer
import com.randomperson22.piggymodtest.events.Jumpscare_Handler
import com.randomperson22.piggymodtest.events.TickHandler
import com.randomperson22.piggymodtest.init.ModBlocks
import com.randomperson22.piggymodtest.init.ModItems
import com.randomperson22.piggymodtest.npc.doggy.DoggyNpcRenderer
import com.randomperson22.piggymodtest.npc.doggy.EntityDoggyNpc
import com.randomperson22.piggymodtest.npc.georgie.EntityGeorgieNpc
import com.randomperson22.piggymodtest.npc.georgie.GeorgieNpcRenderer
import com.randomperson22.piggymodtest.npc.pony.EntityPonyNpc
import com.randomperson22.piggymodtest.npc.pony.PonyNpcRenderer
import com.randomperson22.piggymodtest.npc.zizzy.EntityZizzyNpc
import com.randomperson22.piggymodtest.npc.zizzy.ZizzyNpcRenderer
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.client.registry.RenderingRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Mod.EventBusSubscriber(modid = PiggyModTest.MODID, value = [Side.CLIENT])
class ClientProxy : CommonProxy() {
    override fun preInit(event: FMLPreInitializationEvent?) {
        super.preInit(event)
        Jumpscare_Handler.init()
    }

    override fun init(event: FMLInitializationEvent?) {
        TickHandler.register()
    }

    override fun postInit(event: FMLPostInitializationEvent?) {
        // post-initialization logic
    }

    companion object {
        private val LOGGER: Logger = LogManager.getLogger()

        @SubscribeEvent
        @SideOnly(Side.CLIENT)
        @Suppress("UNUSED_PARAMETER")
        fun registerRenderers(event: ModelRegistryEvent?) {
            LOGGER.info("Registering renderers for PiggyModTest")

            // Entity renderers
            RenderingRegistry.registerEntityRenderingHandler(EntityPiggy::class.java) { renderManager ->
                PiggyRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityGeorgie::class.java) { renderManager ->
                GeorgieRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityMother::class.java) { renderManager ->
                MotherRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityFather::class.java) { renderManager ->
                FatherRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityGrandmother::class.java) { renderManager ->
                GrandmotherRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityTeacher::class.java) { renderManager ->
                TeacherRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityKona::class.java) { renderManager ->
                KonaRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntitySheepy::class.java) { renderManager ->
                SheepyRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityOwell::class.java) { renderManager ->
                OwellRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityPandy::class.java) { renderManager ->
                PandyRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityMemory::class.java) { renderManager ->
                MemoryRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityKitty::class.java) { renderManager ->
                KittyRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityDoggy::class.java) { renderManager ->
                DoggyRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityMimi::class.java) { renderManager ->
                MimiRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityDinopiggy::class.java) { renderManager ->
                DinopiggyRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityUmbra::class.java) { renderManager ->
                UmbraRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityZizzy::class.java) { renderManager ->
                ZizzyRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityZizzyNpc::class.java) { renderManager ->
                ZizzyNpcRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityZompiggy::class.java) { renderManager ->
                ZompiggyRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntitySentinelRed::class.java) { renderManager ->
                SentinelRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntitySentinelGreen::class.java) { renderManager ->
                SentinelRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntitySentinelLightBlue::class.java) { renderManager ->
                SentinelRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntitySentinelPink::class.java) { renderManager ->
                SentinelRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntitySentinelShadow::class.java) { renderManager ->
                SentinelRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntitySentinelWhite::class.java) { renderManager ->
                SentinelRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityMrStitchy::class.java) { renderManager ->
                MrStitchyRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityRobby::class.java) { renderManager ->
                RobbyRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityDuocara::class.java) { renderManager ->
                DuocaraRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityMrP::class.java) { renderManager ->
                MrPRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityReindessa::class.java) { renderManager ->
                ReindessaRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityBilly::class.java) { renderManager ->
                BillyRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityBela::class.java) { renderManager ->
                BelaRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityFrostiggy::class.java) { renderManager ->
                FrostiggyRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityGlitchMemory::class.java) { renderManager ->
                GlitchMemoryRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityDistortedPenny::class.java) { renderManager ->
                DistortedPennyRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityPiggyExe::class.java) { renderManager ->
                PiggyExeRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityGeorgieNpc::class.java) { renderManager ->
                GeorgieNpcRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityDoggyNpc::class.java) { renderManager ->
                DoggyNpcRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityPonyNpc::class.java) { renderManager ->
                PonyNpcRenderer(renderManager)
            }
            RenderingRegistry.registerEntityRenderingHandler(EntityBadgy::class.java) { renderManager ->
                BadgyRenderer(renderManager)
            }

            // Tile entity renderers
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoor::class.java, RenderDoorBlock())
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDefaultTrap::class.java, RenderDefaultTrap())

            // Item models
            registerItemModels()
        }

        @SubscribeEvent
        @Suppress("UNUSED_PARAMETER", "ControlFlowWithEmptyBody")
        fun onClientTick(event: TickEvent.PlayerTickEvent) {
            if (event.side == Side.CLIENT && event.phase == TickEvent.Phase.END && event.player === Minecraft.getMinecraft().player) {
            }
        }

        @SideOnly(Side.CLIENT)
        private fun registerItemModels() {
            LOGGER.info("Registering item models...")

            // You can replace these with ModItems.registerModels() if you move the code there
            ModelLoader.setCustomModelResourceLocation(
                ModItems.BASEBALL_BAT, 0,
                ModelResourceLocation(PiggyModTest.MODID + ":baseball_bat", "inventory")
            )

            ModelLoader.setCustomModelResourceLocation(
                Item.getItemFromBlock(ModBlocks.VENT_BLOCK), 0,
                ModelResourceLocation(PiggyModTest.MODID + ":vent", "inventory")
            )


            /*ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.DOOR_BLOCK), 0,
                new ModelResourceLocation(PiggyModTest.MODID + ":door", "inventory"));*/
            ModelLoader.setCustomModelResourceLocation(
                Item.getItemFromBlock(ModBlocks.INVISIBLE_DEATH_BLOCK), 0,
                ModelResourceLocation(PiggyModTest.MODID + ":invisible_death_block", "inventory")
            )


            /*ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.DEFAULT_TRAP_BLOCK), 0,
                new ModelResourceLocation(PiggyModTest.MODID + ":default_trap", "inventory"));*/
        }
    }
}