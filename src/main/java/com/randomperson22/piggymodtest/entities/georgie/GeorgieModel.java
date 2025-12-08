package com.randomperson22.piggymodtest.entities.georgie;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GeorgieModel extends AnimatedGeoModel<EntityGeorgie> {
	
    public ResourceLocation getModelLocation(EntityGeorgie entity) {
        return new ResourceLocation("piggymodtest", "geo/georgie.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityGeorgie entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/georgie.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityGeorgie entity) {
        return new ResourceLocation("piggymodtest", "animations/piggy.animation.json");
    }
}
