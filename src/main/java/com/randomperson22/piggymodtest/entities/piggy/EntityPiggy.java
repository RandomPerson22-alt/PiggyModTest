package com.randomperson22.piggymodtest.entities.piggy;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.init.ModSounds;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityPiggy extends EntityPiggyBase {
    @Override
    public SoundEvent getJumpscareSound() {
        return ModSounds.ENTITY_PIGGY_JUMPSCARE;
    }

    public EntityPiggy(World worldIn) {
        super(worldIn);
    }
}