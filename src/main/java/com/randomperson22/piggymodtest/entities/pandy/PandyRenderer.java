package com.randomperson22.piggymodtest.entities.pandy;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PandyRenderer extends GeoEntityRenderer<EntityPandy> {
    public PandyRenderer(RenderManager renderManager) {
        super(renderManager, new PandyModel());
        this.shadowSize = 0.5F;
    }
}
