package com.randomperson22.piggymodtest.entities.robby

import com.randomperson22.piggymodtest.entities.EntityPiggyBase
import com.randomperson22.piggymodtest.init.ModSounds
import net.minecraft.util.SoundEvent
import net.minecraft.world.World

class EntityRobby(worldIn: World) : EntityPiggyBase(worldIn) {
    override val jumpscareSound: SoundEvent
        get() = ModSounds.ENTITY_ROBBY_JUMPSCARE

    companion object {
        const val JUMPSCARE_DURATION: Int = 35
    }
}