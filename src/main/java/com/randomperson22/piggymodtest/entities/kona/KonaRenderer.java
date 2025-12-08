package com.randomperson22.piggymodtest.entities.kona;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class KonaRenderer extends GeoEntityRenderer<EntityKona> {
    public KonaRenderer(RenderManager renderManager) {
        super(renderManager, new KonaModel());
        this.shadowSize = 0.5F;
    }
}
