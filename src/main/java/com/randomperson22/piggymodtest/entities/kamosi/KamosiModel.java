package com.randomperson22.piggymodtest.entities.kamosi;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KamosiModel extends AnimatedGeoModel<EntityKamosi> {
	
    public ResourceLocation getModelLocation(EntityKamosi entity) {
        return new ResourceLocation("piggymodtest", "geo/kamosi.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityKamosi entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/kamosi.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityKamosi entity) {
        return new ResourceLocation("piggymodtest", "animations/kamosi.animation.json");
    }
}
