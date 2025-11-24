package com.randomperson22.piggymodtest.entities.frostiggy;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FrostiggyModel extends AnimatedGeoModel<EntityFrostiggy> {
	
    public ResourceLocation getModelLocation(EntityFrostiggy entity) {
        return new ResourceLocation("piggymodtest", "geo/frostiggy.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityFrostiggy entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/frostiggy.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityFrostiggy entity) {
        return new ResourceLocation("piggymodtest", "animations/frostiggy.animation.json");
    }
}
