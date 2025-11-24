package com.randomperson22.piggymodtest.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

//import com.randomperson22.piggymodtest.events.Jumpscare_Handler;
//import com.randomperson22.piggymodtest.interfaces.IBot;

public abstract class EntityPiggyBase extends Monster implements GeoEntity {
    
    // =========================
    // SYNCED DATA (REPLACES dataManager)
    // =========================
    protected static final EntityDataAccessor<Boolean> IS_MOVING =
            SynchedEntityData.defineId(EntityPiggyBase.class, EntityDataSerializers.BOOLEAN);

    protected static final EntityDataAccessor<Boolean> IS_JUMPSCARING =
            SynchedEntityData.defineId(EntityPiggyBase.class, EntityDataSerializers.BOOLEAN);

    protected static final EntityDataAccessor<Boolean> IS_STUNNED =
            SynchedEntityData.defineId(EntityPiggyBase.class, EntityDataSerializers.BOOLEAN);

    protected static final EntityDataAccessor<Boolean> IS_CLIMBING =
            SynchedEntityData.defineId(EntityPiggyBase.class, EntityDataSerializers.BOOLEAN);

    protected static final EntityDataAccessor<String> ANIM_STATE =
            SynchedEntityData.defineId(EntityPiggyBase.class, EntityDataSerializers.STRING);

    public static final ConcurrentHashMap<UUID, UUID> ACTIVE_ATTACKS = new ConcurrentHashMap<>();

    // STUFF YOU USED BEFORE
    protected LivingEntity pendingAttackTarget;
    protected int attackDelay = -1;
    protected int jumpscareCooldown = 0;
    protected int animTick = 0;
    public static final int JUMPSCARE_DURATION = 30;
    protected int stunTicks = 0;
    protected boolean climbing = false;

    // ============ GEO FACTORY ============
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public EntityPiggyBase(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.setPersistenceRequired();
    }

