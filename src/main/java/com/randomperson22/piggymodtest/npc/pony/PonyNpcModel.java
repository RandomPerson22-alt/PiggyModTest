package com.randomperson22.piggymodtest.npc.pony;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PonyNpcModel extends AnimatedGeoModel<EntityPonyNpc> {
	
    public ResourceLocation getModelLocation(EntityPonyNpc entity) {
        return new ResourceLocation("piggymodtest", "geo/ponynpc.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityPonyNpc entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/ponynpc.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityPonyNpc entity) {
        return new ResourceLocation("piggymodtest", "animations/doggynpc.animation.json");
    }
}