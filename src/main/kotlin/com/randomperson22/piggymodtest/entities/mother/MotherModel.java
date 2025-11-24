package com.randomperson22.piggymodtest.entities.mother;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MotherModel extends AnimatedGeoModel<EntityMother> {
	
    public ResourceLocation getModelLocation(EntityMother entity) {
        return new ResourceLocation("piggymodtest", "geo/mother.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityMother entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/mother.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityMother entity) {
        return new ResourceLocation("piggymodtest", "animations/piggy.animation.json");
    }
}
