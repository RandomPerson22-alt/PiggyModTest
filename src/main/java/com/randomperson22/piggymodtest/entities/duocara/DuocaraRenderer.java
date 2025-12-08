package com.randomperson22.piggymodtest.entities.duocara;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DuocaraRenderer extends GeoEntityRenderer<EntityDuocara> {
    public DuocaraRenderer(RenderManager renderManager) {
        super(renderManager, new DuocaraModel());
        this.shadowSize = 0.5F;
    }
}
