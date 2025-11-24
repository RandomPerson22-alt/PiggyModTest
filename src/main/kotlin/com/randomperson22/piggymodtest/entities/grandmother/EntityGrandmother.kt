package com.randomperson22.piggymodtest.entities.grandmother

import com.randomperson22.piggymodtest.entities.EntityPiggyBase
import com.randomperson22.piggymodtest.init.ModSounds
import net.minecraft.util.SoundEvent
import net.minecraft.world.World

class EntityGrandmother(worldIn: World) : EntityPiggyBase(worldIn) {
    override val priority: Int
        get() = 1

    override val jumpscareSound: SoundEvent
        get() = ModSounds.ENTITY_GRANDMOTHER_JUMPSCARE
}