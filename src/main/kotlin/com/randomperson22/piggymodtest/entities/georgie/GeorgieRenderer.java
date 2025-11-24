package com.randomperson22.piggymodtest.entities.georgie;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GeorgieRenderer extends GeoEntityRenderer<EntityGeorgie> {
    public GeorgieRenderer(RenderManager renderManager) {
        super(renderManager, new GeorgieModel());
        this.shadowSize = 0.5F;
    }
}