    // ============================================
    // ATTRIBUTES (REPLACES applyEntityAttributes)
    // ============================================
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.28)
                .add(Attributes.FOLLOW_RANGE, 200)
                .add(Attributes.ATTACK_DAMAGE, 0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1)
                .add(Attributes.MAX_HEALTH, 20);
    }

    // =====================
    // SYNCHED DATA SETUP
    // =====================
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_MOVING, false);
        this.entityData.define(IS_JUMPSCARING, false);
        this.entityData.define(IS_STUNNED, false);
        this.entityData.define(IS_CLIMBING, false);
        this.entityData.define(ANIM_STATE, "idle");
    }

    // ===============
    // BASIC GETTERS
    // ===============
    protected boolean isMoving() { return this.entityData.get(IS_MOVING); }
    protected boolean isJumpscaring() { return this.entityData.get(IS_JUMPSCARING); }
    protected boolean isStunned() { return this.entityData.get(IS_STUNNED); }

    // ===========
    // SOUND OVERRIDE
    // ===========
    protected abstract SoundEvent getJumpscareSound();

    // ===========
    // GOALS (AI)
    // ===========
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    // =====================================
    // MAIN TICK LOGIC (replaces onLivingUpdate)
    // =====================================
    @Override
    public void tick() {
        super.tick();
        animTick++;

        // Handle stun ticking
        if (stunTicks > 0) {
            stunTicks--;
            if (stunTicks <= 0) {
                entityData.set(IS_STUNNED, false);
                this.setNoAi(false);
            }
        }

        // Movement detection
        if (!level().isClientSide) {
            boolean moving = this.getDeltaMovement().horizontalDistanceSqr() > 0.0005;
            entityData.set(IS_MOVING, moving);
        }

        // Handle jumpscare ending
        if (isJumpscaring() && animTick >= JUMPSCARE_DURATION) {
            endJumpscare();
        }

        if (jumpscareCooldown > 0)
            jumpscareCooldown--;

        // ATTACK DELAY
        if (attackDelay > 0) {
            attackDelay--;
        } else if (attackDelay == 0 && pendingAttackTarget != null && pendingAttackTarget.isAlive()) {
            pendingAttackTarget.hurt(damageSources().mobAttack(this), 300f);
            attackDelay = -1;
        }
    }

    // ======================================
    // JUMPSCARE TRIGGER
    // ======================================
    @Override
    public boolean doHurtTarget(Entity target) {
        if (isJumpscaring() || jumpscareCooldown > 0)
            return false;

        if (!(target instanceof LivingEntity victim))
            return false;

        UUID id = victim.getUUID();
        if (ACTIVE_ATTACKS.containsKey(id) && !ACTIVE_ATTACKS.get(id).equals(this.getUUID()))
            return false;

        ACTIVE_ATTACKS.put(id, this.getUUID());

        // Sound
        this.level().playSound(null, this.blockPosition(), getJumpscareSound(), SoundSource.HOSTILE, 3f, 1f);

        // Gecko animation
        setAnimState("jumpscare");

        // External handler (camera, UI, etc.)
        //Jumpscare_Handler.triggerJumpscareAuto(this, victim);

        // "Grab" victim
        victim.startRiding(this);

        // Set states
        this.entityData.set(IS_JUMPSCARING, true);
        this.setNoAi(true);

        this.animTick = 0;
        this.jumpscareCooldown = 30;
        this.pendingAttackTarget = victim;
        this.attackDelay = 18;

        return true;
    }

    private void endJumpscare() {
        setAnimState("idle");
        this.entityData.set(IS_JUMPSCARING, false);
        this.setNoAi(false);

        if (pendingAttackTarget != null)
            ACTIVE_ATTACKS.remove(pendingAttackTarget.getUUID());
    }

    // ============
    // RIDER OFFSET
    // ============
    @Override
    protected void positionRider(Entity passenger, MoveFunction moveFunc) {
        float zOffset = 1.5f;

        float yawRad = this.getYRot() * Mth.DEG_TO_RAD;

        double offsetX = -Mth.sin(yawRad) * zOffset;
        double offsetZ = Mth.cos(yawRad) * zOffset;

        moveFunc.accept(
                passenger,
                this.getX() + offsetX,
                this.getY(),
                this.getZ() + offsetZ
        );
    }

    // ======================
    // STUN SYSTEM
    // ======================
    //@Override
   // public void getStunned(int ticks) {
   //     stunTicks = ticks;
   //     entityData.set(IS_STUNNED, true);
     //   this.setNoAi(true);
    //}

    // ============
    // ANIMATION LOGIC (GECKOLIB)
    // ============
    public void setAnimState(String anim) {
        this.entityData.set(ANIM_STATE, anim);
        animTick = 0;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(
                this,
                "main_controller",
                0,
                event -> {

                    if (isStunned()) {
                        event.setAnimation(RawAnimation.begin().thenLoop("stun"));
                        return PlayState.CONTINUE;
                    }

                    String anim = this.entityData.get(ANIM_STATE);

                    if (anim.equals("jumpscare")) {
                        event.setAnimation(RawAnimation.begin().thenPlay("jumpscare"));
                        return PlayState.CONTINUE;
                    }

                    if (isMoving()) {
                        event.setAnimation(DefaultAnimations.WALK);
                    } else {
                        event.setAnimation(DefaultAnimations.IDLE);
                    }

                    return PlayState.CONTINUE;
                }
        ));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    // ============
    // FOOTSTEP SOUND
    // ============
  /*  @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        super.playStepSound(pos, state);
        this.playSound(ModSounds.FOOTSTEP1.get(), 0.15f, 1f);
    }*/

    @Override
    protected void tickDeath() {
        super.tickDeath();
        if (pendingAttackTarget != null)
            ACTIVE_ATTACKS.remove(pendingAttackTarget.getUUID());
    }
}
