package com.randomperson22.piggymodtest.entities.kona;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KonaModel extends AnimatedGeoModel<EntityKona> {
	
    public ResourceLocation getModelLocation(EntityKona entity) {
        return new ResourceLocation("piggymodtest", "geo/kona.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityKona entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/kona.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityKona entity) {
        return new ResourceLocation("piggymodtest", "animations/kona.animation.json");
    }
}
