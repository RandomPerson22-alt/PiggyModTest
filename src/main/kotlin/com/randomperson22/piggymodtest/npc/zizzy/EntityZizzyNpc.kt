package com.randomperson22.piggymodtest.npc.zizzy

import com.randomperson22.piggymodtest.npc.EntityNpc
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class EntityZizzyNpc(worldIn: World) : EntityNpc(worldIn) {
    override fun getRequiredItem(): Item? {
        return null
    }

    override fun isCorrectItem(stack: ItemStack): Boolean {
        return !stack.isEmpty() && stack.getItem() === Item.getItemFromBlock(Blocks.TALLGRASS) && stack.getMetadata() == 1
    }

    override fun onUpdate() {
        super.onUpdate()
    }
}