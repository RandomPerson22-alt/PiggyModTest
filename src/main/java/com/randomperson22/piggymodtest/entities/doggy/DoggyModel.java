package com.randomperson22.piggymodtest.entities.doggy;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DoggyModel extends AnimatedGeoModel<EntityDoggy> {
	
    public ResourceLocation getModelLocation(EntityDoggy entity) {
        return new ResourceLocation("piggymodtest", "geo/doggy.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityDoggy entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/doggy.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityDoggy entity) {
        return new ResourceLocation("piggymodtest", "animations/doggy.animation.json");
    }
}
