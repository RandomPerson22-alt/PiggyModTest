package com.randomperson22.piggymodtest.entities.badgy;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BadgyModel extends AnimatedGeoModel<EntityBadgy> {
	
    public ResourceLocation getModelLocation(EntityBadgy entity) {
        return new ResourceLocation("piggymodtest", "geo/badgy.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityBadgy entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/badgy.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityBadgy entity) {
        return new ResourceLocation("piggymodtest", "animations/badgy.animation.json");
    }
}