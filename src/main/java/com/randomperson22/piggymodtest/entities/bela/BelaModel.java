package com.randomperson22.piggymodtest.entities.bela;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BelaModel extends AnimatedGeoModel<EntityBela> {
	
    public ResourceLocation getModelLocation(EntityBela entity) {
        return new ResourceLocation("piggymodtest", "geo/bela.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityBela entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/bela.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityBela entity) {
        return new ResourceLocation("piggymodtest", "animations/bela.animation.json");
    }
}
