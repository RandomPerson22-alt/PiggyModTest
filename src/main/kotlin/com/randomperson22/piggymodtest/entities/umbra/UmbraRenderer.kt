package com.randomperson22.piggymodtest.entities.umbra

import net.minecraft.client.renderer.entity.RenderManager
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer

class UmbraRenderer(renderManager: RenderManager?) : GeoEntityRenderer<EntityUmbra?>(renderManager, UmbraModel()) {
    init {
        this.shadowSize = 0.5f
    }
}
