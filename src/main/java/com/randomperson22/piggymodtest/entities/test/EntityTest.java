package com.randomperson22.piggymodtest.entities.test;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.randomperson22.piggymodtest.events.Jumpscare_Handler;
import com.randomperson22.piggymodtest.interfaces.IBot;
import com.randomperson22.piggymodtest.interfaces.ICustomEyeHeight;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityTest extends EntityMob implements IAnimatable, IBot, ICustomEyeHeight {
 private static final DataParameter<Boolean> IS_MOVING = EntityDataManager.createKey(EntityTest.class, DataSerializers.BOOLEAN);
 private static final DataParameter<Boolean> IS_JUMPSCARING = EntityDataManager.createKey(EntityTest.class, DataSerializers.BOOLEAN);
 private static final DataParameter<String> ANIMATION_STATE = EntityDataManager.createKey(EntityTest.class, DataSerializers.STRING);
 private static final DataParameter<Boolean> USE_ALT_ANIMS = EntityDataManager.createKey(EntityTest.class, DataSerializers.BOOLEAN);

 protected EntityPlayer pendingAttackTarget = null;
 protected int attackDelayTicks = -1;
 public int jumpscareCooldown = 0;
 protected int animationTick = 0;
 protected static final int JUMPSCARE_DURATION = 120;
 private static final ConcurrentHashMap<UUID, UUID> ACTIVE_ATTACKS = new ConcurrentHashMap<UUID, UUID>();

 private final AnimationFactory factory = new AnimationFactory(this);
 private final AnimationController<EntityTest> mainController = new AnimationController<>(this, "mainController", 5, this::predicate);
 private final AnimationController<EntityTest> jumpscareController = new AnimationController<>(this, "jumpscareController", 0, this::jumpscarePredicate);

 public EntityTest(World worldIn) {
     super(worldIn);
     this.enablePersistence();
 }

 @Override
 protected void entityInit() {
     super.entityInit();
     this.dataManager.register(IS_MOVING, false);
     this.dataManager.register(IS_JUMPSCARING, false);
     this.dataManager.register(ANIMATION_STATE, "idle1");
     this.dataManager.register(USE_ALT_ANIMS, false); // false = animation set 1 true = set 2
 }

 public void setCurrentAnimation(String animationName) {
     this.dataManager.set(ANIMATION_STATE, animationName);
     this.animationTick = 0;
 }

 public String getCurrentAnimation() {
     return this.dataManager.get(ANIMATION_STATE);
 }

 public int getAnimationTick() {
     return this.animationTick;
 }
 
 protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
     if (this.isJumpscaring()) {
         return PlayState.STOP;
     }

     String anim = this.getCurrentAnimation();
     boolean alt = isUsingAltAnims();
     if (("walk1".equals(anim) && !alt) || ("walk2".equals(anim) && alt)) {
         event.getController().setAnimation(new AnimationBuilder().addAnimation(alt ? "walk2" : "walk1").addRepeatingAnimation(alt ? "walk2" : "walk1", -1));
     } else {
         event.getController().setAnimation(new AnimationBuilder().addAnimation(alt ? "idle2" : "idle1").addRepeatingAnimation(alt ? "idle2" : "idle1", -1));
     }


     return PlayState.CONTINUE;
 }

 protected <E extends IAnimatable> PlayState jumpscarePredicate(AnimationEvent<E> event) {
	 if (this.isJumpscaring()) {
		    event.getController().setAnimation(new AnimationBuilder().addAnimation(isUsingAltAnims() ? "jumpscare2" : "jumpscare1"));
		    return PlayState.CONTINUE;
		}

     return PlayState.STOP;
 }

 @Override
 public void registerControllers(AnimationData data) {
     data.addAnimationController(mainController);
     data.addAnimationController(jumpscareController);
 }

 @Override
 public AnimationFactory getFactory() {
     return factory;
 }

 @Override
 protected void applyEntityAttributes() {
     super.applyEntityAttributes();
     this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28D);
     this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.0D);
     this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.0D);
     this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
     this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
 }

 @Override
 protected void initEntityAI() {
     this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
     this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
     this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));

     this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, false));
 }

 public void setJumpscaring(boolean jumpscaring) {
     this.dataManager.set(IS_JUMPSCARING, jumpscaring);
     if (jumpscaring) {
         this.jumpscareCooldown = 0;
     }
 }
 
 @Override
 protected boolean canDespawn() {
     return false;
 }
 
 @Override
 public void onLivingUpdate() {
     super.onLivingUpdate();

     EntityLivingBase target = this.getAttackTarget();
     if (target != null && this.canEntityBeSeen(target)) {
         // Face the player smoothly
         this.faceEntity(target, 30.0F, 30.0F);
         
         // Check if target is already claimed (unless it's ours)
         if (ACTIVE_ATTACKS.containsKey(target.getUniqueID()) && 
             !ACTIVE_ATTACKS.get(target.getUniqueID()).equals(this.getUniqueID())) {
             this.setAttackTarget(null); // Find another target
         }
     }
     
     if (!this.world.isRemote) {
    	    this.animationTick++;

    	    boolean moving = Math.abs(this.motionX) > 0.001 || Math.abs(this.motionZ) > 0.001;
    	    this.dataManager.set(IS_MOVING, moving);

    	    if (this.isJumpscaring()) {
    	        // Always finish the jumpscare after the set duration
    	        if (this.animationTick >= JUMPSCARE_DURATION) {
    	            resetAnimationState();
    	        }
    	    }

    	    if (jumpscareCooldown > 0) {
    	        jumpscareCooldown--;
    	    }

    	    if (attackDelayTicks > 0) {
    	        attackDelayTicks--;
    	    } else if (attackDelayTicks == 0 && pendingAttackTarget != null) {
    	        // Apply damage
    	        pendingAttackTarget.attackEntityFrom(DamageSource.causeMobDamage(this), 100.0F);

    	        // RELEASE CLAIM AFTER DAMAGE
    	        ACTIVE_ATTACKS.remove(pendingAttackTarget.getUniqueID());
    	        pendingAttackTarget = null;

    	        attackDelayTicks = -1;
    	    }


    	    boolean alt = isUsingAltAnims();
    	    if (!isJumpscaring()) {
    	        if (isMoving()) {
    	            setCurrentAnimation(alt ? "walk2" : "walk1");
    	        } else {
    	            setCurrentAnimation(alt ? "idle2" : "idle1");
    	        }
    	    }

    	}
 }

 protected void resetAnimationState() {
	    System.out.println("[DEBUG] Resetting animation state for Test");
	    this.setCurrentAnimation(isUsingAltAnims() ? "idle2" : "idle1");
		    this.animationTick = 0;
		    this.setJumpscaring(false);
		    this.setNoAI(false);
		     // Release our attack claim if we had one
		     if (pendingAttackTarget != null) {
		         ACTIVE_ATTACKS.remove(pendingAttackTarget.getUniqueID());
		         pendingAttackTarget = null;
		     }
	        // Toggle between true and false on each attack
	        this.dataManager.set(USE_ALT_ANIMS, !this.isUsingAltAnims()); 
	 }

 @Override
