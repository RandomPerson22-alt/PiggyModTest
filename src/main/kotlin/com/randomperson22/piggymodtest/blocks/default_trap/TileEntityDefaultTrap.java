package com.randomperson22.piggymodtest.blocks.default_trap;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;

public class TileEntityDefaultTrap extends TileEntity implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);
    private boolean isTriggered = false;
    private boolean hasPlayedStepTrigger = false;
    private int animationTimer = 0;

    public void tick() {
        if (world == null || world.isRemote) return;

        // Check for entities stepping on the trap
        if (!isTriggered) {
            List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, 
                new AxisAlignedBB(pos).grow(0.1, 0.1, 0.1));
            
            if (!entities.isEmpty()) {
                isTriggered = true;
                hasPlayedStepTrigger = true;
                // You might want to send a packet to client to play animation
            }
        }

        // Handle animation sequence
        if (isTriggered) {
            animationTimer++;
            
            // After step trigger animation would be done, play triggered animation
            // Adjust the timer values based on your animation lengths
            if (animationTimer > 20) { // Assuming steptrigger animation is ~1 second
                // Play triggered animation (you'd handle this in animation controller)
            }

            }
        }
    @SuppressWarnings("deprecation")
	private <E extends TileEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (isTriggered) {
            if (hasPlayedStepTrigger && animationTimer > 20) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("triggered", false));
            } else if (hasPlayedStepTrigger) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("steptrigger", false));
            }
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    // Optional: Reset method if you want the trap to be reusable
    public void resetTrap() {
        isTriggered = false;
        hasPlayedStepTrigger = false;
        animationTimer = 0;
    }
}