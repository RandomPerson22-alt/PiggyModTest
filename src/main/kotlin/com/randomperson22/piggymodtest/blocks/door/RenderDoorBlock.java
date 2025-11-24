package com.randomperson22.piggymodtest.blocks.door;

import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class RenderDoorBlock extends GeoBlockRenderer<TileEntityDoor> {
    public RenderDoorBlock() {
        super(new ModelDoorBlock());
    }
}
