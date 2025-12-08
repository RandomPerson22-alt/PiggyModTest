package com.randomperson22.piggymodtest.entities;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.randomperson22.piggymodtest.events.Jumpscare_Handler;
import com.randomperson22.piggymodtest.interfaces.IBot;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public abstract class EntityPiggyBase extends EntityMob implements IAnimatable, IBot {
    private static final DataParameter<Boolean> IS_MOVING = EntityDataManager.createKey(EntityPiggyBase.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> IS_JUMPSCARING = EntityDataManager.createKey(EntityPiggyBase.class, DataSerializers.BOOLEAN);
    private static final DataParameter<String> ANIMATION_STATE = EntityDataManager.createKey(EntityPiggyBase.class, DataSerializers.STRING);

    protected static final ConcurrentHashMap<UUID, UUID> ACTIVE_ATTACKS = new ConcurrentHashMap<UUID, UUID>();

    protected EntityLivingBase pendingAttackTarget = null;
    protected int attackDelayTicks = -1;
    public int jumpscareCooldown = 0;
    protected int animationTick = 0;
    public static final int JUMPSCARE_DURATION = 30;
    public int stunTicksRemaining = 0;
    protected String stunAnimation = "stun1"; // default for most bots
    public abstract SoundEvent getJumpscareSound();

    private final AnimationFactory factory = new AnimationFactory(this);
    private final AnimationController<EntityPiggyBase> mainController = new AnimationController<>(this, "mainController", 0, this::predicate);

    public EntityPiggyBase(World worldIn) {
        super(worldIn);
        this.enablePersistence();
        ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
        ((PathNavigateGround)this.getNavigator()).setCanSwim(true);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(IS_MOVING, false);
        this.dataManager.register(IS_JUMPSCARING, false);
        this.dataManager.register(ANIMATION_STATE, "idle");
    }

    public void setCurrentAnimation(String animationName) {
        this.mainController.markNeedsReload(); // resets controller immediately
        this.dataManager.set(ANIMATION_STATE, animationName);
        this.animationTick = 0;
    }

    protected void onStunned() {
        this.setNoAI(true); //just set NoAi... for now
    }

    protected void onStunEnd() {
        // Re-enable AI when stun finishes (which reset anim state does)
        resetAnimationState();
    }

    public String getCurrentAnimation() {
        return this.dataManager.get(ANIMATION_STATE);
    }

    public int getAnimationTick() {
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
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));

        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, false));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityVillager.class, false));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
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
            this.attackEntityAsMob(targetEntity);
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.animationTick++;

        // --- STUN HANDLING ---
        if (stunTicksRemaining > 0) {
            stunTicksRemaining--; // count down
            if (stunTicksRemaining == 0) {
                onStunEnd(); // restore AI
            }
        }

        EntityLivingBase target = this.getAttackTarget();
        if (target != null && this.canEntityBeSeen(target)) {
            this.faceEntity(target, 30.0F, 30.0F);

            // Check if target is already claimed (unless it's ours)
            if (ACTIVE_ATTACKS.containsKey(target.getUniqueID()) &&
                    !ACTIVE_ATTACKS.get(target.getUniqueID()).equals(this.getUniqueID())) {
                this.setAttackTarget(null); // Find another target
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
                this.pendingAttackTarget.attackEntityFrom(DamageSource.causeMobDamage(this), 5000.0F);
                this.attackDelayTicks = -1;
            }

            if (!this.isJumpscaring()) {
                setCurrentAnimation(this.isMoving() ? "walk" : "idle");
            }
        }
    }

    private void resetAnimationState() {
        this.setCurrentAnimation("idle");
        this.animationTick = 0;
        this.setJumpscaring(false);
        this.setNoAI(false);

        this.getNavigator().clearPath();
        this.motionX = 0;
        this.motionY = 0;
        this.motionZ = 0;
        this.velocityChanged = true;
        this.getNavigator().clearPath();

        // Release our attack claim if we had one
        if (pendingAttackTarget != null) {
            ACTIVE_ATTACKS.remove(pendingAttackTarget.getUniqueID());
            pendingAttackTarget = null;
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (isJumpscaring() || jumpscareCooldown > 0) {
            return false;
        }

        mainController.markNeedsReload();
        setCurrentAnimation("jumpscare");

        if (!(entityIn instanceof EntityLivingBase)) {
            return false;
        }

        EntityLivingBase targetEntity = (EntityLivingBase) entityIn;

        // Check if target is already claimed
        UUID targetId = targetEntity.getUniqueID();
        if (ACTIVE_ATTACKS.containsKey(targetId)) {
            UUID currentAttacker = ACTIVE_ATTACKS.get(targetId);
            if (!currentAttacker.equals(this.getUniqueID())) {
                return false;
            }
        }

        ACTIVE_ATTACKS.put(targetId, this.getUniqueID());

        // Make the target ride this entity
        targetEntity.startRiding(this);

        // Play jumpscare sound
        SoundEvent soundEvent = getJumpscareSound();
        this.world.playSound(
                null,
                this.posX, this.posY, this.posZ,
                soundEvent,
                SoundCategory.HOSTILE,
                3.0f, 1.0f
        );

        // Trigger jumpscare handler (client/server)
        Jumpscare_Handler.triggerJumpscareAuto(this, targetEntity);

        // Update states
        setJumpscaring(true);
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
    public void onDeath(DamageSource cause) {
        super.onDeath(cause); // calls EntityLivingBase logic
        if (pendingAttackTarget != null) {
            ACTIVE_ATTACKS.remove(pendingAttackTarget.getUniqueID());
        }
        test();
    }

    @Override
    public void setDead() {
        super.setDead();
        if (pendingAttackTarget != null) {
            ACTIVE_ATTACKS.remove(pendingAttackTarget.getUniqueID());
        }
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

    public void setStunAnimation(String anim) {
        this.stunAnimation = anim;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
        // Do nothing → no fall damage, no slow falling effect
    }

    // Satisfy IBot interface
    public void getStunned(int ticks) {
        this.stunTicksRemaining = ticks;
        this.onStunned(); // hook into subclass
    }

    @Override
    public boolean canBeTargeted() {
        return stunTicksRemaining <= 0;
    }

    protected void test() {
        //base logic
    }
}