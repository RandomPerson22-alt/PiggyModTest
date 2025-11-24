package com.randomperson22.piggymodtest.entities.piggy;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PiggyModel extends AnimatedGeoModel<EntityPiggy> {
	
    public ResourceLocation getModelLocation(EntityPiggy entity) {
        return new ResourceLocation("piggymodtest", "geo/piggy.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityPiggy entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/piggy.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityPiggy entity) {
        return new ResourceLocation("piggymodtest", "animations/piggy.animation.json");
    }
}
