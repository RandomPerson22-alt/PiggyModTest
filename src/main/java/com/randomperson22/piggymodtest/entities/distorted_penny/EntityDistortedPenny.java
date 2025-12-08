package com.randomperson22.piggymodtest.entities.distorted_penny;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.init.ModSounds;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityDistortedPenny extends EntityPiggyBase {
    public EntityDistortedPenny(World worldIn) {
        super(worldIn);
    }

    @Override
    public SoundEvent getJumpscareSound() {
        return ModSounds.ENTITY_DISTORTEDPENNY_JUMPSCARE;
    }
}