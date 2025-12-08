package com.randomperson22.piggymodtest.entities.umbra;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class UmbraRenderer extends GeoEntityRenderer<EntityUmbra> {
    public UmbraRenderer(RenderManager renderManager) {
        super(renderManager, new UmbraModel());
        this.shadowSize = 0.5F;
    }
}
