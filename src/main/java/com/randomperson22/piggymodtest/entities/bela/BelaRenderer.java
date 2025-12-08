package com.randomperson22.piggymodtest.entities.bela;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BelaRenderer extends GeoEntityRenderer<EntityBela> {
    public BelaRenderer(RenderManager renderManager) {
        super(renderManager, new BelaModel());
        this.shadowSize = 0.5F;
    }
}
