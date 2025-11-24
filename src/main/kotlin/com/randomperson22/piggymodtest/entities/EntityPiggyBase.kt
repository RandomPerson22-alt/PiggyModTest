package com.randomperson22.piggymodtest.entities;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.randomperson22.piggymodtest.events.Jumpscare_Handler;
import com.randomperson22.piggymodtest.init.ModSounds;
import com.randomperson22.piggymodtest.interfaces.IBot;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public abstract class EntityPiggyBase extends EntityMob implements IAnimatable, IBot {
	 protected static final DataParameter<Boolean> IS_MOVING = EntityDataManager.createKey(EntityPiggyBase.class, DataSerializers.BOOLEAN);
	 protected static final DataParameter<Boolean> IS_JUMPSCARING = EntityDataManager.createKey(EntityPiggyBase.class, DataSerializers.BOOLEAN);
	 protected static final DataParameter<String> ANIMATION_STATE = EntityDataManager.createKey(EntityPiggyBase.class, DataSerializers.STRING);
	 private static final DataParameter<Boolean> IS_STUNNED = EntityDataManager.createKey(EntityPiggyBase.class, DataSerializers.BOOLEAN);
	 protected static final DataParameter<Boolean> IS_CLIMBING_LADDER = EntityDataManager.createKey(EntityPiggyBase.class, DataSerializers.BOOLEAN);
	 protected static final ConcurrentHashMap<UUID, UUID> ACTIVE_ATTACKS = new ConcurrentHashMap<UUID, UUID>();

	 protected EntityLivingBase pendingAttackTarget = null;
	 protected int attackDelayTicks = -1;  
	 protected int jumpscareCooldown = 0;
	 protected int animationTick = 0;
	 protected static final int JUMPSCARE_DURATION = 30;
	 protected int stunTicksRemaining = 0;
	 protected String stunAnimation = "stun1"; // default for most bots
	 protected String currentAnimation = "idle";
	 protected abstract SoundEvent getJumpscareSound();
	 protected boolean climbingLadder = false;

	 protected final AnimationFactory factory = new AnimationFactory(this);
	 protected final AnimationController<EntityPiggyBase> mainController = new AnimationController<>(this, "mainController", 0, this::predicate);

	 public EntityPiggyBase(World worldIn) {
	     super(worldIn);
	     this.enablePersistence();
	     ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
	     ((PathNavigateGround)this.getNavigator()).setCanSwim(true);
	 }
	 
	 @Override
	 protected PathNavigate createNavigator(World worldIn) {
	     PathNavigateGround nav = new PathNavigateGround(this, worldIn);
	     nav.setCanSwim(false); // optional
	     nav.setEnterDoors(true);
	     nav.setBreakDoors(true);
	     return nav;
	 }

	 @Override
	 protected void entityInit() {
	     super.entityInit();
	     this.dataManager.register(IS_MOVING, false);
	     this.dataManager.register(IS_JUMPSCARING, false);
	     this.dataManager.register(ANIMATION_STATE, "idle");
	     this.dataManager.register(IS_STUNNED, false);
	     this.dataManager.register(IS_CLIMBING_LADDER, false);

	 }
	 
	 protected boolean isOnClimbable() {
		    BlockPos posFeet = new BlockPos(this.posX, this.posY, this.posZ);
		    BlockPos posHead = new BlockPos(this.posX, this.posY + 1, this.posZ);

		    Block blockFeet = this.world.getBlockState(posFeet).getBlock();
		    Block blockHead = this.world.getBlockState(posHead).getBlock();

		    return blockFeet == Blocks.LADDER
		        || blockFeet == Blocks.VINE
		        || blockHead == Blocks.LADDER
		        || blockHead == Blocks.VINE;
		}
	 
	 protected boolean hasLadderAbove() {
		    BlockPos above = new BlockPos(this.posX, this.posY + 2.5, this.posZ); // 1.5 blocks above feet
		    Block blockAbove = this.world.getBlockState(above).getBlock();
		    return blockAbove == Blocks.LADDER || blockAbove == Blocks.VINE;
		}

	 public void setCurrentAnimation(String animationName) {
		    this.mainController.markNeedsReload(); // resets controller immediately
		    this.dataManager.set(ANIMATION_STATE, animationName);
		    this.animationTick = 0;
		}
		
	 protected void onStunned() {
	     this.setNoAI(true);
	 }
	 
	 protected void onStunEnd() {
		    this.setNoAI(false);
	 }
	 
	 protected String getCurrentAnimation() {
	     return this.dataManager.get(ANIMATION_STATE);
	 }

	 protected int getAnimationTick() {
	     return this.animationTick;
	 }
	 
	 protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
	     // 1. Stun first
	     if (this.stunTicksRemaining > 0) {
	    	 event.getController().setAnimation(
	    			    new AnimationBuilder().addAnimation(this.stunAnimation).addRepeatingAnimation(this.stunAnimation, -1)
	    			);
	    	 
	         return PlayState.CONTINUE;
	     }

	     // 2. Forced animations (like jumpscare)
	     String anim = this.getCurrentAnimation();
	     if ("jumpscare".equals(anim)) { 
	    	 
	    	 event.getController().setAnimation(new AnimationBuilder().addAnimation("jumpscare"));
	    	 
	         return PlayState.CONTINUE;
	     }

	  // 3. Any other forced animation (single-play)
	     if (anim != null && !anim.equals("idle") && !anim.equals("walk")) {
	         event.getController().setAnimation(
	             new AnimationBuilder().addAnimation(anim) // plays once
	         );
	         return PlayState.CONTINUE;
	     }

	     // 4. Walk / Idle as default
	     if (this.isMoving()) {
	    	    event.getController().setAnimation(
	    	        new AnimationBuilder().addAnimation("walk").addRepeatingAnimation("walk", -1)
	    	    );
	    	} else {
	    	    event.getController().setAnimation(
	    	        new AnimationBuilder().addAnimation("idle").addRepeatingAnimation("idle", -1)
	    	    );
	    	}

	     return PlayState.CONTINUE;
	 }

	 @Override
	 public void registerControllers(AnimationData data) {
	     data.addAnimationController(mainController);
	 }

	 @Override
	 public AnimationFactory getFactory() {
	     return factory;
	 }

	 @Override
	 protected void applyEntityAttributes() {
	     super.applyEntityAttributes();
	     this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28D);
	     this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(200.0D);
	     this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.0D);
	     this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
	     this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	 }
	 
	 @Override
	 protected void initEntityAI() {
	     this.tasks.addTask(0, new EntityAISwimming(this));
	     this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
	     this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 1.0D));
	     this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
	     this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
	     this.tasks.addTask(5, new EntityAILookIdle(this));
	     this.applyEntityAI();
	 }
	 
	    protected void applyEntityAI() {
		     this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, false));
		     this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityVillager.class, false));
		     this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
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
	 public void applyEntityCollision(Entity entityIn) {
	     super.applyEntityCollision(entityIn); // keep normal physics

	     if (this.isDead) return; // stop after death
	     if (entityIn instanceof EntityPlayer) return; // ignore players
	     if (!(entityIn instanceof EntityLivingBase)) return; // only living entities

	     // --- Get entity ID safely ---
	     ResourceLocation entityID = EntityList.getKey(entityIn);
	     if (entityID != null) {
	         String id = entityID.toString(); // e.g. "minecraft:zombie" or "piggymodtest:piggy"

	         // Skip your own mod's entities
	         if (id.startsWith("piggymodtest:")) return;

	         // Skip other mods' entities (only allow vanilla)
	         if (!id.startsWith("minecraft:")) return;
	     }

	     EntityLivingBase targetEntity = (EntityLivingBase) entityIn;

	     // Only attack if not already jumpscaring / cooldown
	     if (!this.isJumpscaring() && this.jumpscareCooldown <= 0) {
	         this.attackEntityAsMob(targetEntity); // full jumpscare attack
	     }
	 }
	 
	 @Override
	 public void onLivingUpdate() {
	     super.onLivingUpdate();
	     this.animationTick++;
	     
	     EntityLivingBase target = this.getAttackTarget();
	     if (target != null && this.canEntityBeSeen(target)) {
	         this.faceEntity(target, 30.0F, 30.0F);
	         
	         // Check if target is already claimed (unless it's ours)
	         if (ACTIVE_ATTACKS.containsKey(target.getUniqueID()) && 
	             !ACTIVE_ATTACKS.get(target.getUniqueID()).equals(this.getUniqueID())) {
	             this.setAttackTarget(null); // Find another target
	         }
	     }
	         if (this.stunTicksRemaining > 0) {
	             this.stunTicksRemaining--;
	             if (this.stunTicksRemaining <= 0) {
	                 this.dataManager.set(IS_STUNNED, false);
	                 this.onStunEnd();
	             }
	         }
	         
	      // --- LADDER CHECK ---
	         if (!this.world.isRemote) {
	             if (isOnClimbable()) {
	                 if (!this.climbingLadder) {
	                     this.setNoAI(true); // disable AI
	                     this.climbingLadder = true;
	                     this.dataManager.set(IS_CLIMBING_LADDER, true);
	                 }

	                 // --- Force piggy to ladder block ---
	                 BlockPos ladderPos = new BlockPos(this.posX, this.posY, this.posZ);
	                 Block block = this.world.getBlockState(ladderPos).getBlock();

	                 if (block == Blocks.LADDER || block == Blocks.VINE) {
	                     // Snap to center
	                     double snapX = ladderPos.getX() + 0.5;
	                     double snapY = ladderPos.getY(); // current height, we'll add motionY next
	                     double snapZ = ladderPos.getZ() + 0.5;
	                     this.setPosition(snapX, snapY, snapZ);

	                     // --- Face toward ladder ---
	                     double dx = snapX - this.posX;
	                     double dz = snapZ - this.posZ;
	                     float yaw = (float) (Math.toDegrees(Math.atan2(dz, dx)) - 90);
	                     this.rotationYaw = yaw;
	                     this.rotationYawHead = yaw;

	                     // --- Climb upward slowly ---
	                     double climbSpeed = 0.05; // adjust for slower/faster climb
	                     BlockPos above = ladderPos.up();
	                     Block blockAbove = this.world.getBlockState(above).getBlock();
	                     if (blockAbove == Blocks.LADDER || blockAbove == Blocks.VINE) {
	                         // Move upward if ladder above
	                         this.setPosition(this.posX, this.posY + climbSpeed, this.posZ);
	                     }
	                 }

	             } else {
	                 // Not on ladder → restore AI
	                 if (this.climbingLadder) {
	                     this.setNoAI(false);
	                     this.climbingLadder = false;
	                     this.dataManager.set(IS_CLIMBING_LADDER, false);
	                 }
	             }
	         }

	     // Original update logic
	     if (!this.world.isRemote) {
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
	         
	         if (this.attackDelayTicks > 0) {
	             this.attackDelayTicks--;
	         } else if (this.attackDelayTicks == 0 && this.pendingAttackTarget != null && 
	                   this.pendingAttackTarget.isEntityAlive()) {
	             this.pendingAttackTarget.attackEntityFrom(DamageSource.causeMobDamage(this), 300.0F);
	             this.attackDelayTicks = -1;
	         }
	         
	         if (!this.isJumpscaring()) {
	             setCurrentAnimation(this.isMoving() ? "walk" : "idle");
	         }
	     }
	 }

	 protected void resetAnimationState() {
	     this.setCurrentAnimation("idle");
	     this.animationTick = 0;
	     this.setJumpscaring(false);
	     this.setNoAI(false);
	     
	     // Release our attack claim if we had one
	     if (pendingAttackTarget != null) {
	         ACTIVE_ATTACKS.remove(pendingAttackTarget.getUniqueID());
	         pendingAttackTarget = null;
	     }
	 }

	    @Override
	    public boolean attackEntityAsMob(Entity entityIn) {
	        if (this.isJumpscaring() || jumpscareCooldown > 0) {
	            return false;
	        }

	        this.mainController.markNeedsReload();
	        this.setCurrentAnimation("jumpscare");

	        if (!(entityIn instanceof EntityLivingBase)) return false;
	        EntityLivingBase targetEntity = (EntityLivingBase) entityIn;

	        if (ACTIVE_ATTACKS.containsKey(targetEntity.getUniqueID())) {
	            UUID currentAttacker = ACTIVE_ATTACKS.get(targetEntity.getUniqueID());
	            if (!currentAttacker.equals(this.getUniqueID())) return false;
	        }
	        ACTIVE_ATTACKS.put(targetEntity.getUniqueID(), this.getUniqueID());

	        targetEntity.startRiding(this);

	        // Play the entity-specific jumpscare sound
	        SoundEvent soundEvent = this.getJumpscareSound();
	        this.world.playSound(
	            null,
	            this.posX, this.posY, this.posZ,
	            soundEvent,
	            SoundCategory.HOSTILE,
	            3F, 1F
	        );

	        // Always call handler for players (UI / camera / packet)
	        Jumpscare_Handler.triggerJumpscareAuto(this, targetEntity);

	        this.setJumpscaring(true);
	        this.setNoAI(true);
	        this.jumpscareCooldown = 30;
	        this.pendingAttackTarget = targetEntity;
	        this.attackDelayTicks = 18;
	        this.animationTick = 0;

	        return true;
	    }

	 @Override
	 public void updatePassenger(Entity passenger) {
	     if (this.isPassenger(passenger)) {
	         // Offsets relative to Piggy's rotation
	         float xOffset = 0.0F;      // Side-to-side (0 = centered)
	         float yOffset = 0.0F;      // Height (1.2 blocks above Piggy)
	         float zOffset = 1.5F;      // 1.5 blocks IN FRONT of Piggy

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
         public boolean shouldRiderSit() {
             return false; // Player will stand normally instead of sitting
         }

	 
	 @Override
	 public void onDeath(DamageSource cause) {
	     super.onDeath(cause); // calls EntityLivingBase logic
	     if (pendingAttackTarget != null) {
	         ACTIVE_ATTACKS.remove(pendingAttackTarget.getUniqueID());
	     }
	     // Optionally play a death animation or sound here
	 }
	 
	 @Override
	 public void setDead() {
		 super.setDead();
	     if (pendingAttackTarget != null) {
	         ACTIVE_ATTACKS.remove(pendingAttackTarget.getUniqueID());
	     }
	 }

	 protected boolean isMoving() {
	     return this.dataManager.get(IS_MOVING);
	 }

	 protected boolean isJumpscaring() {
	     return this.dataManager.get(IS_JUMPSCARING);
	 }

	 @Override
	 public int getPriority() {
	     return 2;
	 }
    
	 protected void setStunAnimation(String anim) {
        this.stunAnimation = anim;
     }
    	 
	    @Override
	    public void fall(float distance, float damageMultiplier) {
	        // Do nothing → no fall damage, no slow falling effect
	    }

	    // Satisfy IBot interface
	    public void getStunned(int ticks) {
	        this.stunTicksRemaining = ticks;
	        if (!this.world.isRemote) {
	            this.dataManager.set(IS_STUNNED, true);
	        }
	        this.onStunned();
	    }
	    
	    @Override
	    protected void playStepSound(BlockPos pos, Block blockIn) {
	        super.playStepSound(pos, blockIn); // play vanilla block footstep
	        this.playSound(
	            ModSounds.FOOTSTEP1,
	            0.15F,
	            1.0F
	        );
	    }
	    
	    protected boolean isStunned() {
	        return this.dataManager.get(IS_STUNNED);
	    }
}