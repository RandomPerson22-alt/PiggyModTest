package com.randomperson22.piggymodtest.npc.doggy;

import com.randomperson22.piggymodtest.npc.EntityNpc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityDoggyNpc extends EntityNpc {
	private static final DataParameter<Boolean> HAS_BONE = EntityDataManager.createKey(EntityDoggyNpc.class, DataSerializers.BOOLEAN);
	
    public EntityDoggyNpc(World worldIn) {
        super(worldIn);
    }
    
    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(HAS_BONE, false);
    }

    public void setActivatedTexture(boolean active) {
        this.dataManager.set(HAS_BONE, active);
    }

    public boolean hasActivatedTexture() {
        return this.dataManager.get(HAS_BONE);
    }
    
    @Override
    protected Item getRequiredItem() {
        return Items.BONE;
    }
    
    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        boolean activated = super.processInteract(player, hand); // base activation

        if (activated && this.getEntityData().getBoolean("Activated")) {
            // Switch texture for Doggy
            this.setActivatedTexture(true);

            // Spawn particles
            if (!world.isRemote) {
                world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY,
                                    this.posX, this.posY + 1, this.posZ,
                                    0, 0.1, 0);
            }
        }

        return activated;
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();

            if (!this.getEntityData().getBoolean("Activated") && this.hasActivatedTexture()) {
                this.setActivatedTexture(false);
            }
        }
    }