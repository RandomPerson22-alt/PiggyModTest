package com.randomperson22.piggymodtest.entities.teacher;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class TeacherRenderer extends GeoEntityRenderer<EntityTeacher> {
    public TeacherRenderer(RenderManager renderManager) {
        super(renderManager, new TeacherModel());
        this.shadowSize = 0.5F;
    }
}
