package com.randomperson22.piggymodtest.entities.memory;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MemoryModel extends AnimatedGeoModel<EntityMemory> {
	
    public ResourceLocation getModelLocation(EntityMemory entity) {
        return new ResourceLocation("piggymodtest", "geo/memory.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityMemory entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/memory.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityMemory entity) {
        return new ResourceLocation("piggymodtest", "animations/memory.animation.json");
    }
}
