package com.randomperson22.piggymodtest.entities.frostiggy;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.init.ModSounds;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityFrostiggy extends EntityPiggyBase {
    public EntityFrostiggy(World worldIn) {
        super(worldIn);
    }

    @Override
    public SoundEvent getJumpscareSound() {
        return ModSounds.ENTITY_FROSTIGGY_JUMPSCARE;
    }
}