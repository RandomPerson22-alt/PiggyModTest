package com.randomperson22.piggymodtest.entities.mr_stitchy;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.init.ModSounds;
import com.randomperson22.piggymodtest.interfaces.ICustomEyeHeight;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityMrStitchy extends EntityPiggyBase implements ICustomEyeHeight {
	public static final int JUMPSCARE_DURATION = 40;
	
    public EntityMrStitchy(World worldIn) {
        super(worldIn);
    }

	 @Override
	 protected void applyEntityAttributes() {
	     super.applyEntityAttributes();
	     this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
	 }

	 @Override
	 public void updatePassenger(Entity passenger) {
	     if (this.isPassenger(passenger)) {
	         // Offsets relative to Piggy's rotation
	         float xOffset = 0.0F;      // Side-to-side (0 = centered)
	         float yOffset = 0.75F;      // Height (1.2 blocks above Piggy)
	         float zOffset = 1.7F;      // 1.5 blocks IN FRONT of Piggy

	         // Convert Piggy's rotation to radians
	         float yawRad = this.rotationYaw * (float) (Math.PI / 180.0F);

	         // Calculate rotated offsets (relative to Piggy's facing direction)
	         double rotatedX = -Math.sin(yawRad) * zOffset;
	         double rotatedZ = Math.cos(yawRad) * zOffset;

	         // Set player position
	         passenger.setPosition(
	             this.posX + rotatedX + xOffset,
	             this.posY + yOffset,
	             this.posZ + rotatedZ
	         );
	     }
	 }
	 
 @Override
 public float getCustomEyeHeight() {
     return 2.5625F; // 41 pixels ÷ 16 = 2.5625 blocks
 }

    @Override
    public SoundEvent getJumpscareSound() {
        return ModSounds.ENTITY_MR_STITCHY_JUMPSCARE;
    }
}