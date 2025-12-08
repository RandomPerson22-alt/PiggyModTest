package com.randomperson22.piggymodtest.blocks.door;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class TileEntityDoor extends TileEntity implements IAnimatable, ITickable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private boolean open = false;
    private boolean animationPlayed = false;

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @SuppressWarnings("deprecation")
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationController<?> controller = event.getController();
        
        if (open && !animationPlayed) {
            // Play the open animation once
            controller.setAnimation(new AnimationBuilder().addAnimation("open", false));
            animationPlayed = true;
            return PlayState.CONTINUE;
        }
        
        // Continue to hold the last frame
        return PlayState.CONTINUE;
    }

    public void setOpen(boolean open) {
        if (this.open != open) {
            this.open = open;
            if (!open) {
                // Reset animation state when closed (without playing animation)
                animationPlayed = false;
            }
            markDirty();
            
            if (world != null) {
                world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
            }
        }
    }

    public boolean isOpen() {
        return open;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void update() {
        // Optional update logic
    }
}