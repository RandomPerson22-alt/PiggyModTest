package com.randomperson22.piggymodtest.entities.georgie;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.init.ModSounds;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityGeorgie extends EntityPiggyBase {
    public EntityGeorgie(World worldIn) {
        super(worldIn);
    }

    @Override
    public SoundEvent getJumpscareSound() {
        return ModSounds.ENTITY_PIGGY_JUMPSCARE;
    }
}