package com.randomperson22.piggymodtest.blocks.door;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderDoorBlockTESR extends TileEntitySpecialRenderer<TileEntityDoor> {
    private final RenderDoorBlock renderer = new RenderDoorBlock();

    @Override
    public void render(TileEntityDoor te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        // Delegate the actual rendering to the GeckoLib renderer
        renderer.render(te, x, y, z, partialTicks, destroyStage, alpha);
    }
}
