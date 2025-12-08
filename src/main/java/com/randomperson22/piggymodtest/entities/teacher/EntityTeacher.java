package com.randomperson22.piggymodtest.entities.teacher;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.init.ModSounds;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityTeacher extends EntityPiggyBase {
    public EntityTeacher(World worldIn) {
        super(worldIn);
    }

    @Override
    public SoundEvent getJumpscareSound() {
        return ModSounds.ENTITY_TEACHER_JUMPSCARE;
    }
}