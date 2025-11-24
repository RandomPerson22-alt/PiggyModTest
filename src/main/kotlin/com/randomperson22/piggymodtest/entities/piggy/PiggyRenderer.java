package com.randomperson22.piggymodtest.entities.piggy;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PiggyRenderer extends GeoEntityRenderer<EntityPiggy> {
    public PiggyRenderer(RenderManager renderManager) {
        super(renderManager, new PiggyModel());
        this.shadowSize = 0.5F;
    }
}
