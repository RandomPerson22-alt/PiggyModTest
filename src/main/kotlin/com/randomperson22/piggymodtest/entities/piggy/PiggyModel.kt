package com.randomperson22.piggymodtest.entities.piggy

import net.minecraft.util.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

class PiggyModel : AnimatedGeoModel<EntityPiggy?>() {
    override fun getModelLocation(entity: EntityPiggy?): ResourceLocation {
        return ResourceLocation("piggymodtest", "geo/piggy.geo.json")
    }

    override fun getTextureLocation(entity: EntityPiggy?): ResourceLocation {
        return ResourceLocation("piggymodtest", "textures/entities/piggy.png")
    }

    override fun getAnimationFileLocation(entity: EntityPiggy?): ResourceLocation {
        return ResourceLocation("piggymodtest", "animations/piggy.animation.json")
    }
}
