package com.randomperson22.piggymodtest.entities.frostiggy;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FrostiggyRenderer extends GeoEntityRenderer<EntityFrostiggy> {
    public FrostiggyRenderer(RenderManager renderManager) {
        super(renderManager, new FrostiggyModel());
        this.shadowSize = 0.5F;
    }
}