public boolean attackEntityAsMob(Entity entityIn) {
     if (entityIn instanceof EntityPlayer && !this.isJumpscaring() && jumpscareCooldown <= 0) {
         EntityPlayer player = (EntityPlayer) entityIn;
         
         // Check if target is already being attacked (using jumpscareCooldown as cooldown tracker)
         if (ACTIVE_ATTACKS.containsKey(player.getUniqueID())) {
             UUID currentAttacker = ACTIVE_ATTACKS.get(player.getUniqueID());
             if (!currentAttacker.equals(this.getUniqueID())) {
                 return false; // Someone else is attacking
             }
         }
         
         // Claim this target
         ACTIVE_ATTACKS.put(player.getUniqueID(), this.getUniqueID());
         
         this.pendingAttackTarget = player;

         // Force player to mount Piggy
         player.startRiding(this);
         if (entityIn instanceof EntityPlayer) {
        	 Jumpscare_Handler.triggerJumpscareAuto(this, player);
        	}

         this.setNoAI(true);
         this.pendingAttackTarget = player;
         this.attackDelayTicks = 103;
         this.animationTick = 0;
         this.setJumpscaring(true);
         this.jumpscareCooldown = 103;

         return true;
     }
     return false;
 }
 
 @Override
 public void updatePassenger(Entity passenger) {
     if (this.isPassenger(passenger)) {
         // Offsets relative to Piggy's rotation
         float xOffset = 0.0F;      // Side-to-side (0 = centered)
         float yOffset = 0.5F;      // Height (1.2 blocks above Piggy)
         float zOffset = 2.5F;      // 1.5 blocks IN FRONT of Piggy

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
public void removePassenger(Entity passenger) {
    super.removePassenger(passenger);
    if (this.isJumpscaring() && passenger instanceof EntityPlayer) {
        System.out.println("[DEBUG] Player dismounted during jumpscare - resetting");
    }
}

@Override
public void setDead() {
    if (pendingAttackTarget != null) {
        ACTIVE_ATTACKS.remove(pendingAttackTarget.getUniqueID());
    }
    super.setDead();
}

 public boolean isUsingAltAnims() {
	    return this.dataManager.get(USE_ALT_ANIMS);
	}

	public void setAltAnimations(boolean useAlt) {
	    this.dataManager.set(USE_ALT_ANIMS, useAlt);
	}
 
 public boolean isMoving() {
     return this.dataManager.get(IS_MOVING);
 }

 public boolean isJumpscaring() {
     return this.dataManager.get(IS_JUMPSCARING);
 }


 @Override
 public int getPriority() {
     return 2;
 }
 
 @Override
 public float getCustomEyeHeight() {
     return 2.0F; // 41 pixels ÷ 16 = 2.5625 blocks
 }

@Override
public void getStunned(int ticks) {
	
}

    @Override
    public boolean canBeTargeted() {
        return false;
    }
}