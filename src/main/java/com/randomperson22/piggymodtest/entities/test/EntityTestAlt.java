package com.randomperson22.piggymodtest.entities.test;

import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public class EntityTestAlt extends EntityTest {

    public EntityTestAlt(World worldIn) {
        super(worldIn);
    }

    @Override
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.isJumpscaring()) {
            return PlayState.STOP;
        }

        // Determine animation based on movement
        String anim;
        if (this.isMoving()) {
            anim = "walk2";
        } else {
            anim = "idle2";
        }

        this.setCurrentAnimation(anim); // update currentAnimation
        event.getController().setAnimation(new AnimationBuilder().addAnimation(anim).addRepeatingAnimation(anim, -1));

        return PlayState.CONTINUE;
    }


    @Override
    protected <E extends IAnimatable> PlayState jumpscarePredicate(AnimationEvent<E> event) {
        if (this.isJumpscaring()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("jumpscare2"));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }
    

    @Override
    protected void resetAnimationState() {
        System.out.println("[DEBUG] Resetting animation state for ALT Test");
        this.setCurrentAnimation("idle2");
        this.animationTick = 0;
        this.setJumpscaring(false);
        this.setNoAI(false);
    }
}