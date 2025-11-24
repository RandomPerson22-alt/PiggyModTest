package com.randomperson22.piggymodtest.entities.sheepy;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SheepyRenderer extends GeoEntityRenderer<EntitySheepy> {
    public SheepyRenderer(RenderManager renderManager) {
        super(renderManager, new SheepyModel());
        this.shadowSize = 0.5F;
    }
}