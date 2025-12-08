package com.randomperson22.piggymodtest.entities.glitch_memory;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.init.ModSounds;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityGlitchMemory extends EntityPiggyBase {
    public EntityGlitchMemory(World worldIn) {
        super(worldIn);
    }

    @Override
    public SoundEvent getJumpscareSound() {
        return ModSounds.ENTITY_MEMORY_JUMPSCARE;
    }
}