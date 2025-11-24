package com.randomperson22.piggymodtest.init

import com.randomperson22.piggymodtest.PiggyModTest
import com.randomperson22.piggymodtest.PiggyModTest.Companion.instance
import com.randomperson22.piggymodtest.entities.badgy.EntityBadgy
import com.randomperson22.piggymodtest.entities.bela.EntityBela
import com.randomperson22.piggymodtest.entities.billy.EntityBilly
import com.randomperson22.piggymodtest.entities.dinopiggy.EntityDinopiggy
import com.randomperson22.piggymodtest.entities.distorted_penny.EntityDistortedPenny
import com.randomperson22.piggymodtest.entities.doggy.EntityDoggy
import com.randomperson22.piggymodtest.entities.duocara.EntityDuocara
import com.randomperson22.piggymodtest.entities.father.EntityFather
import com.randomperson22.piggymodtest.entities.frostiggy.EntityFrostiggy
import com.randomperson22.piggymodtest.entities.georgie.EntityGeorgie
import com.randomperson22.piggymodtest.entities.glitch_memory.EntityGlitchMemory
import com.randomperson22.piggymodtest.entities.grandmother.EntityGrandmother
import com.randomperson22.piggymodtest.entities.kitty.EntityKitty
import com.randomperson22.piggymodtest.entities.kona.EntityKona
import com.randomperson22.piggymodtest.entities.memory.EntityMemory
import com.randomperson22.piggymodtest.entities.mimi.EntityMimi
import com.randomperson22.piggymodtest.entities.mother.EntityMother
import com.randomperson22.piggymodtest.entities.mr_p.EntityMrP
import com.randomperson22.piggymodtest.entities.mr_stitchy.EntityMrStitchy
import com.randomperson22.piggymodtest.entities.owell.EntityOwell
import com.randomperson22.piggymodtest.entities.pandy.EntityPandy
import com.randomperson22.piggymodtest.entities.piggy.EntityPiggy
import com.randomperson22.piggymodtest.entities.piggyexe.EntityPiggyExe
import com.randomperson22.piggymodtest.entities.reindessa.EntityReindessa
import com.randomperson22.piggymodtest.entities.robby.EntityRobby
import com.randomperson22.piggymodtest.entities.sentinels.*
import com.randomperson22.piggymodtest.entities.sheepy.EntitySheepy
import com.randomperson22.piggymodtest.entities.teacher.EntityTeacher
import com.randomperson22.piggymodtest.entities.umbra.EntityUmbra
import com.randomperson22.piggymodtest.entities.zizzy.EntityZizzy
import com.randomperson22.piggymodtest.entities.zompiggy.EntityZompiggy
import com.randomperson22.piggymodtest.npc.doggy.EntityDoggyNpc
import com.randomperson22.piggymodtest.npc.georgie.EntityGeorgieNpc
import com.randomperson22.piggymodtest.npc.pony.EntityPonyNpc
import com.randomperson22.piggymodtest.npc.zizzy.EntityZizzyNpc
import net.minecraft.entity.Entity
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.EntityRegistry

object ModEntities {
    private var id = 0

