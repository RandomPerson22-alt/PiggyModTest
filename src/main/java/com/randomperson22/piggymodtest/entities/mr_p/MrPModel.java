package com.randomperson22.piggymodtest.entities.mr_p;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MrPModel extends AnimatedGeoModel<EntityMrP> {
	
    public ResourceLocation getModelLocation(EntityMrP entity) {
        return new ResourceLocation("piggymodtest", "geo/mr_p.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityMrP entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/mr_p.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityMrP entity) {
        return new ResourceLocation("piggymodtest", "animations/mr_p.animation.json");
    }
}
