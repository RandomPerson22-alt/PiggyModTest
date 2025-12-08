package com.randomperson22.piggymodtest.entities.mimi;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.init.ModSounds;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityMimi extends EntityPiggyBase {
	public static final int JUMPSCARE_DURATION = 35;
	
    public EntityMimi(World worldIn) {
        super(worldIn);
    }

    @Override
    public SoundEvent getJumpscareSound() {
        return ModSounds.ENTITY_MIMI_JUMPSCARE;
    }
}