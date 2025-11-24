package com.randomperson22.piggymodtest.init;

import com.randomperson22.piggymodtest.PiggyModTest;
import com.randomperson22.piggymodtest.entities.piggy.EntityPiggy;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PiggyModTest.MODID);

    public static final RegistryObject<EntityType<EntityPiggy>> ENTITY_PIGGY =
            ENTITIES.register("entity_piggy",
                    () -> EntityType.Builder.of(EntityPiggy::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.8f)
                            .build("entity_piggy"));
}