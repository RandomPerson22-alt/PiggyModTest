package com.randomperson22.piggymodtest.entities.dinopiggy;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.init.ModSounds;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityDinopiggy extends EntityPiggyBase {
	public static final int JUMPSCARE_DURATION = 27;
	
    public EntityDinopiggy(World worldIn) {
        super(worldIn);
    }

    @Override
    public SoundEvent getJumpscareSound() {
        return ModSounds.ENTITY_DINOPIGGY_JUMPSCARE;
    }
}