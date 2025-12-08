package com.randomperson22.piggymodtest.npc.doggy;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DoggyNpcRenderer extends GeoEntityRenderer<EntityDoggyNpc> {
    public DoggyNpcRenderer(RenderManager renderManager) {
        super(renderManager, new DoggyNpcModel());
        this.shadowSize = 0.5f;
        // Initialize your animation processor here if needed
	}
}