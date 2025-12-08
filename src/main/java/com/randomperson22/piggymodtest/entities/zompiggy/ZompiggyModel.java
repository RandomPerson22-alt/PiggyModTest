package com.randomperson22.piggymodtest.entities.zompiggy;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ZompiggyModel extends AnimatedGeoModel<EntityZompiggy> {
	
    public ResourceLocation getModelLocation(EntityZompiggy entity) {
        return new ResourceLocation("piggymodtest", "geo/zompiggy.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityZompiggy entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/zompiggy.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityZompiggy entity) {
        return new ResourceLocation("piggymodtest", "animations/zompiggy.animation.json");
    }
}
