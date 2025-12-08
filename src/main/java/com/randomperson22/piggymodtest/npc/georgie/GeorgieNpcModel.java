package com.randomperson22.piggymodtest.npc.georgie;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GeorgieNpcModel extends AnimatedGeoModel<EntityGeorgieNpc> {
	
    public ResourceLocation getModelLocation(EntityGeorgieNpc entity) {
        return new ResourceLocation("piggymodtest", "geo/georgienpc.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityGeorgieNpc entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/georgienpc.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityGeorgieNpc entity) {
        return new ResourceLocation("piggymodtest", "animations/georgienpc.animation.json");
    }
}