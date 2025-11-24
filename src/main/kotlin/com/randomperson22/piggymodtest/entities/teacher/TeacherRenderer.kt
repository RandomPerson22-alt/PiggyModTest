package com.randomperson22.piggymodtest.entities.teacher

import net.minecraft.client.renderer.entity.RenderManager
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer

class TeacherRenderer(renderManager: RenderManager?) :
    GeoEntityRenderer<EntityTeacher?>(renderManager, TeacherModel()) {
    init {
        this.shadowSize = 0.5f
    }
}
