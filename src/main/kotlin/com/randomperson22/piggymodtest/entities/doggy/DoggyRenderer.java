package com.randomperson22.piggymodtest.entities.doggy;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DoggyRenderer extends GeoEntityRenderer<EntityDoggy> {
    public DoggyRenderer(RenderManager renderManager) {
        super(renderManager, new DoggyModel());
        this.shadowSize = 0.5F;
    }
}
