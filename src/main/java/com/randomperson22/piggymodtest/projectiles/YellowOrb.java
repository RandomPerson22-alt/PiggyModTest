package com.randomperson22.piggymodtest.projectiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class YellowOrb extends Entity implements IAnimatable {
    private double speed = 1.5;

    public YellowOrb(World worldIn) {
        super(worldIn);
        this.setSize(0.25F, 0.25F); // adjust size
    }

    public YellowOrb(World worldIn, EntityLivingBase shooter) {
        this(worldIn);
        this.setPosition(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ);
        this.motionX = -Math.sin(Math.toRadians(shooter.rotationYaw)) * Math.cos(Math.toRadians(shooter.rotationPitch)) * speed;
        this.motionY = -Math.sin(Math.toRadians(shooter.rotationPitch)) * speed;
        this.motionZ = Math.cos(Math.toRadians(shooter.rotationYaw)) * Math.cos(Math.toRadians(shooter.rotationPitch)) * speed;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.posX += motionX;
        this.posY += motionY;
        this.posZ += motionZ;

        // Simple collision detection
        if (!world.isRemote && world.getCollisionBoxes(this, this.getEntityBoundingBox()).size() > 0) {
            this.setDead();
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        // Add a dummy controller that always stops
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        // Do not play any animations
        return PlayState.STOP;
    }

    @Override
    protected void entityInit() {}

    @Override
    protected void readEntityFromNBT(net.minecraft.nbt.NBTTagCompound compound) {}

    @Override
    protected void writeEntityToNBT(net.minecraft.nbt.NBTTagCompound compound) { return; }


	@Override
	public AnimationFactory getFactory() {
		// TODO Auto-generated method stub
		return null;
	}
}
