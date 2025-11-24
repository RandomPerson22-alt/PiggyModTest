package com.randomperson22.piggymodtest.npc.doggy

import com.randomperson22.piggymodtest.npc.EntityNpc
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.network.datasync.DataParameter
import net.minecraft.network.datasync.DataSerializers
import net.minecraft.network.datasync.EntityDataManager
import net.minecraft.util.EnumHand
import net.minecraft.util.EnumParticleTypes
import net.minecraft.world.World

class EntityDoggyNpc(worldIn: World) : EntityNpc(worldIn) {
    override fun entityInit() {
        super.entityInit()
        this.dataManager.register<Boolean?>(HAS_BONE, false)
    }

    fun setActivatedTexture(active: Boolean) {
        this.dataManager.set<Boolean?>(HAS_BONE, active)
    }

    fun hasActivatedTexture(): Boolean {
        return this.dataManager.get<Boolean?>(HAS_BONE)
    }

    override fun getRequiredItem(): Item? {
        return Items.BONE
    }

    public override fun processInteract(player: EntityPlayer, hand: EnumHand): Boolean {
        val activated = super.processInteract(player, hand) // base activation

        if (activated && this.getEntityData().getBoolean("Activated")) {
            // Switch texture for Doggy
            this.setActivatedTexture(true)

            // Spawn particles
            if (!world.isRemote) {
                world.spawnParticle(
                    EnumParticleTypes.VILLAGER_HAPPY,
                    this.posX, this.posY + 1, this.posZ,
                    0.0, 0.1, 0.0
                )
            }
        }

        return activated
    }

    override fun onUpdate() {
        super.onUpdate()

        if (!this.getEntityData().getBoolean("Activated") && this.hasActivatedTexture()) {
            this.setActivatedTexture(false)
        }
    }

    companion object {
        private val HAS_BONE: DataParameter<Boolean?> =
            EntityDataManager.createKey<Boolean?>(EntityDoggyNpc::class.java, DataSerializers.BOOLEAN)
    }
}