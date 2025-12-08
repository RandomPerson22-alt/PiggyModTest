package com.randomperson22.piggymodtest.blocks.default_trap;

import com.randomperson22.piggymodtest.init.ModCreativeTabs;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDefaultTrap extends BlockContainer {
    public BlockDefaultTrap(String name) {
        super(Material.IRON);
        this.setLightOpacity(0); // Prevent light passing through
        this.useNeighborBrightness = false;
        this.setCreativeTab(ModCreativeTabs.PIGGY_TAB);
        setTranslationKey(name);
        setRegistryName(name);
        setHardness(2.0f);
        setResistance(10.0f);
        setHarvestLevel("pickaxe", 0);
    }
    
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityDefaultTrap();
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

 // Trap hitbox based on Blockbench model (-8 to +8 in X/Z, height 2 units)
    private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(
         0.0D, 0.0D,  0.0D,   // minX, minY, minZ
         1.0D, 0.3D,  1.0D  // maxX, maxY, maxZ
    );

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDING_BOX;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB; // walk-through
    }

    
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT; // For transparent parts
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return true; // Force all sides to render
    }
    
}
