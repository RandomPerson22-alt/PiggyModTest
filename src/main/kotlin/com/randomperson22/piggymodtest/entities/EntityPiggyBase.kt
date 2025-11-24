package com.randomperson22.piggymodtest.entities

import com.randomperson22.piggymodtest.events.JumpscareHandler.triggerJumpscareAuto
import com.randomperson22.piggymodtest.init.ModSounds
import com.randomperson22.piggymodtest.interfaces.IBot
import net.minecraft.block.Block
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityList
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.SharedMonsterAttributes
import net.minecraft.entity.ai.*
import net.minecraft.entity.monster.EntityMob
import net.minecraft.entity.passive.EntityVillager
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.network.datasync.DataParameter
import net.minecraft.network.datasync.DataSerializers
import net.minecraft.network.datasync.EntityDataManager
import net.minecraft.pathfinding.PathNavigate
import net.minecraft.pathfinding.PathNavigateGround
import net.minecraft.util.DamageSource
import net.minecraft.util.SoundCategory
import net.minecraft.util.SoundEvent
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.controller.AnimationController.IAnimationPredicate
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

abstract class EntityPiggyBase(worldIn: World) : EntityMob(worldIn), IAnimatable, IBot {
    protected var pendingAttackTarget: EntityLivingBase? = null
    protected var attackDelayTicks: Int = -1
    protected var jumpscareCooldown: Int = 0
    protected var animationTick: Int = 0
    protected var stunTicksRemaining: Int = 0
    protected var stunAnimation: String? = "stun1" // default for most bots
    protected abstract val jumpscareSound: SoundEvent
    protected var climbingLadder: Boolean = false

    protected val factory: AnimationFactory = AnimationFactory(this)
    protected val mainController: AnimationController<EntityPiggyBase> = AnimationController(
        this,
        "mainController",
        0f
    ) { event: AnimationEvent<EntityPiggyBase> ->
        predicate(event)
    }

    init {
        this.enablePersistence()
        (this.getNavigator() as PathNavigateGround).setBreakDoors(true)
        (this.getNavigator() as PathNavigateGround).setCanSwim(true)
    }

    override fun createNavigator(worldIn: World): PathNavigate {
        val nav = PathNavigateGround(this, worldIn)
        nav.setCanSwim(false) // optional
        nav.setEnterDoors(true)
        nav.setBreakDoors(true)
        return nav
    }

    override fun entityInit() {
        super.entityInit()
        this.dataManager.register<Boolean?>(IS_MOVING, false)
        this.dataManager.register<Boolean?>(IS_JUMPSCARING, false)
        this.dataManager.register<String?>(ANIMATION_STATE, "idle")
        this.dataManager.register<Boolean?>(IS_STUNNED, false)
        this.dataManager.register<Boolean?>(IS_CLIMBING_LADDER, false)
    }

    protected val isOnClimbable: Boolean
        get() {
            val posFeet =
                BlockPos(this.posX, this.posY, this.posZ)
            val posHead =
                BlockPos(this.posX, this.posY + 1, this.posZ)

            val blockFeet = this.world.getBlockState(posFeet).getBlock()
            val blockHead = this.world.getBlockState(posHead).getBlock()

            return blockFeet === Blocks.LADDER || blockFeet === Blocks.VINE || blockHead === Blocks.LADDER || blockHead === Blocks.VINE
        }

    protected fun hasLadderAbove(): Boolean {
        val above = BlockPos(this.posX, this.posY + 2.5, this.posZ) // 1.5 blocks above feet
        val blockAbove = this.world.getBlockState(above).getBlock()
        return blockAbove === Blocks.LADDER || blockAbove === Blocks.VINE
    }

    fun setCurrentAnimation(animationName: String) {
        this.mainController.markNeedsReload() // resets controller immediately
        this.dataManager.set<String?>(ANIMATION_STATE, animationName)
        this.animationTick = 0
    }

    protected fun onStunned() {
        this.setNoAI(true)
    }

    protected fun onStunEnd() {
        this.setNoAI(false)
    }

    protected fun getCurrentAnimation(): String {
        return this.dataManager.get<String>(ANIMATION_STATE)
    }

