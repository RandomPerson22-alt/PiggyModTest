package com.randomperson22.piggymodtest.entities.piggy;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PiggyRenderer extends GeoEntityRenderer<EntityPiggy> {

    public PiggyRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PiggyModel());
        this.shadowRadius = 0.5f; // renamed from shadowSize
    }

    @SuppressWarnings("removal")
	@Override
    public ResourceLocation getTextureLocation(EntityPiggy entity) {
        return new ResourceLocation("piggymodtest", "textures/entity/piggy.png");
    }
}