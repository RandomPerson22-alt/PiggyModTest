package com.randomperson22.piggymodtest.entities.duocara;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DuocaraModel extends AnimatedGeoModel<EntityDuocara> {
	
    public ResourceLocation getModelLocation(EntityDuocara entity) {
        return new ResourceLocation("piggymodtest", "geo/duocara.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityDuocara entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/duocara.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityDuocara entity) {
        return new ResourceLocation("piggymodtest", "animations/duocara.animation.json");
    }
}
