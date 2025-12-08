package com.randomperson22.piggymodtest.entities.badgy;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.init.ModSounds;

import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityBadgy extends EntityPiggyBase {
    public EntityBadgy(World worldIn) {
        super(worldIn);
    }

    @Override
    public SoundEvent getJumpscareSound() {
    	return ModSounds.ENTITY_BADGY_JUMPSCARE;
    }
}