package com.randomperson22.piggymodtest.projectiles;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class YellowOrbModel extends AnimatedGeoModel<YellowOrb> {

    @Override
    public ResourceLocation getModelLocation(YellowOrb entity) {
        return new ResourceLocation("piggymodtest", "geo/yelloworb.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(YellowOrb entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/yelloworb.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(YellowOrb entity) {
        return new ResourceLocation("piggymodtest", "animations/yelloworb.animation.json");
    }
}
