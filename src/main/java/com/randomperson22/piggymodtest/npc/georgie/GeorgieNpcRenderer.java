package com.randomperson22.piggymodtest.npc.georgie;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GeorgieNpcRenderer extends GeoEntityRenderer<EntityGeorgieNpc> {
    public GeorgieNpcRenderer(RenderManager renderManager) {
        super(renderManager, new GeorgieNpcModel());
        this.shadowSize = 0.5f;
        // Initialize your animation processor here if needed
	}
}