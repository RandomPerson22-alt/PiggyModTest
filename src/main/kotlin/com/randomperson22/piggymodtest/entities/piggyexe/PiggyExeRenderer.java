package com.randomperson22.piggymodtest.entities.piggyexe;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PiggyExeRenderer extends GeoEntityRenderer<EntityPiggyExe> {
    public PiggyExeRenderer(RenderManager renderManager) {
        super(renderManager, new PiggyExeModel());
        this.shadowSize = 0.5F;
    }
}
