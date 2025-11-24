package com.randomperson22.piggymodtest.entities.piggy

import net.minecraft.client.renderer.entity.RenderManager
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer

class PiggyRenderer(renderManager: RenderManager?) : GeoEntityRenderer<EntityPiggy?>(renderManager, PiggyModel()) {
    init {
        this.shadowSize = 0.5f
    }
}
