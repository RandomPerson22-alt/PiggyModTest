package com.randomperson22.piggymodtest.entities.teacher

import net.minecraft.util.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

class TeacherModel : AnimatedGeoModel<EntityTeacher?>() {
    override fun getModelLocation(entity: EntityTeacher?): ResourceLocation {
        return ResourceLocation("piggymodtest", "geo/teacher.geo.json")
    }

    override fun getTextureLocation(entity: EntityTeacher?): ResourceLocation {
        return ResourceLocation("piggymodtest", "textures/entities/teacher.png")
    }

    override fun getAnimationFileLocation(entity: EntityTeacher?): ResourceLocation {
        return ResourceLocation("piggymodtest", "animations/piggy.animation.json")
    }
}
