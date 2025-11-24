package com.randomperson22.piggymodtest.entities.distorted_penny;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DistortedPennyRenderer extends GeoEntityRenderer<EntityDistortedPenny> {
    public DistortedPennyRenderer(RenderManager renderManager) {
        super(renderManager, new DistortedPennyModel());
        this.shadowSize = 0.5F;
    }
}
