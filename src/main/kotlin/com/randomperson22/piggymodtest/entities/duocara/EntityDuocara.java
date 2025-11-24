package com.randomperson22.piggymodtest.entities.duocara;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.init.ModSounds;

import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityDuocara extends EntityPiggyBase {
    public EntityDuocara(World worldIn) {
        super(worldIn);
    }

    @Override
    public SoundEvent getJumpscareSound() {
    	return ModSounds.ENTITY_DUOCARA_JUMPSCARE;
    }
}