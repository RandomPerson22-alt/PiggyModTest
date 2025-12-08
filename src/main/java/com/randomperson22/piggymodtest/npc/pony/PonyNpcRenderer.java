package com.randomperson22.piggymodtest.npc.pony;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PonyNpcRenderer extends GeoEntityRenderer<EntityPonyNpc> {
    public PonyNpcRenderer(RenderManager renderManager) {
        super(renderManager, new PonyNpcModel());
        this.shadowSize = 0.5f;
        // Initialize your animation processor here if needed
	}
}