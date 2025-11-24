package com.randomperson22.piggymodtest.projectiles;

/*import com.randomperson22.piggymodtest.entities.mr_p.EntityMrP;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class YellowOrb extends Entity implements IAnimatable, IProjectile {
    private EntityLivingBase target;
    private double speed = 0.2;
    protected final AnimationFactory factory = new AnimationFactory(this);
    
    public YellowOrb(World worldIn) {
        super(worldIn);
        this.setSize(0.8F, 0.8F);
    }

    public YellowOrb(World worldIn, EntityMrP shooter, EntityLivingBase target) {
        super(worldIn);
        this.target = target;
        this.setSize(0.8F, 0.8F);
        this.setPosition(shooter.posX, shooter.posY + 1.5, shooter.posZ);
    }

    
    @Override
    protected void entityInit() {
        // DataManager fields if needed
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (world.isRemote) return;

        if (target == null || target.isDead) {
            this.setDead(); // remove orb if no valid target
            return;
        }

        // Homing logic
        double dx = target.posX - this.posX;
        double dy = target.posY + target.getEyeHeight() - this.posY;
        double dz = target.posZ - this.posZ;
        double distance = Math.sqrt(dx*dx + dy*dy + dz*dz);

        // normalize and scale by speed
        this.motionX = dx / distance * speed;
        this.motionY = dy / distance * speed;
        this.motionZ = dz / distance * speed;

        this.posX += motionX;
        this.posY += motionY;
        this.posZ += motionZ;
    }

    @Override
    protected void readEntityFromNBT(net.minecraft.nbt.NBTTagCompound compound) {
        // Save/load if needed
    }

    @Override
    protected void writeEntityToNBT(net.minecraft.nbt.NBTTagCompound compound) {
        // Save/load if needed
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        // optional: set initial motion like arrows
        this.motionX = x * velocity;
        this.motionY = y * velocity;
        this.motionZ = z * velocity;
    }

	@Override
	public void registerControllers(AnimationData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AnimationFactory getFactory() {
		// TODO Auto-generated method stub
		return factory;
	}
}*/