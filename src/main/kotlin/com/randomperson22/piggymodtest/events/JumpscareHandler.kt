package com.randomperson22.piggymodtest.events

import com.randomperson22.piggymodtest.init.ModNetwork
import com.randomperson22.piggymodtest.interfaces.ICustomEyeHeight
import com.randomperson22.piggymodtest.network.PacketJumpscare
import net.minecraft.client.Minecraft
import net.minecraft.client.entity.EntityPlayerSP
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.util.math.Vec3d
import net.minecraftforge.client.event.InputUpdateEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.apache.logging.log4j.LogManager
import java.lang.reflect.Field
import java.util.*
import kotlin.math.atan2
import kotlin.math.sqrt

@SideOnly(Side.CLIENT)
object JumpscareHandler {
    private val LOGGER = LogManager.getLogger("PiggyMod/Jumpscare")
    private val jumpscareTimers = WeakHashMap<EntityPlayer, Int>()
    private val faceTargets = WeakHashMap<EntityPlayer, Vec3d>()
    private val previousViewModes = WeakHashMap<EntityPlayer, Int>()

    fun init() {
        MinecraftForge.EVENT_BUS.register(this)
        LOGGER.info("Jumpscare handler initialized")
    }

    @SubscribeEvent
    fun onClientTick(event: TickEvent.ClientTickEvent) {
        if (event.phase != TickEvent.Phase.END) return

        val mc = Minecraft.getMinecraft()
        val player = mc.player ?: return

        jumpscareTimers[player]?.let { timer ->
            LOGGER.debug("Jumpscare timer: $timer ticks remaining for player ${player.name}")

            if (!previousViewModes.containsKey(player)) {
                val currentView = mc.gameSettings.thirdPersonView
                previousViewModes[player] = currentView
                if (currentView != 0) {
                    mc.gameSettings.thirdPersonView = 0
                    LOGGER.debug("Forced first-person view for player ${player.name}")
                }
            }

            faceTargets[player]?.let { target ->
                forceCameraLook(player, target)
            }

            if (timer <= 0) {
                LOGGER.debug("Jumpscare ended for player ${player.name}")
                jumpscareTimers.remove(player)
                faceTargets.remove(player)
                previousViewModes[player]?.let { oldView ->
                    mc.gameSettings.thirdPersonView = oldView
                    previousViewModes.remove(player)
                    LOGGER.debug("Restored camera view for player ${player.name}")
                }
            } else {
                jumpscareTimers[player] = timer - 1
            }
        }
    }

    fun triggerJumpscareAuto(entity: EntityLivingBase, target: EntityLivingBase) {
        LOGGER.debug(
            "triggerJumpscareAuto called - entity: {}, target: {}, world remote: {}",
            entity,
            target,
            entity.world.isRemote
        )

        if (entity.world.isRemote) {
            if (target is EntityPlayerSP) {
                triggerJumpscareClient(entity, target, 30)
            }
        } else {
            if (target is EntityPlayerMP) {
                triggerJumpscareServer(entity, target)
            }
        }
    }

    fun triggerJumpscareServer(entity: EntityLivingBase, targetPlayer: EntityPlayerMP) {
        LOGGER.debug("triggerJumpscareServer called - entity: {}, targetPlayer: {}", entity, targetPlayer)

        if (entity == null || targetPlayer == null) {
            LOGGER.warn("Jumpscare failed: entity or targetPlayer is null")
            return
        }

        var duration = 30
        try {
            val durationField: Field = entity.javaClass.getDeclaredField("JUMPSCARE_DURATION")
            durationField.isAccessible = true
            duration = durationField.getInt(null)
            LOGGER.debug("Found custom jumpscare duration: $duration ticks")
        } catch (e: Exception) {
            LOGGER.debug("Using default jumpscare duration (30 ticks) for ${entity.javaClass.simpleName}")
        }

        ModNetwork.INSTANCE.sendTo(PacketJumpscare(entity.entityId, duration), targetPlayer)
        LOGGER.debug("Sent jumpscare packet to player ${targetPlayer.name}")
    }

    fun triggerJumpscareClient(entity: EntityLivingBase, player: EntityPlayerSP, duration: Int) {
        LOGGER.debug(
            "triggerJumpscareClient called - entity: {}, player: {}, duration: {}",
            entity,
            player.name,
            duration
        )

        jumpscareTimers[player] = duration

        val eyeHeight = if (entity is ICustomEyeHeight) entity.customEyeHeight else entity.eyeHeight
        faceTargets[player] = Vec3d(entity.posX, entity.posY + eyeHeight, entity.posZ)
    }

    private fun forceCameraLook(player: EntityPlayerSP, target: Vec3d) {
        val lookVec = target.subtract(player.getPositionEyes(1.0f))
        val dist = sqrt(lookVec.x * lookVec.x + lookVec.z * lookVec.z)
        val yaw = Math.toDegrees(atan2(lookVec.z, lookVec.x)) - 90.0
        val pitch = -Math.toDegrees(atan2(lookVec.y, dist))

        player.rotationYaw = yaw.toFloat()
        player.prevRotationYaw = yaw.toFloat()
        player.renderYawOffset = yaw.toFloat()
        player.prevRenderYawOffset = yaw.toFloat()
        player.rotationYawHead = yaw.toFloat()
        player.prevRotationYawHead = yaw.toFloat()
        player.rotationPitch = pitch.toFloat()
        player.setPositionAndRotation(player.posX, player.posY, player.posZ, yaw.toFloat(), pitch.toFloat())

        LOGGER.debug("Forced camera look - Yaw: ${yaw}, Pitch: ${pitch}")
    }

    @SubscribeEvent
    fun onInputUpdate(event: InputUpdateEvent) {
        if (jumpscareTimers.containsKey(event.entityPlayer)) {
            event.movementInput.moveForward = 0f
            event.movementInput.moveStrafe = 0f
            event.movementInput.jump = false
            event.movementInput.sneak = false
            LOGGER.debug("Blocked input for player ${event.entityPlayer.name}")
        }
    }
}
