package com.randomperson22.piggymodtest.entities.owell;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class OwellModel extends AnimatedGeoModel<EntityOwell> {
	
    public ResourceLocation getModelLocation(EntityOwell entity) {
        return new ResourceLocation("piggymodtest", "geo/owell.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityOwell entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/owell.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityOwell entity) {
        return new ResourceLocation("piggymodtest", "animations/owell.animation.json");
    }
}
