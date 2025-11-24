package com.randomperson22.piggymodtest.entities.sheepy

import net.minecraft.util.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

class SheepyModel : AnimatedGeoModel<EntitySheepy?>() {
    override fun getModelLocation(entity: EntitySheepy?): ResourceLocation {
        return ResourceLocation("piggymodtest", "geo/sheepy.geo.json")
    }

    override fun getTextureLocation(entity: EntitySheepy?): ResourceLocation {
        return ResourceLocation("piggymodtest", "textures/entities/sheepy.png")
    }

    override fun getAnimationFileLocation(entity: EntitySheepy?): ResourceLocation {
        return ResourceLocation("piggymodtest", "animations/piggy.animation.json")
    }
}
