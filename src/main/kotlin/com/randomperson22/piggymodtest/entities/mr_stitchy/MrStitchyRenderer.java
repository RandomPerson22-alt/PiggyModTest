package com.randomperson22.piggymodtest.entities.mr_stitchy;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MrStitchyRenderer extends GeoEntityRenderer<EntityMrStitchy> {
    public MrStitchyRenderer(RenderManager renderManager) {
        super(renderManager, new MrStitchyModel());
        this.shadowSize = 0.5F;
    }
}
