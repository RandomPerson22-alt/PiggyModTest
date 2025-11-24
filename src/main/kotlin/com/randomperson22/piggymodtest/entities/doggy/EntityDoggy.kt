package com.randomperson22.piggymodtest.entities.doggy

import com.randomperson22.piggymodtest.entities.EntityPiggyBase
import com.randomperson22.piggymodtest.init.ModSounds
import net.minecraft.util.SoundEvent
import net.minecraft.world.World

class EntityDoggy(worldIn: World) : EntityPiggyBase(worldIn) {
    override val jumpscareSound: SoundEvent
        get() = ModSounds.ENTITY_DOGGY_JUMPSCARE

    override val priority: Int
        get() = 1

    companion object {
        const val JUMPSCARE_DURATION: Int = 31
    }
}