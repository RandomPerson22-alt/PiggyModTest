package com.randomperson22.piggymodtest.entities.mother;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MotherRenderer extends GeoEntityRenderer<EntityMother> {
    public MotherRenderer(RenderManager renderManager) {
        super(renderManager, new MotherModel());
        this.shadowSize = 0.5F;
    }
}
