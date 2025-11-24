package com.randomperson22.piggymodtest.entities.test;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TestModel extends AnimatedGeoModel<EntityTest> {
	
    public ResourceLocation getModelLocation(EntityTest entity) {
        return new ResourceLocation("piggymodtest", "geo/test.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityTest entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/test.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityTest entity) {
        return new ResourceLocation("piggymodtest", "animations/test.animation.json");
    }
}
