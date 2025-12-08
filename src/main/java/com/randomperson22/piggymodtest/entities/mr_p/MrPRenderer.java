package com.randomperson22.piggymodtest.entities.mr_p;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MrPRenderer extends GeoEntityRenderer<EntityMrP> {
    public MrPRenderer(RenderManager renderManager) {
        super(renderManager, new MrPModel());
        this.shadowSize = 0.5F;
    }
}
