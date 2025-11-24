package com.randomperson22.piggymodtest.blocks.door;

import com.randomperson22.piggymodtest.PiggyModTest;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelDoorBlock extends AnimatedGeoModel<TileEntityDoor> {
    @Override
    public ResourceLocation getModelLocation(TileEntityDoor object) {
        return new ResourceLocation(PiggyModTest.MODID, "geo/door.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TileEntityDoor object) {
        return new ResourceLocation(PiggyModTest.MODID, "textures/blocks/door.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TileEntityDoor animatable) {
        return new ResourceLocation(PiggyModTest.MODID, "animations/door.animation.json");
    }
}
