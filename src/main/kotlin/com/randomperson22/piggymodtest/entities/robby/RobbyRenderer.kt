package com.randomperson22.piggymodtest.entities.robby

import net.minecraft.client.renderer.entity.RenderManager
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer

class RobbyRenderer(renderManager: RenderManager?) : GeoEntityRenderer<EntityRobby?>(renderManager, RobbyModel()) {
    init {
        this.shadowSize = 0.5f
    }
}
