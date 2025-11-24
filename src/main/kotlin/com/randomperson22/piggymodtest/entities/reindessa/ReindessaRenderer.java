package com.randomperson22.piggymodtest.entities.reindessa;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ReindessaRenderer extends GeoEntityRenderer<EntityReindessa> {
    public ReindessaRenderer(RenderManager renderManager) {
        super(renderManager, new ReindessaModel());
        this.shadowSize = 0.5F;
    }
}
