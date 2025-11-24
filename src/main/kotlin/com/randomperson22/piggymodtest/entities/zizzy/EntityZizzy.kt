package com.randomperson22.piggymodtest.entities.zizzy

import com.randomperson22.piggymodtest.entities.EntityPiggyBase
import com.randomperson22.piggymodtest.init.ModSounds
import net.minecraft.util.SoundEvent
import net.minecraft.world.World

class EntityZizzy(worldIn: World) : EntityPiggyBase(worldIn) {
    override val jumpscareSound: SoundEvent
        get() = ModSounds.ENTITY_ZIZZY_JUMPSCARE

    companion object {
        const val JUMPSCARE_DURATION: Int = 35
    }
}