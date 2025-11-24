package com.randomperson22.piggymodtest.entities.glitch_memory;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GlitchMemoryModel extends AnimatedGeoModel<EntityGlitchMemory> {
	
    public ResourceLocation getModelLocation(EntityGlitchMemory entity) {
        return new ResourceLocation("piggymodtest", "geo/glitch_memory.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityGlitchMemory entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/glitch_memory.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityGlitchMemory entity) {
        return new ResourceLocation("piggymodtest", "animations/memory.animation.json");
    }
}
