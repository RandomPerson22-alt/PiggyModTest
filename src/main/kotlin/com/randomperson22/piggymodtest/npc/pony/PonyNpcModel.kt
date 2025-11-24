package com.randomperson22.piggymodtest.npc.pony

import net.minecraft.util.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

class PonyNpcModel : AnimatedGeoModel<EntityPonyNpc?>() {
    override fun getModelLocation(entity: EntityPonyNpc?): ResourceLocation {
        return ResourceLocation("piggymodtest", "geo/ponynpc.geo.json")
    }

    override fun getTextureLocation(entity: EntityPonyNpc?): ResourceLocation {
        return ResourceLocation("piggymodtest", "textures/entities/ponynpc.png")
    }

    override fun getAnimationFileLocation(entity: EntityPonyNpc?): ResourceLocation {
        return ResourceLocation("piggymodtest", "animations/doggynpc.animation.json")
    }
}