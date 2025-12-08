package com.randomperson22.piggymodtest.entities.zizzy;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ZizzyModel extends AnimatedGeoModel<EntityZizzy> {
	
    public ResourceLocation getModelLocation(EntityZizzy entity) {
        return new ResourceLocation("piggymodtest", "geo/zizzy.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityZizzy entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/zizzy.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityZizzy entity) {
        return new ResourceLocation("piggymodtest", "animations/zizzy.animation.json");
    }
}
