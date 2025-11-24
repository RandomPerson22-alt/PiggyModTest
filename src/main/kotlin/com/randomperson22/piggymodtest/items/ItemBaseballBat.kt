package com.randomperson22.piggymodtest.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBaseballBat extends Item {
    public ItemBaseballBat() {
        setMaxStackSize(1);
    }

    @Override
    public boolean hitEntity(ItemStack stack, net.minecraft.entity.EntityLivingBase target, net.minecraft.entity.EntityLivingBase attacker) {
        target.attackEntityFrom(net.minecraft.util.DamageSource.causePlayerDamage((EntityPlayer) attacker), 5.0F);
        return true;
    }
}
