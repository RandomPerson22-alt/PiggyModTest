package com.randomperson22.piggymodtest.entities.badgy;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BadgyRenderer extends GeoEntityRenderer<EntityBadgy> {
    public BadgyRenderer(RenderManager renderManager) {
        super(renderManager, new BadgyModel());
        this.shadowSize = 0.5F;
    }
}