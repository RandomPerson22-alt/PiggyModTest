package com.randomperson22.piggymodtest.entities.dinopiggy;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DinopiggyRenderer extends GeoEntityRenderer<EntityDinopiggy> {
    public DinopiggyRenderer(RenderManager renderManager) {
        super(renderManager, new DinopiggyModel());
        this.shadowSize = 0.5F;
    }
}
