package com.randomperson22.piggymodtest.entities.piggy;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class PiggyRenderer extends GeoEntityRenderer<EntityPiggy> {
    public PiggyRenderer(RenderManager renderManager) {
        super(renderManager, new PiggyModel());
        this.shadowSize = 0.5F;
    }
}
