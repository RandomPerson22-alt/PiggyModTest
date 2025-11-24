package com.randomperson22.piggymodtest.entities.dinopiggy;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DinopiggyModel extends AnimatedGeoModel<EntityDinopiggy> {
	
    public ResourceLocation getModelLocation(EntityDinopiggy entity) {
        return new ResourceLocation("piggymodtest", "geo/dinopiggy.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityDinopiggy entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/dinopiggy.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityDinopiggy entity) {
        return new ResourceLocation("piggymodtest", "animations/dinopiggy.animation.json");
    }
}
