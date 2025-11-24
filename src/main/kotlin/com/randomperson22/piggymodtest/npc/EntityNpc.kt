package com.randomperson22.piggymodtest.npc

import com.randomperson22.piggymodtest.entities.EntityPiggyBase
import com.randomperson22.piggymodtest.interfaces.IBot
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityCreature
import net.minecraft.entity.SharedMonsterAttributes
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumHand
import net.minecraft.world.World
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory

abstract class EntityNpc(worldIn: World) : EntityCreature(worldIn), IAnimatable {

    private val factory = AnimationFactory(this)
    protected var jumpscareCooldown = 0

    init {
        this.setNoAI(true) // start frozen
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).baseValue = 0.5
    }

    // Each NPC must define its activation item
    protected abstract fun getRequiredItem(): Item?

    protected open fun isCorrectItem(stack: ItemStack): Boolean {
        val required = getRequiredItem()
        if (required == null || stack.isEmpty) return false
        return stack.item == required
    }

    override fun processInteract(player: EntityPlayer, hand: EnumHand): Boolean {
        val stack = player.getHeldItem(hand)

        // Block if already activated
        if (this.entityData.getBoolean("Activated")) return false

        if (!stack.isEmpty && isCorrectItem(stack)) {
            if (!player.capabilities.isCreativeMode) {
                stack.shrink(1)
            }

            entityData.setBoolean("Activated", true)
            this.setNoAI(false)

            if (world.isRemote) {
                repeat(10) {
                    val dx = this.posX + (rand.nextDouble() - 0.5)
                    val dy = this.posY + rand.nextDouble() * 1.0 + 0.5
                    val dz = this.posZ + (rand.nextDouble() - 0.5)

                    this.world.spawnParticle(
                        net.minecraft.util.EnumParticleTypes.VILLAGER_HAPPY,
                        dx, dy, dz,
                        0.0, 0.1, 0.0
                    )
                }

                this.world.playSound(
                    player,
                    this.posX, this.posY, this.posZ,
                    net.minecraft.init.SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
                    net.minecraft.util.SoundCategory.NEUTRAL,
                    1.0f, 1.0f
                )
            }

            return true
        }

        return super.processInteract(player, hand)
    }

    override fun onLivingUpdate() {
        super.onLivingUpdate()
        updateHunt()

        if (jumpscareCooldown > 0) {
            jumpscareCooldown--
            if (jumpscareCooldown <= 0) {
                entityData.setBoolean("Jumpscaring", false)
                setNoAI(false)
            }
        }
    }

    protected open fun updateHunt() {
        if (!entityData.getBoolean("Activated")) return

        val bots = world.getEntitiesWithinAABB(EntityPiggyBase::class.java, this.entityBoundingBox.grow(32.0))
            .filterIsInstance<IBot>()
            .map { it as EntityPiggyBase }
            .sortedWith(
                compareByDescending<EntityPiggyBase> { (it as IBot).priority }
                    .thenBy { this.getDistance(it) }
            )

        if (bots.isEmpty()) return

        val target = bots[0]
        val speed = this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).attributeValue
        this.navigator.tryMoveToEntityLiving(target, speed)

        if (this.getDistance(target) < 0.8f) {
            target.getStunned(600)
            entityData.setBoolean("Activated", false)
            entityData.setBoolean("Jumpscaring", true)
            setNoAI(true)
        }
    }

    override fun applyEntityCollision(entityIn: Entity) {
        super.applyEntityCollision(entityIn)
        if (entityIn is EntityPiggyBase) {
            if (entityData.getBoolean("Activated")) {
                entityIn.getStunned(600)
                entityData.setBoolean("Jumpscaring", true)
                setNoAI(true)
            }
        }
    }

    override fun knockBack(entityIn: Entity, strength: Float, xRatio: Double, zRatio: Double) {
        // no knockback
    }

    // ---- Animation ----
    override fun registerControllers(data: AnimationData) {
        data.addAnimationController(AnimationController(this, "main_controller", 0f, ::animationPredicate))
    }

    private fun <E : IAnimatable> animationPredicate(event: AnimationEvent<E>): PlayState {
        return if (entityData.getBoolean("Jumpscaring")) {
            event.controller.setAnimation(AnimationBuilder().addAnimation("jumpscare"))
            if (jumpscareCooldown <= 0) jumpscareCooldown = 120
            PlayState.CONTINUE
        } else {
            event.controller.setAnimation(
                AnimationBuilder().addAnimation(if (event.isMoving) "walk" else "idle")
            )
            PlayState.CONTINUE
        }
    }

    override fun getFactory(): AnimationFactory = factory
}