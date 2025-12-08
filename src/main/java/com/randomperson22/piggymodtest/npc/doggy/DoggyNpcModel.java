package com.randomperson22.piggymodtest.npc.doggy;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DoggyNpcModel extends AnimatedGeoModel<EntityDoggyNpc> {
	
    public ResourceLocation getModelLocation(EntityDoggyNpc entity) {
        return new ResourceLocation("piggymodtest", "geo/doggynpc.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityDoggyNpc entity) {
        if (entity.hasActivatedTexture()) {
            // The activated texture
            return new ResourceLocation("piggymodtest", "textures/entities/doggywithbone.png");
        }
        // Default texture
        return new ResourceLocation("piggymodtest", "textures/entities/doggynobone.png");
    }


    public ResourceLocation getAnimationFileLocation(EntityDoggyNpc entity) {
        return new ResourceLocation("piggymodtest", "animations/doggynpc.animation.json");
    }
}