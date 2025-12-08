package com.randomperson22.piggymodtest.npc.zizzy;

import com.randomperson22.piggymodtest.npc.EntityNpc;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityZizzyNpc extends EntityNpc {
    public EntityZizzyNpc(World worldIn) {
        super(worldIn);
    }

    @Override
    protected Item getRequiredItem() {
        return null;
    }

    @Override
    protected boolean isCorrectItem(ItemStack stack) {
        return !stack.isEmpty()
            && stack.getItem() == Item.getItemFromBlock(Blocks.TALLGRASS)
            && stack.getMetadata() == 1;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }
}