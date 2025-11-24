package com.randomperson22.piggymodtest.items

import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.DamageSource

class ItemBaseballBat : Item() {
    init {
        setMaxStackSize(1)
    }

    override fun hitEntity(stack: ItemStack, target: EntityLivingBase, attacker: EntityLivingBase): Boolean {
        target.attackEntityFrom(DamageSource.causePlayerDamage(attacker as EntityPlayer), 5.0f)
        return true
    }
}
