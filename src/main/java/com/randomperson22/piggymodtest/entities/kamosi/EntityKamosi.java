package com.randomperson22.piggymodtest.entities.kamosi;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.init.ModSounds;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityKamosi extends EntityPiggyBase {
    @Override
    public SoundEvent getJumpscareSound() {
        return ModSounds.ENTITY_KAMOSI_JUMPSCARE;
    }

    public EntityKamosi(World worldIn) {
        super(worldIn);
    }
}
