package com.randomperson22.piggymodtest.entities.mr_stitchy;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MrStitchyModel extends AnimatedGeoModel<EntityMrStitchy> {
	
    public ResourceLocation getModelLocation(EntityMrStitchy entity) {
        return new ResourceLocation("piggymodtest", "geo/mr_stitchy.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityMrStitchy entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/mr_stitchy.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityMrStitchy entity) {
        return new ResourceLocation("piggymodtest", "animations/mr_stitchy.animation.json");
    }
}
