package com.randomperson22.piggymodtest.blocks.deathblock;

import com.randomperson22.piggymodtest.init.ModBlocks;
import com.randomperson22.piggymodtest.init.ModCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class BlockInvisibleDeath extends Block {

	public BlockInvisibleDeath(String name) {
        super(Material.BARRIER); // Using BARRIER material for proper non-solid behavior
        
        this.setCreativeTab(ModCreativeTabs.PIGGY_TAB);
        this.setRegistryName(name);
        setTranslationKey(name);
        
        // Make completely non-solid and invisible
        this.setBlockUnbreakable();
        this.setLightOpacity(0);
    }

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
	    Minecraft mc = Minecraft.getMinecraft();
	    EntityPlayerSP player = mc.player;
	    if (player == null || mc.world == null) return;

	    ItemStack held = player.getHeldItemMainhand();

	    if (held.getItem() == Item.getItemFromBlock(ModBlocks.INVISIBLE_DEATH_BLOCK)) {
	        BlockPos playerPos = new BlockPos(player.posX, player.posY, player.posZ);
	        int radius = 8;

	        for (BlockPos pos : BlockPos.getAllInBox(playerPos.add(-radius, -radius, -radius),
	                                                 playerPos.add(radius, radius, radius))) {
	            if (mc.world.getBlockState(pos).getBlock() == ModBlocks.INVISIBLE_DEATH_BLOCK) {
	                mc.effectRenderer.addEffect(new ParticleDeathWarning(
	                        mc.world,
	                        pos.getX() + 0.5, pos.getY() + 1.2, pos.getZ() + 0.5
	                ));
	            }
	        }
	    }
	}

	
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
    
    @Override
    public PathNodeType getAiPathNodeType(IBlockState state, IBlockAccess world, BlockPos pos) {
        return PathNodeType.BLOCKED;
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

    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
        if (!worldIn.isRemote && entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            
            // Don't kill creative mode players
            if (!player.capabilities.isCreativeMode) {
                player.attackEntityFrom(DamageSource.OUT_OF_WORLD, Float.MAX_VALUE);
            }
        }
    }
}