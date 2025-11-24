package com.randomperson22.piggymodtest.entities.test;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class TestRenderer extends GeoEntityRenderer<EntityTest> {
    public TestRenderer(RenderManager renderManager) {
        super(renderManager, new TestModel());
        this.shadowSize = 0.5F;
    }
}
