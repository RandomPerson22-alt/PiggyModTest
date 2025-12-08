package com.randomperson22.piggymodtest.entities.kamosi;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class KamosiRenderer extends GeoEntityRenderer<EntityKamosi> {
    public KamosiRenderer(RenderManager renderManager) {
        super(renderManager, new KamosiModel());
        this.shadowSize = 0.5F;
    }
}
