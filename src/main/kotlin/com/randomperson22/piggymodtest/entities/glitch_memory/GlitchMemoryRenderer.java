package com.randomperson22.piggymodtest.entities.glitch_memory;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GlitchMemoryRenderer extends GeoEntityRenderer<EntityGlitchMemory> {
    public GlitchMemoryRenderer(RenderManager renderManager) {
        super(renderManager, new GlitchMemoryModel());
        this.shadowSize = 0.5F;
    }
}
