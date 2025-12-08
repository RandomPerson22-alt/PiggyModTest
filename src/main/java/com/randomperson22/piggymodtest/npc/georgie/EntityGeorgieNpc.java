package com.randomperson22.piggymodtest.npc.georgie;

import com.randomperson22.piggymodtest.npc.EntityNpc;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityGeorgieNpc extends EntityNpc {
    public EntityGeorgieNpc(World worldIn) {
        super(worldIn);
    }

    @Override
    protected Item getRequiredItem() {
        return Items.APPLE;
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
    }
}