    protected fun <E : IAnimatable> predicate(event: AnimationEvent<E>): PlayState {
        // 1. Stun first
        if (this.stunTicksRemaining > 0) {
            event.controller.setAnimation(
                AnimationBuilder()
                    .addAnimation(this.stunAnimation) // non-null default
                    .addRepeatingAnimation(this.stunAnimation, -1)
            )
            return PlayState.CONTINUE
        }

        // 2. Forced animations (like jumpscare)
        val anim = this.getCurrentAnimation()
        if (anim == "jumpscare") {
            event.controller.setAnimation(AnimationBuilder().addAnimation("jumpscare"))
            return PlayState.CONTINUE
        }

        // 3. Any other forced animation (single-play)
        if (anim != "idle" && anim != "walk") {
            event.controller.setAnimation(AnimationBuilder().addAnimation(anim))
            return PlayState.CONTINUE
        }

        // 4. Walk / Idle as default
        if (this.isMoving) {
            event.controller.setAnimation(
                AnimationBuilder()
                    .addAnimation("walk")
                    .addRepeatingAnimation("walk", -1)
            )
        } else {
            event.controller.setAnimation(
                AnimationBuilder()
                    .addAnimation("idle")
                    .addRepeatingAnimation("idle", -1)
            )
        }

        return PlayState.CONTINUE
    }

    override fun registerControllers(data: AnimationData) {
        data.addAnimationController(mainController)
    }

    override fun getFactory(): AnimationFactory {
        return factory
    }

