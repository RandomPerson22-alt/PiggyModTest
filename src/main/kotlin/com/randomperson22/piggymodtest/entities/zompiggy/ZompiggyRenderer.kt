package com.randomperson22.piggymodtest.entities.zompiggy

import net.minecraft.client.renderer.entity.RenderManager
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer

class ZompiggyRenderer(renderManager: RenderManager?) :
    GeoEntityRenderer<EntityZompiggy?>(renderManager, ZompiggyModel()) {
    init {
        this.shadowSize = 0.5f
    }
}
