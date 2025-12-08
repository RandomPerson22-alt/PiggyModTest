package com.randomperson22.piggymodtest.npc;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.interfaces.IBot;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.Entity;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class EntityNpc extends EntityCreature implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    protected int jumpscareCooldown = 0;

    public EntityNpc(World worldIn) {
        super(worldIn);
        this.setNoAI(true); // start frozen
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.50D);
    }

    // Each NPC must define its activation item
    protected abstract Item getRequiredItem();

    protected boolean isCorrectItem(ItemStack stack) {
        Item required = getRequiredItem();
        if (required == null || stack.isEmpty()) return false;
        return stack.getItem() == required;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);

        // Block if already activated: don't consume item, don't trigger again
        if (this.getEntityData().getBoolean("Activated")) {
            return false;
        }

        if (!stack.isEmpty() && isCorrectItem(stack)) {
            // consume item only if activating now
            if (!player.capabilities.isCreativeMode) {
                stack.shrink(1);
            }

            // mark activated
            this.getEntityData().setBoolean("Activated", true);
            this.setNoAI(false);

            if (world.isRemote) {
                // client-side feedback
                for (int i = 0; i < 10; i++) {
                    double dx = this.posX + (this.rand.nextDouble() - 0.5D);
                    double dy = this.posY + this.rand.nextDouble() + 0.5D;
                    double dz = this.posZ + (this.rand.nextDouble() - 0.5D);

                    this.world.spawnParticle(
                        net.minecraft.util.EnumParticleTypes.VILLAGER_HAPPY,
                        dx, dy, dz,
                        0, 0.1D, 0
                    );
                }

                this.world.playSound(
                    player,
                    this.posX, this.posY, this.posZ,
                    net.minecraft.init.SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
                    net.minecraft.util.SoundCategory.NEUTRAL,
                    1.0F, 1.0F
                );
            }

            return true;
        }

        return super.processInteract(player, hand);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        updateHunt();

        // Handle jumpscare cooldown
        if (jumpscareCooldown > 0) {
            jumpscareCooldown--;
            if (jumpscareCooldown <= 0) {
                this.getEntityData().setBoolean("Jumpscaring", false);
                this.setNoAI(false);
            }
        }
    }

    protected void updateHunt() {
        if (!this.getEntityData().getBoolean("Activated")) return;

        // Find nearby bots
        List<EntityPiggyBase> bots = this.world.getEntitiesWithinAABB(EntityPiggyBase.class, this.getEntityBoundingBox().grow(32))
                .stream()
                .filter(b -> b instanceof IBot)
                .filter(b -> b.stunTicksRemaining <= 0)
                .map(b -> (EntityPiggyBase) b)
                .collect(Collectors.toList());

        if (bots.isEmpty()) return;

        // Sort by priority and distance
        bots.sort(
                Comparator
                        .comparingInt((EntityPiggyBase b) -> ((IBot) b).getPriority()).reversed()
                        .thenComparingDouble(b -> this.getDistance(b))
        );

        // Primary target
        EntityPiggyBase target = bots.get(0);
        double speed = this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
        this.getNavigator().tryMoveToEntityLiving(target, speed);

        // --- Check collision with the primary target ---
        if (this.getDistance(target) < 0.8F) {
            triggerStunAndJumpscare(target);
            return; // stop further checks
        }

        // --- Troll Easter egg: accidental touch other bots ---
        for (EntityPiggyBase bot : bots) {
            if (bot == target) continue; // skip primary target
            if (this.getDistance(bot) < 0.8F) {
                triggerStunAndJumpscare(bot); // do all stun/jumpscare stuff on accidental bot
                break; // only trigger once
            }
        }
    }

    // Extracted common method to handle stun/jumpscare
    private void triggerStunAndJumpscare(EntityPiggyBase bot) {
        bot.getStunned(400); // 20 seconds
        this.getEntityData().setBoolean("Activated", false);
        this.getEntityData().setBoolean("Jumpscaring", true);
        this.setNoAI(true); // freeze NPC during jumpscare
    }

    @Override
    public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio) {
        // No knockback
    }

    // ---- Animation ----
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "main_controller", 0, this::animationPredicate));
    }

    private <E extends IAnimatable> PlayState animationPredicate(AnimationEvent<E> event) {
        if (this.getEntityData().getBoolean("Jumpscaring")) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("jumpscare"));
            if (jumpscareCooldown <= 0) jumpscareCooldown = 120;
            return PlayState.CONTINUE;
        }

        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk"));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle"));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
