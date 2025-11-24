package com.randomperson22.piggymodtest.entities.pandy;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PandyModel extends AnimatedGeoModel<EntityPandy> {
	
    public ResourceLocation getModelLocation(EntityPandy entity) {
        return new ResourceLocation("piggymodtest", "geo/pandy.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityPandy entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/pandy.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityPandy entity) {
        return new ResourceLocation("piggymodtest", "animations/piggy.animation.json");
    }
}
