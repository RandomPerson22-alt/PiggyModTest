package com.randomperson22.piggymodtest.blocks.door;

import com.randomperson22.piggymodtest.init.ModCreativeTabs;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDoorBlock extends BlockContainer {
    public BlockDoorBlock(String name) {
        super(Material.WOOD);
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
        return new TileEntityDoor();
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        // Convert pixel coordinates from your model to block units (1/16)
        // Main door panel: x(0-14), y(0-30), z(7-9)
        // Frame adds 2 pixels on each side (x:-2 to 16)
        return new AxisAlignedBB(
            0.0,      // minX (0/16)
            0.0,      // minY (0/16)
            7/16.0,   // minZ (7/16)
            14/16.0,  // maxX (14/16)
            30/16.0,  // maxY (30/16)
            9/16.0    // maxZ (9/16)
        );
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        // For collision, include the frame (whole block width)
        return new AxisAlignedBB(
            0.0,      // minX
            0.0,      // minY
            6/16.0,   // minZ (frame starts at 6)
            1.0,      // maxX (full block width)
            30/16.0,  // maxY
            10/16.0   // maxZ (frame ends at 10)
        );
    }
    
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state,
                                    EntityPlayer player, EnumHand hand, EnumFacing side,
                                    float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileEntityDoor tile = (TileEntityDoor) world.getTileEntity(pos);
            if (tile != null) {
                tile.setOpen(!tile.isOpen());  // toggle open state on server
            }
        } else {
            // ALSO trigger on client, for GeckoLib animation
            TileEntityDoor tile = (TileEntityDoor) world.getTileEntity(pos);
            if (tile != null) {
                tile.setOpen(true);  // Force animation to play on client
            }
        }
        return true;
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World world, BlockPos pos) {
        return getBoundingBox(state, world, pos).offset(pos);
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
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
