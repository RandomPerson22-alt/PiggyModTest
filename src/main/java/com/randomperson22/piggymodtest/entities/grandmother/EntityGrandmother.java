package com.randomperson22.piggymodtest.entities.grandmother;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.init.ModSounds;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityGrandmother extends EntityPiggyBase {
    public EntityGrandmother(World worldIn) {
        super(worldIn);
    }

 @Override
 public int getPriority() {
     return 1;
 }

    @Override
    public SoundEvent getJumpscareSound() {
        return ModSounds.ENTITY_GRANDMOTHER_JUMPSCARE;
    }
}