package com.randomperson22.piggymodtest.npc.zizzy;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ZizzyNpcRenderer extends GeoEntityRenderer<EntityZizzyNpc> {
    public ZizzyNpcRenderer(RenderManager renderManager) {
        super(renderManager, new ZizzyNpcModel());
        this.shadowSize = 0.5f;
        // Initialize your animation processor here if needed
	}
}