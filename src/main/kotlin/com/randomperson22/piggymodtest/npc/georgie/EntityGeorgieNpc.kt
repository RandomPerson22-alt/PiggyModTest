package com.randomperson22.piggymodtest.npc.georgie

import com.randomperson22.piggymodtest.npc.EntityNpc
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.world.World

class EntityGeorgieNpc(worldIn: World) : EntityNpc(worldIn) {
    override fun getRequiredItem(): Item? {
        return Items.APPLE
    }

    override fun onUpdate() {
        super.onUpdate()
    }
}