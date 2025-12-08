package com.randomperson22.piggymodtest.entities.father;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FatherModel extends AnimatedGeoModel<EntityFather> {
	
    public ResourceLocation getModelLocation(EntityFather entity) {
        return new ResourceLocation("piggymodtest", "geo/father.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityFather entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/father.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityFather entity) {
        return new ResourceLocation("piggymodtest", "animations/piggy.animation.json");
    }
}
