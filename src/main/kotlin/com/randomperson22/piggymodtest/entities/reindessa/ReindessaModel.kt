package com.randomperson22.piggymodtest.entities.reindessa

import net.minecraft.util.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

class ReindessaModel : AnimatedGeoModel<EntityReindessa?>() {
    override fun getModelLocation(entity: EntityReindessa?): ResourceLocation {
        return ResourceLocation("piggymodtest", "geo/reindessa.geo.json")
    }

    override fun getTextureLocation(entity: EntityReindessa?): ResourceLocation {
        return ResourceLocation("piggymodtest", "textures/entities/reindessa.png")
    }

    override fun getAnimationFileLocation(entity: EntityReindessa?): ResourceLocation {
        return ResourceLocation("piggymodtest", "animations/reindessa.animation.json")
    }
}
