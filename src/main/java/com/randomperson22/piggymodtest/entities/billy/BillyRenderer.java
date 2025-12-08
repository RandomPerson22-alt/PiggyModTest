package com.randomperson22.piggymodtest.entities.billy;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BillyRenderer extends GeoEntityRenderer<EntityBilly> {
    public BillyRenderer(RenderManager renderManager) {
        super(renderManager, new BillyModel());
        this.shadowSize = 0.5F;
    }
}
