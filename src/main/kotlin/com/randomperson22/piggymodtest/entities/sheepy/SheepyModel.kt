package com.randomperson22.piggymodtest.entities.sheepy;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SheepyModel extends AnimatedGeoModel<EntitySheepy> {
	
    public ResourceLocation getModelLocation(EntitySheepy entity) {
        return new ResourceLocation("piggymodtest", "geo/sheepy.geo.json");
    }

    public ResourceLocation getTextureLocation(EntitySheepy entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/sheepy.png");
    }

    public ResourceLocation getAnimationFileLocation(EntitySheepy entity) {
        return new ResourceLocation("piggymodtest", "animations/piggy.animation.json");
    }
}
