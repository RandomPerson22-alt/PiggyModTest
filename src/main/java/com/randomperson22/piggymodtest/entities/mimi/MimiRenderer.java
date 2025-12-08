package com.randomperson22.piggymodtest.entities.mimi;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MimiRenderer extends GeoEntityRenderer<EntityMimi> {
    public MimiRenderer(RenderManager renderManager) {
        super(renderManager, new MimiModel());
        this.shadowSize = 0.5F;
    }
}
