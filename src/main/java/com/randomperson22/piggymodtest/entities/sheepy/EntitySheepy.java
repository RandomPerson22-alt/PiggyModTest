package com.randomperson22.piggymodtest.entities.sheepy;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.init.ModSounds;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntitySheepy extends EntityPiggyBase {
    public EntitySheepy(World worldIn) {
        super(worldIn);
    }

    @Override
    public SoundEvent getJumpscareSound() {
        return ModSounds.ENTITY_SHEEPY_JUMPSCARE;
    }
}