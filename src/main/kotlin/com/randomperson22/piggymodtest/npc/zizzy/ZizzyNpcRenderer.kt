package com.randomperson22.piggymodtest.npc.zizzy

import net.minecraft.client.renderer.entity.RenderManager
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer

class ZizzyNpcRenderer(renderManager: RenderManager?) :
    GeoEntityRenderer<EntityZizzyNpc?>(renderManager, ZizzyNpcModel()) {
    init {
        this.shadowSize = 0.5f
        // Initialize your animation processor here if needed
    }
}