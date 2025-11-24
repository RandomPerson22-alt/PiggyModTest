package com.randomperson22.piggymodtest.entities.piggy

import com.randomperson22.piggymodtest.entities.EntityPiggyBase
import com.randomperson22.piggymodtest.init.ModSounds
import net.minecraft.util.SoundEvent
import net.minecraft.world.World

class EntityPiggy(worldIn: World) : EntityPiggyBase(worldIn) {
    override val jumpscareSound: SoundEvent
        get() = ModSounds.ENTITY_PIGGY_JUMPSCARE
}