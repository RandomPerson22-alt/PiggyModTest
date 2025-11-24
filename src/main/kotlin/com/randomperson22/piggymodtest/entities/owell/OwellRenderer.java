package com.randomperson22.piggymodtest.entities.owell;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class OwellRenderer extends GeoEntityRenderer<EntityOwell> {
    public OwellRenderer(RenderManager renderManager) {
        super(renderManager, new OwellModel());
        this.shadowSize = 0.5F;
    }
}
