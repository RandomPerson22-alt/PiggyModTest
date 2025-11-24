package com.randomperson22.piggymodtest.npc.doggy

import net.minecraft.util.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

class DoggyNpcModel : AnimatedGeoModel<EntityDoggyNpc?>() {
    override fun getModelLocation(entity: EntityDoggyNpc?): ResourceLocation {
        return ResourceLocation("piggymodtest", "geo/doggynpc.geo.json")
    }

    override fun getTextureLocation(p0: EntityDoggyNpc?): ResourceLocation {
        if (p0?.hasActivatedTexture() == true) {
            // The activated texture
            return ResourceLocation("piggymodtest", "textures/entities/doggywithbone.png")
        }
        // Default texture
        return ResourceLocation("piggymodtest", "textures/entities/doggynobone.png")
    }

    override fun getAnimationFileLocation(entity: EntityDoggyNpc?): ResourceLocation {
        return ResourceLocation("piggymodtest", "animations/doggynpc.animation.json")
    }
}