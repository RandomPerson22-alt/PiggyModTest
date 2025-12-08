package com.randomperson22.piggymodtest.blocks.default_trap;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderDefaultTrapTESR extends TileEntitySpecialRenderer<TileEntityDefaultTrap> {
    private final RenderDefaultTrap renderer = new RenderDefaultTrap();

    @Override
    public void render(TileEntityDefaultTrap te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        // Delegate the rendering to GeckoLib's renderer
        renderer.render(te, x, y, z, partialTicks, destroyStage, alpha);
    }
}
