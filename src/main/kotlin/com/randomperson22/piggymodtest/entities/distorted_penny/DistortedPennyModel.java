package com.randomperson22.piggymodtest.entities.distorted_penny;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DistortedPennyModel extends AnimatedGeoModel<EntityDistortedPenny> {
	
    public ResourceLocation getModelLocation(EntityDistortedPenny entity) {
        return new ResourceLocation("piggymodtest", "geo/distorted_penny.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityDistortedPenny entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/distorted_penny.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityDistortedPenny entity) {
        return new ResourceLocation("piggymodtest", "animations/distorted_penny.animation.json");
    }
}
