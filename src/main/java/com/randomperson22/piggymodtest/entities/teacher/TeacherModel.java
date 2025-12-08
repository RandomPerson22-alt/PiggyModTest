package com.randomperson22.piggymodtest.entities.teacher;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TeacherModel extends AnimatedGeoModel<EntityTeacher> {
	
    public ResourceLocation getModelLocation(EntityTeacher entity) {
        return new ResourceLocation("piggymodtest", "geo/teacher.geo.json");
    }

    public ResourceLocation getTextureLocation(EntityTeacher entity) {
        return new ResourceLocation("piggymodtest", "textures/entities/teacher.png");
    }

    public ResourceLocation getAnimationFileLocation(EntityTeacher entity) {
        return new ResourceLocation("piggymodtest", "animations/piggy.animation.json");
    }
}
