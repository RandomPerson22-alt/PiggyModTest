package com.randomperson22.piggymodtest.entities.test;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;

import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityTest extends EntityPiggyBase {
    public EntityTest(World worldIn) {
        super(worldIn);
    }

	@Override
	protected SoundEvent getJumpscareSound() {
		// TODO Auto-generated method stub
		return null;
	}
}