package com.randomperson22.piggymodtest.entities.robby

import net.minecraft.util.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

class RobbyModel : AnimatedGeoModel<EntityRobby?>() {
    override fun getModelLocation(entity: EntityRobby?): ResourceLocation {
        return ResourceLocation("piggymodtest", "geo/robby.geo.json")
    }

    override fun getTextureLocation(entity: EntityRobby?): ResourceLocation {
        return ResourceLocation("piggymodtest", "textures/entities/robby.png")
    }

    override fun getAnimationFileLocation(entity: EntityRobby?): ResourceLocation {
        return ResourceLocation("piggymodtest", "animations/robby.animation.json")
    }
}
