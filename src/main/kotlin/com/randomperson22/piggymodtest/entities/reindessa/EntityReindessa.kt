package com.randomperson22.piggymodtest.entities.reindessa

import com.randomperson22.piggymodtest.entities.EntityPiggyBase
import com.randomperson22.piggymodtest.init.ModSounds
import net.minecraft.util.SoundEvent
import net.minecraft.world.World

class EntityReindessa(worldIn: World) : EntityPiggyBase(worldIn) {
    override val jumpscareSound: SoundEvent
        get() = ModSounds.ENTITY_DESSA_JUMPSCARE
}