package com.randomperson22.piggymodtest.entities.zizzy

import net.minecraft.client.renderer.entity.RenderManager
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer

class ZizzyRenderer(renderManager: RenderManager?) : GeoEntityRenderer<EntityZizzy?>(renderManager, ZizzyModel()) {
    init {
        this.shadowSize = 0.5f
    }
}
