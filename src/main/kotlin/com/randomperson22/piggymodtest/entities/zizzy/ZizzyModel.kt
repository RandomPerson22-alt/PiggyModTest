package com.randomperson22.piggymodtest.entities.zizzy

import net.minecraft.util.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

class ZizzyModel : AnimatedGeoModel<EntityZizzy?>() {
    override fun getModelLocation(entity: EntityZizzy?): ResourceLocation {
        return ResourceLocation("piggymodtest", "geo/zizzy.geo.json")
    }

    override fun getTextureLocation(entity: EntityZizzy?): ResourceLocation {
        return ResourceLocation("piggymodtest", "textures/entities/zizzy.png")
    }

    override fun getAnimationFileLocation(entity: EntityZizzy?): ResourceLocation {
        return ResourceLocation("piggymodtest", "animations/zizzy.animation.json")
    }
}
