package com.randomperson22.piggymodtest.entities.kitty;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.init.ModSounds;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityKitty extends EntityPiggyBase {
    public EntityKitty(World worldIn) {
        super(worldIn);
    }

    @Override
    public SoundEvent getJumpscareSound() {
        return ModSounds.ENTITY_KITTY_JUMPSCARE;
    }
}