package com.randomperson22.piggymodtest.blocks.default_trap;

import com.randomperson22.piggymodtest.PiggyModTest;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelDefaultTrap extends AnimatedGeoModel<TileEntityDefaultTrap> {
    @Override
    public ResourceLocation getModelLocation(TileEntityDefaultTrap object) {
        return new ResourceLocation(PiggyModTest.MODID, "geo/default_trap.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TileEntityDefaultTrap object) {
        return new ResourceLocation(PiggyModTest.MODID, "textures/blocks/defaulttrap.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TileEntityDefaultTrap animatable) {
        return new ResourceLocation(PiggyModTest.MODID, "animations/default_trap.animation.json");
    }
}
