package com.randomperson22.piggymodtest;

import com.randomperson22.piggymodtest.blocks.default_trap.RenderDefaultTrapTESR;
import com.randomperson22.piggymodtest.blocks.default_trap.TileEntityDefaultTrap;
import com.randomperson22.piggymodtest.blocks.door.ModelDoorBlock;
import com.randomperson22.piggymodtest.blocks.door.RenderDoorBlock;
import com.randomperson22.piggymodtest.blocks.door.RenderDoorBlockTESR;
import com.randomperson22.piggymodtest.blocks.door.TileEntityDoor;
import com.randomperson22.piggymodtest.entities.badgy.BadgyRenderer;
import com.randomperson22.piggymodtest.entities.badgy.EntityBadgy;
import com.randomperson22.piggymodtest.entities.bela.BelaRenderer;
import com.randomperson22.piggymodtest.entities.bela.EntityBela;
import com.randomperson22.piggymodtest.entities.billy.BillyRenderer;
import com.randomperson22.piggymodtest.entities.billy.EntityBilly;
import com.randomperson22.piggymodtest.entities.dinopiggy.DinopiggyRenderer;
import com.randomperson22.piggymodtest.entities.dinopiggy.EntityDinopiggy;
import com.randomperson22.piggymodtest.entities.distorted_penny.DistortedPennyRenderer;
import com.randomperson22.piggymodtest.entities.distorted_penny.EntityDistortedPenny;
import com.randomperson22.piggymodtest.entities.doggy.DoggyRenderer;
import com.randomperson22.piggymodtest.entities.doggy.EntityDoggy;
import com.randomperson22.piggymodtest.entities.duocara.DuocaraRenderer;
import com.randomperson22.piggymodtest.entities.duocara.EntityDuocara;
import com.randomperson22.piggymodtest.blocks.default_trap.RenderDefaultTrap;
import com.randomperson22.piggymodtest.entities.father.EntityFather;
import com.randomperson22.piggymodtest.entities.father.FatherRenderer;
import com.randomperson22.piggymodtest.entities.frostiggy.EntityFrostiggy;
import com.randomperson22.piggymodtest.entities.frostiggy.FrostiggyRenderer;
import com.randomperson22.piggymodtest.entities.georgie.EntityGeorgie;
import com.randomperson22.piggymodtest.entities.georgie.GeorgieRenderer;
import com.randomperson22.piggymodtest.entities.glitch_memory.EntityGlitchMemory;
import com.randomperson22.piggymodtest.entities.glitch_memory.GlitchMemoryRenderer;
import com.randomperson22.piggymodtest.entities.grandmother.EntityGrandmother;
import com.randomperson22.piggymodtest.entities.grandmother.GrandmotherRenderer;
import com.randomperson22.piggymodtest.entities.kamosi.EntityKamosi;
import com.randomperson22.piggymodtest.entities.kamosi.KamosiRenderer;
import com.randomperson22.piggymodtest.entities.kitty.EntityKitty;
import com.randomperson22.piggymodtest.entities.kitty.KittyRenderer;
import com.randomperson22.piggymodtest.entities.kona.EntityKona;
import com.randomperson22.piggymodtest.entities.kona.KonaRenderer;
import com.randomperson22.piggymodtest.entities.memory.EntityMemory;
import com.randomperson22.piggymodtest.entities.memory.MemoryRenderer;
import com.randomperson22.piggymodtest.entities.mimi.EntityMimi;
import com.randomperson22.piggymodtest.entities.mimi.MimiRenderer;
import com.randomperson22.piggymodtest.entities.mother.EntityMother;
import com.randomperson22.piggymodtest.entities.mother.MotherRenderer;
import com.randomperson22.piggymodtest.entities.mr_p.EntityMrP;
import com.randomperson22.piggymodtest.entities.mr_p.MrPRenderer;
import com.randomperson22.piggymodtest.entities.mr_stitchy.EntityMrStitchy;
import com.randomperson22.piggymodtest.entities.mr_stitchy.MrStitchyRenderer;
import com.randomperson22.piggymodtest.entities.owell.EntityOwell;
import com.randomperson22.piggymodtest.entities.owell.OwellRenderer;
import com.randomperson22.piggymodtest.entities.pandy.EntityPandy;
import com.randomperson22.piggymodtest.entities.pandy.PandyRenderer;
import com.randomperson22.piggymodtest.entities.piggy.EntityPiggy;
import com.randomperson22.piggymodtest.entities.piggy.PiggyRenderer;
import com.randomperson22.piggymodtest.entities.piggyexe.EntityPiggyExe;
import com.randomperson22.piggymodtest.entities.piggyexe.PiggyExeRenderer;
import com.randomperson22.piggymodtest.entities.reindessa.EntityReindessa;
import com.randomperson22.piggymodtest.entities.reindessa.ReindessaRenderer;
import com.randomperson22.piggymodtest.entities.robby.EntityRobby;
import com.randomperson22.piggymodtest.entities.robby.RobbyRenderer;
import com.randomperson22.piggymodtest.entities.sentinels.EntitySentinelGreen;
import com.randomperson22.piggymodtest.entities.sentinels.EntitySentinelLightBlue;
import com.randomperson22.piggymodtest.entities.sentinels.EntitySentinelPink;
import com.randomperson22.piggymodtest.entities.sentinels.EntitySentinelRed;
import com.randomperson22.piggymodtest.entities.sentinels.EntitySentinelShadow;
import com.randomperson22.piggymodtest.entities.sentinels.EntitySentinelWhite;
import com.randomperson22.piggymodtest.entities.sentinels.SentinelRenderer;
import com.randomperson22.piggymodtest.entities.sheepy.EntitySheepy;
import com.randomperson22.piggymodtest.entities.sheepy.SheepyRenderer;
import com.randomperson22.piggymodtest.entities.teacher.EntityTeacher;
import com.randomperson22.piggymodtest.entities.teacher.TeacherRenderer;
import com.randomperson22.piggymodtest.entities.test.EntityTest;
import com.randomperson22.piggymodtest.entities.test.TestRenderer;
import com.randomperson22.piggymodtest.entities.umbra.EntityUmbra;
import com.randomperson22.piggymodtest.entities.umbra.UmbraRenderer;
import com.randomperson22.piggymodtest.entities.zizzy.EntityZizzy;
import com.randomperson22.piggymodtest.entities.zizzy.ZizzyRenderer;
import com.randomperson22.piggymodtest.entities.zompiggy.EntityZompiggy;
import com.randomperson22.piggymodtest.entities.zompiggy.ZompiggyRenderer;
import com.randomperson22.piggymodtest.events.Jumpscare_Handler;
import com.randomperson22.piggymodtest.events.TickHandler;
import com.randomperson22.piggymodtest.init.ModBlocks;
import com.randomperson22.piggymodtest.init.ModItems;
import com.randomperson22.piggymodtest.init.ModNetwork;
import com.randomperson22.piggymodtest.npc.doggy.DoggyNpcRenderer;
import com.randomperson22.piggymodtest.npc.doggy.EntityDoggyNpc;
import com.randomperson22.piggymodtest.npc.georgie.EntityGeorgieNpc;
import com.randomperson22.piggymodtest.npc.georgie.GeorgieNpcRenderer;
import com.randomperson22.piggymodtest.npc.pony.EntityPonyNpc;
import com.randomperson22.piggymodtest.npc.pony.PonyNpcRenderer;
import com.randomperson22.piggymodtest.npc.zizzy.EntityZizzyNpc;
import com.randomperson22.piggymodtest.npc.zizzy.ZizzyNpcRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

