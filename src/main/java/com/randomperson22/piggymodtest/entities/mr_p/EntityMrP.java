package com.randomperson22.piggymodtest.entities.mr_p;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;
import com.randomperson22.piggymodtest.init.ModSounds;
import com.randomperson22.piggymodtest.projectiles.YellowOrb;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import java.util.Random;

public class EntityMrP extends EntityPiggyBase {
    private int reloadDelay = 0;
    private boolean isReloading = false;
    private final Random rand = new Random();

    public EntityMrP(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        // If reloading, count down
        if (isReloading) {
            if (reloadDelay > 0) {
                reloadDelay--;
            } else {
                // Time to shoot
                playAnimation("shoot");
                shootProjectile();
                isReloading = false;
            }
        } else {
            // 25% chance per "attack opportunity" to reload-shoot
            if (!this.world.isRemote && rand.nextFloat() < 0.0025f) { // small per-tick chance
                playAnimation("idle_reload");
                reloadDelay = 20; // 20 ticks = 1 second delay
                isReloading = true;
            }
        }
    }

    private void shootProjectile() {
        if (!world.isRemote) {
            YellowOrb orb = new YellowOrb(world, this);
            world.spawnEntity(orb);
        }
    }

    private void playAnimation(String anim) {

        this.setCurrentAnimation("shoot");

        System.out.println("Playing animation: " + anim);
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public SoundEvent getJumpscareSound() {
        return ModSounds.ENTITY_MR_P_JUMPSCARE;
    }

}
