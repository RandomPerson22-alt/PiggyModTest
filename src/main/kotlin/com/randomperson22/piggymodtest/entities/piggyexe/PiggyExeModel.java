package com.randomperson22.piggymodtest.entities.piggyexe;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PiggyExeModel extends AnimatedGeoModel<EntityPiggyExe> {
	
    public ResourceLocation getModelLocation(EntityPiggyExe entity) {
        return new ResourceLocation("piggymodtest", "geo/piggyexe.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityPiggyExe entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/piggyexe.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityPiggyExe entity) {
        return new ResourceLocation("piggymodtest", "animations/piggyexe.animation.json");
    }
}
