package com.randomperson22.piggymodtest.entities.kitty;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class KittyRenderer extends GeoEntityRenderer<EntityKitty> {
    public KittyRenderer(RenderManager renderManager) {
        super(renderManager, new KittyModel());
        this.shadowSize = 0.5F;
    }
}
