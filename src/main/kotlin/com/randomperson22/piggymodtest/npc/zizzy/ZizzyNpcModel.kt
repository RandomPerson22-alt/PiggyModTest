package com.randomperson22.piggymodtest.npc.zizzy

import net.minecraft.util.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

class ZizzyNpcModel : AnimatedGeoModel<EntityZizzyNpc?>() {
    override fun getModelLocation(entity: EntityZizzyNpc?): ResourceLocation {
        return ResourceLocation("piggymodtest", "geo/zizzy.geo.json")
    }

    override fun getTextureLocation(entity: EntityZizzyNpc?): ResourceLocation {
        return ResourceLocation("piggymodtest", "textures/entities/zizzynpc.png")
    }

    override fun getAnimationFileLocation(entity: EntityZizzyNpc?): ResourceLocation {
        return ResourceLocation("piggymodtest", "animations/zizzynpc.animation.json")
    }
}