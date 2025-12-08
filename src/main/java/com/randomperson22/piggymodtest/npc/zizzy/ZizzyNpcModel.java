package com.randomperson22.piggymodtest.npc.zizzy;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ZizzyNpcModel extends AnimatedGeoModel<EntityZizzyNpc> {
	
    public ResourceLocation getModelLocation(EntityZizzyNpc entity) {
        return new ResourceLocation("piggymodtest", "geo/zizzy.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityZizzyNpc entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/zizzynpc.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityZizzyNpc entity) {
        return new ResourceLocation("piggymodtest", "animations/zizzynpc.animation.json");
    }
}