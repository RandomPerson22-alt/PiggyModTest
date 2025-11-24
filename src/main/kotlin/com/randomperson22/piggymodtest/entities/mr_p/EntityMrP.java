package com.randomperson22.piggymodtest.entities.mr_p;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.init.ModSounds;

public class EntityMrP extends EntityPiggyBase {
    private int decisionCooldown = 0; // ticks until next decision
    private BlockPos targetPos = null;
    private int pathingTimeout = 0; // ticks trying to reach target
    public EntityMrP(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {
        // No normal AI goals — we’ll handle custom logic in onLivingUpdate()
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!this.world.isRemote) {
            // Decision logic
            if (decisionCooldown > 0) {
                decisionCooldown--;
            } else {
                decisionCooldown = 85;
                if (this.rand.nextInt(2) == 1) {
                    List<EntityPlayer> players = this.world.getEntitiesWithinAABB(
                            EntityPlayer.class,
                            this.getEntityBoundingBox().grow(100)
                    );

                    EntityPlayer nearest = null;
                    double closest = Double.MAX_VALUE;

                    for (EntityPlayer p : players) {
                        if (p.capabilities.isCreativeMode) continue;
                        double dist = this.getDistance(p);
                        if (dist < closest) {
                            closest = dist;
                            nearest = p;
                        }
                    }

                    if (nearest != null) {
                        targetPos = nearest.getPosition();
                        pathingTimeout = 0; // reset timeout
                    }
                }
            }

            // Move toward target
            if (targetPos != null) {
                double speed = this.getDistanceSqToCenter(targetPos) > 64 ? 1.5D : 1.0D;
                this.getNavigator().tryMoveToXYZ(targetPos.getX(), targetPos.getY(), targetPos.getZ(), speed);

                pathingTimeout++;
                // If stuck for more than 5 seconds (100 ticks), reset target
                if (pathingTimeout > 100) {
                    this.getNavigator().clearPath();
                    targetPos = null;
                    pathingTimeout = 0;
                }

                if (this.getDistanceSqToCenter(targetPos) < 0.1D) {
                    this.getNavigator().clearPath();
                    targetPos = null;
                    pathingTimeout = 0;
                }
            }

            // Collision-based attack
            List<EntityPlayer> collided = this.world.getEntitiesWithinAABB(
                    EntityPlayer.class,
                    this.getEntityBoundingBox()
            );
            for (EntityPlayer player : collided) {
                if (!player.capabilities.isCreativeMode) {
                    this.attackEntityAsMob(player);
                }
            }
        }
    }
    
    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    protected SoundEvent getJumpscareSound() {
        return ModSounds.ENTITY_MRP_JUMPSCARE;
    }
}