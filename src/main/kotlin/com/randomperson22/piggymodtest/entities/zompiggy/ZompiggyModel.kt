package com.randomperson22.piggymodtest.entities.zompiggy

import net.minecraft.util.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

class ZompiggyModel : AnimatedGeoModel<EntityZompiggy?>() {
    override fun getModelLocation(entity: EntityZompiggy?): ResourceLocation {
        return ResourceLocation("piggymodtest", "geo/zompiggy.geo.json")
    }

    override fun getTextureLocation(entity: EntityZompiggy?): ResourceLocation {
        return ResourceLocation("piggymodtest", "textures/entities/zompiggy.png")
    }

    override fun getAnimationFileLocation(entity: EntityZompiggy?): ResourceLocation {
        return ResourceLocation("piggymodtest", "animations/zompiggy.animation.json")
    }
}
