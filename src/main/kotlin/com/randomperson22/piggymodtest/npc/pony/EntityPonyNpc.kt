package com.randomperson22.piggymodtest.npc.pony

import com.randomperson22.piggymodtest.npc.EntityNpc
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.world.World

class EntityPonyNpc(worldIn: World) : EntityNpc(worldIn) {
    override fun getRequiredItem(): Item? {
        return Items.CARROT
    }

    override fun onUpdate() {
        super.onUpdate()
    }
}