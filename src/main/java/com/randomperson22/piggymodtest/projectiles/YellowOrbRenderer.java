package com.randomperson22.piggymodtest.projectiles;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class YellowOrbRenderer extends GeoProjectilesRenderer<YellowOrb> {
    public YellowOrbRenderer(RenderManager renderManager) {
        super(renderManager, new YellowOrbModel());
        this.shadowSize = 0.25F; // smaller shadow for projectile
    }
}
