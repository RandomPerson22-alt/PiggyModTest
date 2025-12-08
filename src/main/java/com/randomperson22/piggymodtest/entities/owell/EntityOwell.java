package com.randomperson22.piggymodtest.entities.owell;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.init.ModSounds;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityOwell extends EntityPiggyBase {
	public static final int JUMPSCARE_DURATION = 25;
	
    public EntityOwell(World worldIn) {
        super(worldIn);
    }

    @Override
    public SoundEvent getJumpscareSound() {
        return ModSounds.ENTITY_OWELL_JUMPSCARE;
    }
}