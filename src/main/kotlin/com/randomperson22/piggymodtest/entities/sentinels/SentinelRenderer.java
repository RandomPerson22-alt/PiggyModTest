package com.randomperson22.piggymodtest.entities.sentinels;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SentinelRenderer extends GeoEntityRenderer<SentinelBase> {
    public SentinelRenderer(RenderManager renderManager) {
        super(renderManager, new SentinelModel());
        this.shadowSize = 0.5f;
    }
}
