package com.randomperson22.piggymodtest.entities.memory;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MemoryRenderer extends GeoEntityRenderer<EntityMemory> {
    public MemoryRenderer(RenderManager renderManager) {
        super(renderManager, new MemoryModel());
        this.shadowSize = 0.5F;
    }
}
