package com.randomperson22.piggymodtest.entities.sentinels;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SentinelModel extends AnimatedGeoModel<SentinelBase> {
	
    @Override
    public ResourceLocation getModelLocation(SentinelBase object) {
        return new ResourceLocation("piggymodtest", "geo/sentinel.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SentinelBase object) {
        // SentinelRed -> sentinel_red.png
        String className = object.getClass().getSimpleName(); // "EntitySentinelRed"
        String color = className.replace("EntitySentinel", "").toLowerCase(); // "red"
        String textureName = "sentinel_" + color + ".png";

        return new ResourceLocation("piggymodtest", "textures/entities/" + textureName);
    }


    @Override
    public ResourceLocation getAnimationFileLocation(SentinelBase animatable) {
        return new ResourceLocation("piggymodtest", "animations/sentinel.animation.json");
    }
}
