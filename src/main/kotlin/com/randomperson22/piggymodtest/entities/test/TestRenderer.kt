package com.randomperson22.piggymodtest.entities.test

import net.minecraft.client.renderer.entity.RenderManager
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer

class TestRenderer(renderManager: RenderManager?) : GeoEntityRenderer<EntityTest?>(renderManager, TestModel()) {
    init {
        this.shadowSize = 0.5f
    }
}
