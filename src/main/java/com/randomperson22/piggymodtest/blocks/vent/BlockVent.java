package com.randomperson22.piggymodtest.blocks.vent;

import com.randomperson22.piggymodtest.init.ModCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockVent extends Block {
    
    // Define the custom bounding box (6 pixels tall = 0.375 blocks)
    private static final AxisAlignedBB VENT_AABB = new AxisAlignedBB(
        0.0D, 0.0D, 0.0D,      // Min X, Y, Z
        1.0D, 0.25D, 1.0D      // Max X, Y, Z
    );
    
    public BlockVent(String name) {
        super(Material.IRON);
        setTranslationKey(name);
        setRegistryName(name);
        setHardness(1.5f);
        setResistance(10.0f);
        setHarvestLevel("pickaxe", 1);
        this.setCreativeTab(ModCreativeTabs.PIGGY_TAB);
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return VENT_AABB;
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return VENT_AABB;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
}