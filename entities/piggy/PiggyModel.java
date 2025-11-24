package com.randomperson22.piggymodtest.entities.piggy;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PiggyModel extends GeoModel<EntityPiggy> {
    @SuppressWarnings("removal")
	@Override
    public ResourceLocation getModelResource(EntityPiggy animatable) {
        return new ResourceLocation("piggymodtest", "geo/piggy.geo.json");
    }

    @SuppressWarnings("removal")
	@Override
    public ResourceLocation getTextureResource(EntityPiggy animatable) {
        return new ResourceLocation("piggymodtest", "textures/entity/piggy.png");
    }

    @SuppressWarnings("removal")
	@Override
    public ResourceLocation getAnimationResource(EntityPiggy animatable) {
        return new ResourceLocation("piggymodtest", "animations/piggy.animation.json");
    }
}