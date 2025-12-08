package com.randomperson22.piggymodtest.entities.robby;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RobbyRenderer extends GeoEntityRenderer<EntityRobby> {
    public RobbyRenderer(RenderManager renderManager) {
        super(renderManager, new RobbyModel());
        this.shadowSize = 0.5F;
    }
}
