package com.randomperson22.piggymodtest.entities.mimi;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MimiModel extends AnimatedGeoModel<EntityMimi> {
	
    public ResourceLocation getModelLocation(EntityMimi entity) {
        return new ResourceLocation("piggymodtest", "geo/mimi.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityMimi entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/mimi.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityMimi entity) {
        return new ResourceLocation("piggymodtest", "animations/mimi.animation.json");
    }
}
