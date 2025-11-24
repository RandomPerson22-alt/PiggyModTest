package com.randomperson22.piggymodtest.entities.umbra

import com.randomperson22.piggymodtest.entities.EntityPiggyBase
import com.randomperson22.piggymodtest.init.ModSounds
import net.minecraft.entity.Entity
import net.minecraft.util.SoundEvent
import net.minecraft.world.World
import kotlin.math.cos
import kotlin.math.sin

class EntityUmbra(worldIn: World) : EntityPiggyBase(worldIn) {
    init {
        this.setSize(0.5f, 0.5f)
    }

    public override fun updatePassenger(passenger: Entity) {
        if (this.isPassenger(passenger)) {
            // Offsets relative to Piggy's rotation
            val xOffset = 0.0f // Side-to-side (0 = centered)
            val yOffset = -1.0f // Height (1.2 blocks above Piggy)
            val zOffset = 1.0f // 1.5 blocks IN FRONT of Piggy

            // Convert Piggy's rotation to radians
            val yawRad = this.rotationYaw * (Math.PI / 180.0f).toFloat()

            // Calculate rotated offsets (relative to Piggy's facing direction)
            val rotatedX = -sin(yawRad.toDouble()) * zOffset
            val rotatedZ = cos(yawRad.toDouble()) * zOffset

            // Set player position
            passenger.setPosition(
                this.posX + rotatedX + xOffset,
                this.posY + yOffset,
                this.posZ + rotatedZ
            )
        }
    }

    override val priority: Int
        get() = 1

    override val jumpscareSound: SoundEvent
        get() = ModSounds.ENTITY_PIGGY_JUMPSCARE

    companion object {
        const val JUMPSCARE_DURATION: Int = 10
    }
}