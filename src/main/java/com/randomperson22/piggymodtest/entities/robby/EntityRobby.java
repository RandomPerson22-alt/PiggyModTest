package com.randomperson22.piggymodtest.entities.robby;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.init.ModSounds;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityRobby extends EntityPiggyBase {
	public static final int JUMPSCARE_DURATION = 35;
	
    public EntityRobby(World worldIn) {
        super(worldIn);
    }

    @Override
    public SoundEvent getJumpscareSound() {
        return ModSounds.ENTITY_ROBBY_JUMPSCARE;
    }
}