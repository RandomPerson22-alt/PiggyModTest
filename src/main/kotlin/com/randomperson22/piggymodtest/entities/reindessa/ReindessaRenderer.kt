package com.randomperson22.piggymodtest.entities.reindessa

import net.minecraft.client.renderer.entity.RenderManager
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer

class ReindessaRenderer(renderManager: RenderManager?) :
    GeoEntityRenderer<EntityReindessa?>(renderManager, ReindessaModel()) {
    init {
        this.shadowSize = 0.5f
    }
}
