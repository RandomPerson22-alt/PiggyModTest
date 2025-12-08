package com.randomperson22.piggymodtest.entities.reindessa;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ReindessaModel extends AnimatedGeoModel<EntityReindessa> {
	
    public ResourceLocation getModelLocation(EntityReindessa entity) {
        return new ResourceLocation("piggymodtest", "geo/reindessa.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityReindessa entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/reindessa.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityReindessa entity) {
        return new ResourceLocation("piggymodtest", "animations/reindessa.animation.json");
    }
}
