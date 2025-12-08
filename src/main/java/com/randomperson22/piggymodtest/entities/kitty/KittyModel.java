package com.randomperson22.piggymodtest.entities.kitty;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KittyModel extends AnimatedGeoModel<EntityKitty> {
	
    public ResourceLocation getModelLocation(EntityKitty entity) {
        return new ResourceLocation("piggymodtest", "geo/kitty.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityKitty entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/kitty.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityKitty entity) {
        return new ResourceLocation("piggymodtest", "animations/piggy.animation.json");
    }
}
