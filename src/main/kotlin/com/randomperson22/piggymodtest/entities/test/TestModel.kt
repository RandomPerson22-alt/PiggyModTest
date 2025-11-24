package com.randomperson22.piggymodtest.entities.test

import net.minecraft.util.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

class TestModel : AnimatedGeoModel<EntityTest?>() {
    override fun getModelLocation(entity: EntityTest?): ResourceLocation {
        return ResourceLocation("piggymodtest", "geo/test.geo.json")
    }

    override fun getTextureLocation(entity: EntityTest?): ResourceLocation {
        return ResourceLocation("piggymodtest", "textures/entities/test.png")
    }

    override fun getAnimationFileLocation(entity: EntityTest?): ResourceLocation {
        return ResourceLocation("piggymodtest", "animations/test.animation.json")
    }
}
