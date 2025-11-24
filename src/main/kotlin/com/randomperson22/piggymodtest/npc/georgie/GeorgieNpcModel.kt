package com.randomperson22.piggymodtest.npc.georgie

import net.minecraft.util.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

class GeorgieNpcModel : AnimatedGeoModel<EntityGeorgieNpc?>() {
    override fun getModelLocation(entity: EntityGeorgieNpc?): ResourceLocation {
        return ResourceLocation("piggymodtest", "geo/georgienpc.geo.json")
    }

    override fun getTextureLocation(entity: EntityGeorgieNpc?): ResourceLocation {
        return ResourceLocation("piggymodtest", "textures/entities/georgienpc.png")
    }

    override fun getAnimationFileLocation(entity: EntityGeorgieNpc?): ResourceLocation {
        return ResourceLocation("piggymodtest", "animations/georgienpc.animation.json")
    }
}