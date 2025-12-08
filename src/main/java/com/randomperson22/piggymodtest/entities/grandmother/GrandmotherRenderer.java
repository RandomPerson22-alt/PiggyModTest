package com.randomperson22.piggymodtest.entities.grandmother;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GrandmotherRenderer extends GeoEntityRenderer<EntityGrandmother> {
    public GrandmotherRenderer(RenderManager renderManager) {
        super(renderManager, new GrandmotherModel());
        this.shadowSize = 0.5F;
    }
}
