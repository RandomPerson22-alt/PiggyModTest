package com.randomperson22.piggymodtest.entities.sentinels

import net.minecraft.client.renderer.entity.RenderManager
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer

class SentinelRenderer(renderManager: RenderManager?) :
    GeoEntityRenderer<SentinelBase?>(renderManager, SentinelModel()) {
    init {
        this.shadowSize = 0.5f
    }
}
