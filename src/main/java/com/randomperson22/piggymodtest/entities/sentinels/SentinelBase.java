package com.randomperson22.piggymodtest.entities.sentinels;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;

import com.randomperson22.piggymodtest.init.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class SentinelBase extends EntityPiggyBase {
    public SentinelBase(World worldIn) {
        super(worldIn);
    }

	 @Override
	 public void updatePassenger(Entity passenger) {
	     if (this.isPassenger(passenger)) {
	         // Offsets relative to Piggy's rotation
	         float xOffset = 0.0F;      // Side-to-side (0 = centered)
	         float yOffset = 0.0F;      // Height (1.2 blocks above Piggy)
	         float zOffset = 1.0F;      // 1.5 blocks IN FRONT of Piggy

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
    public SoundEvent getJumpscareSound() {
        return ModSounds.ENTITY_SENTINEL_JUMPSCARE;
    }
}