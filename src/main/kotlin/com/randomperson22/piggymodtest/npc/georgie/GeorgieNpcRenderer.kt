package com.randomperson22.piggymodtest.npc.georgie

import net.minecraft.client.renderer.entity.RenderManager
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer

class GeorgieNpcRenderer(renderManager: RenderManager?) :
    GeoEntityRenderer<EntityGeorgieNpc?>(renderManager, GeorgieNpcModel()) {
    init {
        this.shadowSize = 0.5f
        // Initialize your animation processor here if needed
    }
}