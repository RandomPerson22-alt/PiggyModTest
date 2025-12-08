package com.randomperson22.piggymodtest.entities.zompiggy;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ZompiggyRenderer extends GeoEntityRenderer<EntityZompiggy> {
    public ZompiggyRenderer(RenderManager renderManager) {
        super(renderManager, new ZompiggyModel());
        this.shadowSize = 0.5F;
    }
}
