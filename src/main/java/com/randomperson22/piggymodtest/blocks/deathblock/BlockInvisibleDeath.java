package com.randomperson22.piggymodtest.blocks.deathblock;

import com.randomperson22.piggymodtest.init.ModCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockInvisibleDeath extends Block {

	public BlockInvisibleDeath(String name) {
        super(Material.BARRIER); // Using BARRIER material for proper non-solid behavior
        
        this.setCreativeTab(ModCreativeTabs.PIGGY_TAB);
        this.setTranslationKey(name);
        this.setRegistryName(name);
        
        // Make completely non-solid and invisible
        this.setBlockUnbreakable();
        this.setLightOpacity(0);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
    
    public static final DamageSource DEATH_BLOCK_DAMAGE = new DamageSource("death_block")
    	    .setDamageBypassesArmor()
    	    .setDamageIsAbsolute();

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB; // Makes the block non-solid (walk-through)
    }

    @Override
    public void  onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
        if (!worldIn.isRemote && entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            
            // Don't kill creative mode players
            if (!player.capabilities.isCreativeMode) {
                player.attackEntityFrom(DamageSource.OUT_OF_WORLD, Float.MAX_VALUE);
            }
        }
    }
}