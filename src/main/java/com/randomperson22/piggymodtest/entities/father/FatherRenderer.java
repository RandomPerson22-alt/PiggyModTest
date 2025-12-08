package com.randomperson22.piggymodtest.entities.father;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FatherRenderer extends GeoEntityRenderer<EntityFather> {
    public FatherRenderer(RenderManager renderManager) {
        super(renderManager, new FatherModel());
        this.shadowSize = 0.5F;
    }
}