    fun register() {
        registerEntity("piggy", EntityPiggy::class.java, 0xd8554c, 0xff8097)
        registerEntity("georgie", EntityGeorgie::class.java, 0x7fa9ca, 0xd8554c)
        registerEntity("mother", EntityMother::class.java, 0xdb824f, 0xff8097)
        registerEntity("father", EntityFather::class.java, 0x5cab8d, 0xff8097)
        registerEntity("grandmother", EntityGrandmother::class.java, 0xa65b9d, 0xff9bbb)
        registerEntity("sheepy", EntitySheepy::class.java, 0xcc92bc, 0xe7e7ec)
        registerEntity("kona", EntityKona::class.java, 0xff0000, 0x20ff00)
        registerEntity("owell", EntityOwell::class.java, 0x5a1771, 0xb9972d)
        registerEntity("teacher", EntityTeacher::class.java, 0x528852, 0x737376)
        registerEntity("pandy", EntityPandy::class.java, 0x59573a, 0x5c5c5e)
        registerEntity("memory", EntityMemory::class.java, 0x3a332b, 0xaba29c)
        registerEntity("kitty", EntityKitty::class.java, 0x326250, 0x855800)
        registerEntity("mimi", EntityMimi::class.java, 0xFFFFFF, 0x00FF00)
        registerEntity("doggy", EntityDoggy::class.java, 0x5f0057, 0x502906)
        registerEntity("dinopiggy", EntityDinopiggy::class.java, 0x502906, 0x071919)
        registerEntity("umbra", EntityUmbra::class.java, 0xFFFFFF, 0x000000)
        registerEntity("zizzy", EntityZizzy::class.java, 0xFAFAFA, 0xFDFDFD)
        registerEntity("zizzynpc", EntityZizzyNpc::class.java, 0xFAFAFA, 0xFDFDFD)
        registerEntity("zompiggy", EntityZompiggy::class.java, 0xAAAAAA, 0xFBFBFB)
        registerEntity("sentinel_red", EntitySentinelRed::class.java, 0x000000, 0xFF0000)
        registerEntity("sentinel_green", EntitySentinelGreen::class.java, 0x000000, 0x00FF00)
        registerEntity("sentinel_light_blue", EntitySentinelLightBlue::class.java, 0x000000, 0x00BFFF)
        registerEntity("sentinel_pink", EntitySentinelPink::class.java, 0x000000, 0xFF69B4)
        registerEntity("sentinel_shadow", EntitySentinelShadow::class.java, 0x000000, 0x555555)
        registerEntity("sentinel_white", EntitySentinelWhite::class.java, 0x000000, 0xAAAAAA)
        registerEntity("mr_stitchy", EntityMrStitchy::class.java, 0x000000, 0xFdcFdc)
        registerEntity("robby", EntityRobby::class.java, 0xFFFFFF, 0xeFFFFFF)
        registerEntity("duocara", EntityDuocara::class.java, 0x00000FF, 0xFF0000)
        registerEntity("mr_p", EntityMrP::class.java, 0x0000000, 0x000000)
        registerEntity("reindessa", EntityReindessa::class.java, 0x00FF000, 0xF0000a)
        registerEntity("billy", EntityBilly::class.java, 0x00ae00e, 0xe00F06)
        registerEntity("bela", EntityBela::class.java, 0x00FEA0, 0xF00E0a)
        registerEntity("frostiggy", EntityFrostiggy::class.java, 0x00FFFA, 0xeFFFA0e)
        registerEntity("glitch_memory", EntityGlitchMemory::class.java, 0x0000Fe, 0xe676758)
        registerEntity("distorted_penny", EntityDistortedPenny::class.java, 0xa45245, 0xf465426)
        registerEntity("piggyexe", EntityPiggyExe::class.java, 0x5324543, 0x34543543)
        registerEntity("georgienpc", EntityGeorgieNpc::class.java, 0x24525134, 0x8653143)
        registerEntity("doggynpc", EntityDoggyNpc::class.java, 0x8365432, 0x8763255)
        registerEntity("ponynpc", EntityPonyNpc::class.java, 0x6243651, 0x6532532)
        registerEntity("badgy", EntityBadgy::class.java, 0x325435, 0x98743)
    }

    private fun <T : Entity> registerEntity(name: String, clazz: Class<T>, eggPrimary: Int, eggSecondary: Int) {
        EntityRegistry.registerModEntity(
            ResourceLocation(PiggyModTest.MODID, name),
            clazz,
            name,
            id++,
            instance,
            64, 1, true,
            eggPrimary, eggSecondary
        )
    }

    private fun <T : Entity> registerEntityNoEgg(name: String, clazz: Class<T>) {
        EntityRegistry.registerModEntity(
            ResourceLocation(PiggyModTest.MODID, name),
            clazz,
            name,
            id++,
            instance,
            64, 1, true
        )
    }
}