package com.randomperson22.piggymodtest.entities.umbra

import net.minecraft.util.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

class UmbraModel : AnimatedGeoModel<EntityUmbra?>() {
    override fun getModelLocation(entity: EntityUmbra?): ResourceLocation {
        return ResourceLocation("piggymodtest", "geo/umbra.geo.json")
    }

    override fun getTextureLocation(entity: EntityUmbra?): ResourceLocation {
        return ResourceLocation("piggymodtest", "textures/entities/umbra.png")
    }

    override fun getAnimationFileLocation(entity: EntityUmbra?): ResourceLocation {
        return ResourceLocation("piggymodtest", "animations/umbra.animation.json")
    }
}
