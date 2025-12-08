package com.randomperson22.piggymodtest.npc.pony;

import com.randomperson22.piggymodtest.npc.EntityNpc;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityPonyNpc extends EntityNpc {
    public EntityPonyNpc(World worldIn) {
        super(worldIn);
    }

    @Override
    protected Item getRequiredItem() {
        return Items.CARROT;
    } 
    
    @Override
    public void onUpdate() {
        super.onUpdate();
    }
}