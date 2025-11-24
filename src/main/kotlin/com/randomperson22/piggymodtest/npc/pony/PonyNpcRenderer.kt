package com.randomperson22.piggymodtest.npc.pony

import net.minecraft.client.renderer.entity.RenderManager
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer

class PonyNpcRenderer(renderManager: RenderManager?) :
    GeoEntityRenderer<EntityPonyNpc?>(renderManager, PonyNpcModel()) {
    init {
        this.shadowSize = 0.5f
        // Initialize your animation processor here if needed
    }
}