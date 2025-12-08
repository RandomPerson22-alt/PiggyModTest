package com.randomperson22.piggymodtest.entities.grandmother;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrandmotherModel extends AnimatedGeoModel<EntityGrandmother> {
	
    public ResourceLocation getModelLocation(EntityGrandmother entity) {
        return new ResourceLocation("piggymodtest", "geo/grandmother.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityGrandmother entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/grandmother.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityGrandmother entity) {
        return new ResourceLocation("piggymodtest", "animations/piggy.animation.json");
    }
}
