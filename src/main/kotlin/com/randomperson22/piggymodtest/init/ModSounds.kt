package com.randomperson22.piggymodtest.init

import com.randomperson22.piggymodtest.PiggyModTest
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Mod.EventBusSubscriber(modid = PiggyModTest.MODID)
object ModSounds {

    lateinit var FOOTSTEP1: SoundEvent
    lateinit var ENTITY_PIGGY_JUMPSCARE: SoundEvent
    lateinit var ENTITY_MOTHER_JUMPSCARE: SoundEvent
    lateinit var ENTITY_SHEEPY_JUMPSCARE: SoundEvent
    lateinit var ENTITY_KONA_JUMPSCARE: SoundEvent
    lateinit var ENTITY_OWELL_JUMPSCARE: SoundEvent
    lateinit var ENTITY_GRANDMOTHER_JUMPSCARE: SoundEvent
    lateinit var ENTITY_TEACHER_JUMPSCARE: SoundEvent
    lateinit var ENTITY_KITTY_JUMPSCARE: SoundEvent
    lateinit var ENTITY_MIMI_JUMPSCARE: SoundEvent
    lateinit var ENTITY_PANDY_JUMPSCARE: SoundEvent
    lateinit var ENTITY_MEMORY_JUMPSCARE: SoundEvent
    lateinit var ENTITY_DOGGY_JUMPSCARE: SoundEvent
    lateinit var ENTITY_DINOPIGGY_JUMPSCARE: SoundEvent
    lateinit var ENTITY_ZIZZY_JUMPSCARE: SoundEvent
    lateinit var ENTITY_ZOMPIGGY_JUMPSCARE: SoundEvent
    lateinit var ENTITY_SENTINEL_JUMPSCARE: SoundEvent
    lateinit var ENTITY_MRSTITCHY_JUMPSCARE: SoundEvent
    lateinit var ENTITY_ROBBY_JUMPSCARE: SoundEvent
    lateinit var ENTITY_DUOCARA_JUMPSCARE: SoundEvent
    lateinit var ENTITY_MRP_JUMPSCARE: SoundEvent
    lateinit var ENTITY_DESSA_JUMPSCARE: SoundEvent
    lateinit var ENTITY_BILLY_JUMPSCARE: SoundEvent
    lateinit var ENTITY_BELA_JUMPSCARE: SoundEvent
    lateinit var ENTITY_FROSTIGGY_JUMPSCARE: SoundEvent
    lateinit var ENTITY_DISTORTEDPENNY_JUMPSCARE: SoundEvent
    lateinit var ENTITY_PIGGYEXE_JUMPSCARE: SoundEvent
    lateinit var ENTITY_BADGY_JUMPSCARE: SoundEvent

    @SubscribeEvent
    @JvmStatic
    fun registerSounds(event: RegistryEvent.Register<SoundEvent>) {
        FOOTSTEP1 = register(event, "entity.piggy.step")
        ENTITY_PIGGY_JUMPSCARE = register(event, "entity.piggy.jumpscare")
        ENTITY_MOTHER_JUMPSCARE = register(event, "entity.mother.mother_jumpscare")
        ENTITY_SHEEPY_JUMPSCARE = register(event, "entity.sheepy.sheepy_jumpscare")
        ENTITY_KONA_JUMPSCARE = register(event, "entity.kona.kona_jumpscare")
        ENTITY_OWELL_JUMPSCARE = register(event, "entity.owell.owell_jumpscare")
        ENTITY_GRANDMOTHER_JUMPSCARE = register(event, "entity.grandmother.grandmother_jumpscare")
        ENTITY_TEACHER_JUMPSCARE = register(event, "entity.teacher.teacher_jumpscare")
        ENTITY_PANDY_JUMPSCARE = register(event, "entity.pandy.pandy_jumpscare")
        ENTITY_KITTY_JUMPSCARE = register(event, "entity.kitty.kitty_jumpscare")
        ENTITY_MIMI_JUMPSCARE = register(event, "entity.mimi.mimi_jumpscare")
        ENTITY_MEMORY_JUMPSCARE = register(event, "entity.memory.memory_jumpscare")
        ENTITY_DOGGY_JUMPSCARE = register(event, "entity.doggy.doggy_jumpscare")
        ENTITY_DINOPIGGY_JUMPSCARE = register(event, "entity.dinopiggy.dinopiggy_jumpscare")
        ENTITY_ZIZZY_JUMPSCARE = register(event, "entity.zizzy.zizzy_jumpscare")
        ENTITY_ZOMPIGGY_JUMPSCARE = register(event, "entity.zompiggy.zompiggy_jumpscare")
        ENTITY_SENTINEL_JUMPSCARE = register(event, "entity.sentinel.sentinel_jumpscare")
        ENTITY_MRSTITCHY_JUMPSCARE = register(event, "entity.mr_stitchy.mrstitchy_jumpscare")
        ENTITY_ROBBY_JUMPSCARE = register(event, "entity.robby.robby_jumpscare")
        ENTITY_DUOCARA_JUMPSCARE = register(event, "entity.duocara.duocara_jumpscare")
        ENTITY_MRP_JUMPSCARE = register(event, "entity.mr_p.mrp_jumpscare")
        ENTITY_DESSA_JUMPSCARE = register(event, "entity.dessa.dessa_jumpscare")
        ENTITY_BILLY_JUMPSCARE = register(event, "entity.billy.billy_jumpscare")
        ENTITY_BELA_JUMPSCARE = register(event, "entity.bela.bela_jumpscare")
        ENTITY_FROSTIGGY_JUMPSCARE = register(event, "entity.frostiggy.frostiggy_jumpscare")
        ENTITY_DISTORTEDPENNY_JUMPSCARE = register(event, "entity.distortedpenny.distortedpenny_jumpscare")
        ENTITY_PIGGYEXE_JUMPSCARE = register(event, "entity.piggyexe.piggyexe_jumpscare")
        ENTITY_BADGY_JUMPSCARE = register(event, "entity.badgy.badgy_jumpscare")
    }

    private fun register(event: RegistryEvent.Register<SoundEvent>, name: String): SoundEvent {
        val location = ResourceLocation(PiggyModTest.MODID, name)
        return SoundEvent(location).setRegistryName(location).also { event.registry.register(it) }
    }
}
