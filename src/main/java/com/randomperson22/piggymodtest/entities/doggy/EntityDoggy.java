package com.randomperson22.piggymodtest.entities.doggy;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.init.ModSounds;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityDoggy extends EntityPiggyBase {
	public static final int JUMPSCARE_DURATION = 31;
	
    public EntityDoggy(World worldIn) {
        super(worldIn);
    }
    
    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public SoundEvent getJumpscareSound() {
        return ModSounds.ENTITY_DOGGY_JUMPSCARE;
    }
}