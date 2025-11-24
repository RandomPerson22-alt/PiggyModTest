package com.randomperson22.piggymodtest.entities.zizzy;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ZizzyRenderer extends GeoEntityRenderer<EntityZizzy> {
    public ZizzyRenderer(RenderManager renderManager) {
        super(renderManager, new ZizzyModel());
        this.shadowSize = 0.5F;
    }
}
