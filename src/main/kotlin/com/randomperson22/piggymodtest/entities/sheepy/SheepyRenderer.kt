package com.randomperson22.piggymodtest.entities.sheepy

import net.minecraft.client.renderer.entity.RenderManager
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer

class SheepyRenderer(renderManager: RenderManager?) : GeoEntityRenderer<EntitySheepy?>(renderManager, SheepyModel()) {
    init {
        this.shadowSize = 0.5f
    }
}