package com.randomperson22.piggymodtest.entities.sentinels

import net.minecraft.util.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel
import java.util.*

class SentinelModel : AnimatedGeoModel<SentinelBase>() {

    override fun getModelLocation(entity: SentinelBase): ResourceLocation {
        return ResourceLocation("piggymodtest", "geo/sentinel.geo.json")
    }

    override fun getTextureLocation(entity: SentinelBase): ResourceLocation {
        // SentinelRed -> sentinel_red.png
        val className = entity.javaClass.simpleName // "EntitySentinelRed"
        val color = className.replace("EntitySentinel", "").lowercase(Locale.getDefault()) // "red"
        val textureName = "sentinel_$color.png"

        return ResourceLocation("piggymodtest", "textures/entities/$textureName")
    }

    override fun getAnimationFileLocation(entity: SentinelBase): ResourceLocation {
        return ResourceLocation("piggymodtest", "animations/sentinel.animation.json")
    }
}
