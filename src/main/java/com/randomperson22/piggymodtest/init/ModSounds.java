package com.randomperson22.piggymodtest.init;

import com.randomperson22.piggymodtest.PiggyModTest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = PiggyModTest.MODID)
public class ModSounds {
    public static SoundEvent ENTITY_PIGGY_JUMPSCARE;
    public static SoundEvent ENTITY_MOTHER_JUMPSCARE;
    public static SoundEvent ENTITY_SHEEPY_JUMPSCARE;
    public static SoundEvent ENTITY_KONA_JUMPSCARE;
    public static SoundEvent ENTITY_OWELL_JUMPSCARE;
    public static SoundEvent ENTITY_GRANDMOTHER_JUMPSCARE;
    public static SoundEvent ENTITY_TEACHER_JUMPSCARE;
    public static SoundEvent ENTITY_KITTY_JUMPSCARE;
    public static SoundEvent ENTITY_MIMI_JUMPSCARE;
    public static SoundEvent ENTITY_PANDY_JUMPSCARE;
    public static SoundEvent ENTITY_MEMORY_JUMPSCARE;
    public static SoundEvent ENTITY_DOGGY_JUMPSCARE;
    public static SoundEvent ENTITY_DINOPIGGY_JUMPSCARE;
    public static SoundEvent ENTITY_ZIZZY_JUMPSCARE;
    public static SoundEvent ENTITY_ZOMPIGGY_JUMPSCARE;
    public static SoundEvent ENTITY_SENTINEL_JUMPSCARE;
    public static SoundEvent ENTITY_MR_STITCHY_JUMPSCARE;
    public static SoundEvent ENTITY_ROBBY_JUMPSCARE;
    public static SoundEvent ENTITY_DUOCARA_JUMPSCARE;
    public static SoundEvent ENTITY_MR_P_JUMPSCARE;
    public static SoundEvent ENTITY_DESSA_JUMPSCARE;
    public static SoundEvent ENTITY_BILLY_JUMPSCARE;
    public static SoundEvent ENTITY_BELA_JUMPSCARE;
    public static SoundEvent ENTITY_FROSTIGGY_JUMPSCARE;
    public static SoundEvent ENTITY_DISTORTEDPENNY_JUMPSCARE;
    public static SoundEvent ENTITY_PIGGYEXE_JUMPSCARE;
    public static SoundEvent ENTITY_BADGY_JUMPSCARE;
    public static SoundEvent ENTITY_KAMOSI_JUMPSCARE;
    public static SoundEvent FOOTSTEPS;
    public static SoundEvent DUOCARA_MOVE;

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        ENTITY_PIGGY_JUMPSCARE        = register(event, "entity.piggy.jumpscare");
        ENTITY_MOTHER_JUMPSCARE       = register(event, "entity.mother.mother_jumpscare");
        ENTITY_SHEEPY_JUMPSCARE       = register(event, "entity.sheepy.sheepy_jumpscare");
        ENTITY_KONA_JUMPSCARE         = register(event, "entity.kona.kona_jumpscare");
        ENTITY_OWELL_JUMPSCARE        = register(event, "entity.owell.owell_jumpscare");
        ENTITY_GRANDMOTHER_JUMPSCARE  = register(event, "entity.grandmother.grandmother_jumpscare");
        ENTITY_TEACHER_JUMPSCARE      = register(event, "entity.teacher.teacher_jumpscare");
        ENTITY_PANDY_JUMPSCARE        = register(event, "entity.pandy.pandy_jumpscare");
        ENTITY_KITTY_JUMPSCARE        = register(event, "entity.kitty.kitty_jumpscare");
        ENTITY_MIMI_JUMPSCARE        = register(event, "entity.mimi.mimi_jumpscare");
        ENTITY_MEMORY_JUMPSCARE       = register(event, "entity.memory.memory_jumpscare");
        ENTITY_DOGGY_JUMPSCARE        = register(event, "entity.doggy.doggy_jumpscare");
        ENTITY_DINOPIGGY_JUMPSCARE    = register(event, "entity.dinopiggy.dinopiggy_jumpscare");
        ENTITY_ZIZZY_JUMPSCARE    = register(event, "entity.zizzy.zizzy_jumpscare");
        ENTITY_ZOMPIGGY_JUMPSCARE    = register(event, "entity.zompiggy.zompiggy_jumpscare");
        ENTITY_SENTINEL_JUMPSCARE    = register(event, "entity.sentinel.sentinel_jumpscare");
        ENTITY_MR_STITCHY_JUMPSCARE    = register(event, "entity.mr_stitchy.mrstitchy_jumpscare");
        ENTITY_ROBBY_JUMPSCARE    = register(event, "entity.robby.robby_jumpscare");
        ENTITY_DUOCARA_JUMPSCARE    = register(event, "entity.duocara.duocara_jumpscare");
        ENTITY_MR_P_JUMPSCARE    = register(event, "entity.mr_p.mrp_jumpscare");
        ENTITY_DESSA_JUMPSCARE    = register(event, "entity.dessa.dessa_jumpscare");
        ENTITY_BILLY_JUMPSCARE    = register(event, "entity.billy.billy_jumpscare");
	    ENTITY_BELA_JUMPSCARE    = register(event, "entity.bela.bela_jumpscare");
	    ENTITY_FROSTIGGY_JUMPSCARE    = register(event, "entity.frostiggy.frostiggy_jumpscare");
	    ENTITY_DISTORTEDPENNY_JUMPSCARE    = register(event, "entity.distortedpenny.distortedpenny_jumpscare");
	    ENTITY_PIGGYEXE_JUMPSCARE    = register(event, "entity.piggyexe.piggyexe_jumpscare");
        ENTITY_BADGY_JUMPSCARE    = register(event, "entity.badgy.badgy_jumpscare");
        ENTITY_KAMOSI_JUMPSCARE    = register(event, "entity.kamosi.kamosi_jumpscare");
        FOOTSTEPS = register(event, "footsteps.footsteps");
        DUOCARA_MOVE = register(event, "footsteps.duocara_move");
    }

    private static SoundEvent register(RegistryEvent.Register<SoundEvent> event, String name) {
        ResourceLocation location = new ResourceLocation(PiggyModTest.MODID, name);
        SoundEvent sound = new SoundEvent(location).setRegistryName(location);
        event.getRegistry().register(sound);
        return sound;
    }
}
