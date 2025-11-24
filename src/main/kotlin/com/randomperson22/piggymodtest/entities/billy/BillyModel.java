package com.randomperson22.piggymodtest.entities.billy;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BillyModel extends AnimatedGeoModel<EntityBilly> {
	
    public ResourceLocation getModelLocation(EntityBilly entity) {
        return new ResourceLocation("piggymodtest", "geo/billy.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityBilly entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/billy.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityBilly entity) {
        return new ResourceLocation("piggymodtest", "animations/billy.animation.json");
    }
}