@Mod.EventBusSubscriber(modid = PiggyModTest.MODID, value = Side.CLIENT)
public class ClientProxy extends CommonProxy {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        Jumpscare_Handler.init();
        ModNetwork.registerPackets();
    }

    @Override
    public void init(FMLInitializationEvent event) {
    	 TickHandler.register();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        // post-initialization logic
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerRenderers(ModelRegistryEvent event) {
        LOGGER.info("Registering renderers for PiggyModTest");

        // Entity renderers
        RenderingRegistry.registerEntityRenderingHandler(EntityPiggy.class, PiggyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGeorgie.class, GeorgieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMother.class, MotherRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFather.class, FatherRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGrandmother.class, GrandmotherRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTeacher.class, TeacherRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityKona.class, KonaRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySheepy.class, SheepyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityOwell.class, OwellRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPandy.class, PandyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMemory.class, MemoryRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityKitty.class, KittyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDoggy.class, DoggyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMimi.class, MimiRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDinopiggy.class, DinopiggyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTest.class, TestRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityUmbra.class, UmbraRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityZizzy.class, ZizzyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityZizzyNpc.class, ZizzyNpcRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityZompiggy.class, ZompiggyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySentinelRed.class, SentinelRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySentinelGreen.class, SentinelRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySentinelLightBlue.class, SentinelRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySentinelPink.class, SentinelRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySentinelShadow.class, SentinelRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySentinelWhite.class, SentinelRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMrStitchy.class, MrStitchyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRobby.class, RobbyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDuocara.class, DuocaraRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMrP.class, MrPRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityReindessa.class, ReindessaRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBilly.class, BillyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBela.class, BelaRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFrostiggy.class, FrostiggyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGlitchMemory.class, GlitchMemoryRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDistortedPenny.class, DistortedPennyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPiggyExe.class, PiggyExeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGeorgieNpc.class, GeorgieNpcRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDoggyNpc.class, DoggyNpcRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPonyNpc.class, PonyNpcRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBadgy.class, BadgyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityKamosi.class, KamosiRenderer::new);

        // Tile entity renderers
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoor.class, new RenderDoorBlockTESR());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDefaultTrap.class, new RenderDefaultTrapTESR());
        
        // Item models
        registerItemModels();
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.PlayerTickEvent event) {
        if (event.side == Side.CLIENT && event.phase == TickEvent.Phase.END &&
            event.player == Minecraft.getMinecraft().player) {
        }
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModels() {
        LOGGER.info("Registering item models...");

        // You can replace these with ModItems.registerModels() if you move the code there
        ModelLoader.setCustomModelResourceLocation(ModItems.BASEBALL_BAT, 0,
                new ModelResourceLocation(PiggyModTest.MODID + ":baseball_bat", "inventory"));
        
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.VENT_BLOCK), 0,
                new ModelResourceLocation(PiggyModTest.MODID + ":vent", "inventory"));
        
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.DOOR_BLOCK), 0,
                new ModelResourceLocation(PiggyModTest.MODID + ":door", "inventory"));

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.INVISIBLE_DEATH_BLOCK), 0,
                new ModelResourceLocation(PiggyModTest.MODID + ":invisible_death_block", "inventory"));
        
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.DEFAULT_TRAP_BLOCK), 0,
                new ModelResourceLocation(PiggyModTest.MODID + ":default_trap", "inventory"));
    }
}
