package com.randomperson22.piggymodtest.entities.umbra;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class UmbraModel extends AnimatedGeoModel<EntityUmbra> {
	
    public ResourceLocation getModelLocation(EntityUmbra entity) {
        return new ResourceLocation("piggymodtest", "geo/umbra.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityUmbra entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/umbra.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityUmbra entity) {
        return new ResourceLocation("piggymodtest", "animations/umbra.animation.json");
    }
}
