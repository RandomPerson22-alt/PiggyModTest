package com.randomperson22.piggymodtest.blocks.default_trap;

import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class RenderDefaultTrap extends GeoBlockRenderer<TileEntityDefaultTrap> {
    public RenderDefaultTrap() {
        super(new ModelDefaultTrap());
    }
}