    override fun applyEntityAttributes() {
        super.applyEntityAttributes()
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28)
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(200.0)
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.0)
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0)
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0)
    }

    override fun initEntityAI() {
        this.tasks.addTask(0, EntityAISwimming(this))
        this.tasks.addTask(1, EntityAIAttackMelee(this, 1.0, true))
        this.tasks.addTask(2, EntityAIWanderAvoidWater(this, 1.0))
        this.tasks.addTask(3, EntityAIWatchClosest(this, EntityPlayer::class.java, 8.0f))
        this.tasks.addTask(4, EntityAIOpenDoor(this, true))
        this.tasks.addTask(5, EntityAILookIdle(this))
        this.applyEntityAI()
    }

    protected fun applyEntityAI() {
        this.targetTasks.addTask(
            1,
            EntityAINearestAttackableTarget<EntityPlayer?>(this, EntityPlayer::class.java, false)
        )
        this.targetTasks.addTask(
            1,
            EntityAINearestAttackableTarget<EntityVillager?>(this, EntityVillager::class.java, false)
        )
        this.targetTasks.addTask(2, EntityAIHurtByTarget(this, true))
    }

    override fun canDespawn(): Boolean {
        return false
    }

    override fun applyEntityCollision(entityIn: Entity) {
        super.applyEntityCollision(entityIn) // keep normal physics

        if (this.isDead) return  // stop after death

        if (entityIn is EntityPlayer) return  // ignore players

        if (entityIn !is EntityLivingBase) return  // only living entities


        // --- Get entity ID safely ---
        val entityID = EntityList.getKey(entityIn)
        if (entityID != null) {
            val id = entityID.toString() // e.g. "minecraft:zombie" or "piggymodtest:piggy"

            // Skip your own mod's entities
            if (id.startsWith("piggymodtest:")) return

            // Skip other mods' entities (only allow vanilla)
            if (!id.startsWith("minecraft:")) return
        }

        val targetEntity = entityIn

        // Only attack if not already jumpscaring / cooldown
        if (!this.isJumpscaring && this.jumpscareCooldown <= 0) {
            this.attackEntityAsMob(targetEntity) // full jumpscare attack
        }
    }

    override fun onLivingUpdate() {
        super.onLivingUpdate()
        this.animationTick++

        val target = this.getAttackTarget()
        if (target != null && this.canEntityBeSeen(target)) {
            this.faceEntity(target, 30.0f, 30.0f)


            // Check if target is already claimed (unless it's ours)
            if (ACTIVE_ATTACKS.containsKey(target.getUniqueID()) && ACTIVE_ATTACKS.get(target.getUniqueID()) != this.getUniqueID()) {
                this.setAttackTarget(null) // Find another target
            }
        }
        if (this.stunTicksRemaining > 0) {
            this.stunTicksRemaining--
            if (this.stunTicksRemaining <= 0) {
                this.dataManager.set<Boolean?>(IS_STUNNED, false)
                this.onStunEnd()
            }
        }

        // --- LADDER CHECK ---
        if (!this.world.isRemote) {
            if (this.isOnClimbable) {
                if (!this.climbingLadder) {
                    this.setNoAI(true) // disable AI
                    this.climbingLadder = true
                    this.dataManager.set<Boolean?>(IS_CLIMBING_LADDER, true)
                }

                // --- Force piggy to ladder block ---
                val ladderPos = BlockPos(this.posX, this.posY, this.posZ)
                val block = this.world.getBlockState(ladderPos).getBlock()

                if (block === Blocks.LADDER || block === Blocks.VINE) {
                    // Snap to center
                    val snapX = ladderPos.getX() + 0.5
                    val snapY = ladderPos.getY().toDouble() // current height, we'll add motionY next
                    val snapZ = ladderPos.getZ() + 0.5
                    this.setPosition(snapX, snapY, snapZ)

                    // --- Face toward ladder ---
                    val dx = snapX - this.posX
                    val dz = snapZ - this.posZ
                    val yaw = (Math.toDegrees(atan2(dz, dx)) - 90).toFloat()
                    this.rotationYaw = yaw
                    this.rotationYawHead = yaw

                    // --- Climb upward slowly ---
                    val climbSpeed = 0.05 // adjust for slower/faster climb
                    val above = ladderPos.up()
                    val blockAbove = this.world.getBlockState(above).getBlock()
                    if (blockAbove === Blocks.LADDER || blockAbove === Blocks.VINE) {
                        // Move upward if ladder above
                        this.setPosition(this.posX, this.posY + climbSpeed, this.posZ)
                    }
                }
            } else {
                // Not on ladder → restore AI
                if (this.climbingLadder) {
                    this.setNoAI(false)
                    this.climbingLadder = false
                    this.dataManager.set<Boolean?>(IS_CLIMBING_LADDER, false)
                }
            }
        }

        // Original update logic
        if (!this.world.isRemote) {
            val moving = abs(this.motionX) > 0.001 || abs(this.motionZ) > 0.001
            this.dataManager.set<Boolean?>(IS_MOVING, moving)

            if (this.isJumpscaring) {
                // Always finish the jumpscare after the set duration
                if (this.animationTick >= JUMPSCARE_DURATION) {
                    resetAnimationState()
                }
            }

            if (jumpscareCooldown > 0) {
                jumpscareCooldown--
            }

            if (this.attackDelayTicks > 0) {
                this.attackDelayTicks--
            } else if (this.attackDelayTicks == 0 && this.pendingAttackTarget != null &&
                this.pendingAttackTarget!!.isEntityAlive()
            ) {
                this.pendingAttackTarget!!.attackEntityFrom(DamageSource.causeMobDamage(this), 300.0f)
                this.attackDelayTicks = -1
            }

            if (!this.isJumpscaring) {
                setCurrentAnimation(if (this.isMoving) "walk" else "idle")
            }
        }
    }

    protected fun resetAnimationState() {
        this.setCurrentAnimation("idle")
        this.animationTick = 0
        this.isJumpscaring = false
        this.setNoAI(false)


        // Release our attack claim if we had one
        if (pendingAttackTarget != null) {
            ACTIVE_ATTACKS.remove(pendingAttackTarget!!.getUniqueID())
            pendingAttackTarget = null
        }
    }

    override fun attackEntityAsMob(entityIn: Entity): Boolean {
        if (this.isJumpscaring || jumpscareCooldown > 0) {
            return false
        }

        this.mainController.markNeedsReload()
        this.setCurrentAnimation("jumpscare")

        if (entityIn !is EntityLivingBase) return false
        val targetEntity = entityIn

        if (ACTIVE_ATTACKS.containsKey(targetEntity.getUniqueID())) {
            val currentAttacker: UUID = ACTIVE_ATTACKS.get(targetEntity.getUniqueID())!!
            if (currentAttacker != this.getUniqueID()) return false
        }
        ACTIVE_ATTACKS.put(targetEntity.getUniqueID(), this.getUniqueID())

        targetEntity.startRiding(this)

        // Play the entity-specific jumpscare sound
        val soundEvent = this.jumpscareSound
        this.world.playSound(
            null,
            this.posX, this.posY, this.posZ,
            soundEvent,
            SoundCategory.HOSTILE,
            3f, 1f
        )

        // Always call handler for players (UI / camera / packet)
        triggerJumpscareAuto(this, targetEntity)

        this.isJumpscaring = true
        this.setNoAI(true)
        this.jumpscareCooldown = 30
        this.pendingAttackTarget = targetEntity
        this.attackDelayTicks = 18
        this.animationTick = 0

        return true
    }

    override fun updatePassenger(passenger: Entity) {
        if (this.isPassenger(passenger)) {
            // Offsets relative to Piggy's rotation
            val xOffset = 0.0f // Side-to-side (0 = centered)
            val yOffset = 0.0f // Height (1.2 blocks above Piggy)
            val zOffset = 1.5f // 1.5 blocks IN FRONT of Piggy

            // Convert Piggy's rotation to radians
            val yawRad = this.rotationYaw * (Math.PI / 180.0f).toFloat()

            // Calculate rotated offsets (relative to Piggy's facing direction)
            val rotatedX = -sin(yawRad.toDouble()) * zOffset
            val rotatedZ = cos(yawRad.toDouble()) * zOffset

            // Set player position
            passenger.setPosition(
                this.posX + rotatedX + xOffset,
                this.posY + yOffset,
                this.posZ + rotatedZ
            )
        }
    }

    override fun shouldRiderSit(): Boolean {
        return false // Player will stand normally instead of sitting
    }


    override fun onDeath(cause: DamageSource) {
        super.onDeath(cause) // calls EntityLivingBase logic
        if (pendingAttackTarget != null) {
            ACTIVE_ATTACKS.remove(pendingAttackTarget!!.getUniqueID())
        }
        // Optionally play a death animation or sound here
    }

    override fun setDead() {
        super.setDead()
        if (pendingAttackTarget != null) {
            ACTIVE_ATTACKS.remove(pendingAttackTarget!!.getUniqueID())
        }
    }

    protected val isMoving: Boolean
        get() = this.dataManager.get<Boolean?>(IS_MOVING)

    protected var isJumpscaring: Boolean
        get() = this.dataManager.get<Boolean?>(IS_JUMPSCARING)
        set(jumpscaring) {
            this.dataManager.set<Boolean?>(IS_JUMPSCARING, jumpscaring)
            if (jumpscaring) {
                this.jumpscareCooldown = 0
            }
        }

    override val priority: Int
        get() = 2

    override fun fall(distance: Float, damageMultiplier: Float) {
        // Do nothing → no fall damage, no slow falling effect
    }

    // Satisfy IBot interface
    override fun getStunned(ticks: Int) {
        this.stunTicksRemaining = ticks
        if (!this.world.isRemote) {
            this.dataManager.set<Boolean?>(IS_STUNNED, true)
        }
        this.onStunned()
    }

    override fun playStepSound(pos: BlockPos, blockIn: Block) {
        super.playStepSound(pos, blockIn) // play vanilla block footstep
        this.playSound(
            ModSounds.FOOTSTEP1,
            0.15f,
            1.0f
        )
    }

    protected val isStunned: Boolean
        get() = this.dataManager.get<Boolean?>(IS_STUNNED)

    companion object {
        protected val IS_MOVING: DataParameter<Boolean?> =
            EntityDataManager.createKey<Boolean?>(EntityPiggyBase::class.java, DataSerializers.BOOLEAN)
        protected val IS_JUMPSCARING: DataParameter<Boolean?> =
            EntityDataManager.createKey<Boolean?>(EntityPiggyBase::class.java, DataSerializers.BOOLEAN)
        protected val ANIMATION_STATE: DataParameter<String?> =
            EntityDataManager.createKey<String?>(EntityPiggyBase::class.java, DataSerializers.STRING)
        private val IS_STUNNED: DataParameter<Boolean?> =
            EntityDataManager.createKey<Boolean?>(EntityPiggyBase::class.java, DataSerializers.BOOLEAN)
        protected val IS_CLIMBING_LADDER: DataParameter<Boolean?> =
            EntityDataManager.createKey<Boolean?>(EntityPiggyBase::class.java, DataSerializers.BOOLEAN)
        protected val ACTIVE_ATTACKS: ConcurrentHashMap<UUID?, UUID> = ConcurrentHashMap<UUID?, UUID>()

        protected const val JUMPSCARE_DURATION: Int = 30
    }
}