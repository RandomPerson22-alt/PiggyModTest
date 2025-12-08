package com.randomperson22.piggymodtest.entities.reindessa;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.init.ModSounds;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityReindessa extends EntityPiggyBase {
    public EntityReindessa(World worldIn) {
        super(worldIn);
    }

    @Override
    public SoundEvent getJumpscareSound() {
        return ModSounds.ENTITY_DESSA_JUMPSCARE;
    }
}