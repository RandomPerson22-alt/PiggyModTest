package com.randomperson22.piggymodtest.entities.robby;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RobbyModel extends AnimatedGeoModel<EntityRobby> {
	
    public ResourceLocation getModelLocation(EntityRobby entity) {
        return new ResourceLocation("piggymodtest", "geo/robby.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityRobby entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/robby.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityRobby entity) {
        return new ResourceLocation("piggymodtest", "animations/robby.animation.json");
    }
}
