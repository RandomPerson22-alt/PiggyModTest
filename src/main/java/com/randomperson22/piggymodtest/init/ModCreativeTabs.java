package com.randomperson22.piggymodtest.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;

public class ModCreativeTabs {
    public static final CreativeTabs PIGGY_TAB = new CreativeTabs("piggy_tab") {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.BASEBALL_BAT);
        }

        @Override
        public void displayAllRelevantItems(NonNullList<ItemStack> items) {
            super.displayAllRelevantItems(items);

            items.add(makeSpawnEgg("piggymodtest:piggy"));
            items.add(makeSpawnEgg("piggymodtest:georgie"));
            items.add(makeSpawnEgg("piggymodtest:mother"));
            items.add(makeSpawnEgg("piggymodtest:father"));
            items.add(makeSpawnEgg("piggymodtest:grandmother"));
            items.add(makeSpawnEgg("piggymodtest:sheepy"));
            items.add(makeSpawnEgg("piggymodtest:teacher"));
            items.add(makeSpawnEgg("piggymodtest:owell"));
            items.add(makeSpawnEgg("piggymodtest:kona"));
            items.add(makeSpawnEgg("piggymodtest:pandy"));
            items.add(makeSpawnEgg("piggymodtest:memory"));
            items.add(makeSpawnEgg("piggymodtest:kitty"));
            items.add(makeSpawnEgg("piggymodtest:mimi"));
            items.add(makeSpawnEgg("piggymodtest:doggy"));
            items.add(makeSpawnEgg("piggymodtest:dinopiggy"));
            items.add(makeSpawnEgg("piggymodtest:test"));
            items.add(makeSpawnEgg("piggymodtest:umbra"));
            items.add(makeSpawnEgg("piggymodtest:zizzynpc"));
            items.add(makeSpawnEgg("piggymodtest:zizzy"));       
            items.add(makeSpawnEgg("piggymodtest:zompiggy"));  
            items.add(makeSpawnEgg("piggymodtest:mr_stitchy"));
            items.add(makeSpawnEgg("piggymodtest:sentinel_red"));
            items.add(makeSpawnEgg("piggymodtest:sentinel_green"));  
            items.add(makeSpawnEgg("piggymodtest:sentinel_light_blue"));  
            items.add(makeSpawnEgg("piggymodtest:sentinel_pink"));  
            items.add(makeSpawnEgg("piggymodtest:sentinel_shadow"));  
            items.add(makeSpawnEgg("piggymodtest:sentinel_white"));  
            items.add(makeSpawnEgg("piggymodtest:robby"));  
            items.add(makeSpawnEgg("piggymodtest:duocara"));  
            items.add(makeSpawnEgg("piggymodtest:mr_p"));
            items.add(makeSpawnEgg("piggymodtest:billy"));
            items.add(makeSpawnEgg("piggymodtest:reindessa"));
            items.add(makeSpawnEgg("piggymodtest:bela"));
            items.add(makeSpawnEgg("piggymodtest:frostiggy"));
            items.add(makeSpawnEgg("piggymodtest:glitch_memory"));
            items.add(makeSpawnEgg("piggymodtest:distorted_penny"));
            items.add(makeSpawnEgg("piggymodtest:piggyexe"));
            items.add(makeSpawnEgg("piggymodtest:georgienpc"));
            items.add(makeSpawnEgg("piggymodtest:doggynpc"));
            items.add(makeSpawnEgg("piggymodtest:ponynpc"));
            items.add(makeSpawnEgg("piggymodtest:badgy"));
            items.add(makeSpawnEgg("piggymodtest:kamosi"));
        }

        private ItemStack makeSpawnEgg(String entityId) {
            ItemStack egg = new ItemStack(Items.SPAWN_EGG);
            NBTTagCompound entityTag = new NBTTagCompound();
            entityTag.setString("id", entityId);

            NBTTagCompound tag = new NBTTagCompound();
            tag.setTag("EntityTag", entityTag);
            egg.setTagCompound(tag);

            return egg;
        }
    };
}