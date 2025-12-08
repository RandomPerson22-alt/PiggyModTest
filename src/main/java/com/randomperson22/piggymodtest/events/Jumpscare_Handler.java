package com.randomperson22.piggymodtest.events;

import com.randomperson22.piggymodtest.init.ModNetwork;
import com.randomperson22.piggymodtest.interfaces.ICustomEyeHeight;
import com.randomperson22.piggymodtest.network.PacketJumpscare;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.WeakHashMap;

import static java.lang.Math.atan2;
import static java.lang.Math.sqrt;

@SideOnly(Side.CLIENT)
public final class Jumpscare_Handler {

    private static final Logger LOGGER = LogManager.getLogger("PiggyMod/Jumpscare");
    private static final WeakHashMap<EntityPlayer, Integer> jumpscareTimers = new WeakHashMap<>();
    private static final WeakHashMap<EntityPlayer, Vec3d> faceTargets = new WeakHashMap<>();
    private static final WeakHashMap<EntityPlayer, Integer> previousViewModes = new WeakHashMap<>();

    private Jumpscare_Handler() {} // prevent instantiation

    public static void init() {
        MinecraftForge.EVENT_BUS.register(Jumpscare_Handler.class);
        LOGGER.info("Jumpscare handler initialized");
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.player;
        if (player == null) return;

        Integer timer = jumpscareTimers.get(player);
        if (timer != null) {
            LOGGER.debug("Jumpscare timer: {} ticks remaining for player {}", timer, player.getName());

            if (!previousViewModes.containsKey(player)) {
                int currentView = mc.gameSettings.thirdPersonView;
                previousViewModes.put(player, currentView);
                if (currentView != 0) {
                    mc.gameSettings.thirdPersonView = 0;
                    LOGGER.debug("Forced first-person view for player {}", player.getName());
                }
            }

            Vec3d target = faceTargets.get(player);
            if (target != null) {
                forceCameraLook(player, target);
            }

            if (timer <= 0) {
                LOGGER.debug("Jumpscare ended for player {}", player.getName());
                jumpscareTimers.remove(player);
                faceTargets.remove(player);
                Integer oldView = previousViewModes.remove(player);
                if (oldView != null) {
                    mc.gameSettings.thirdPersonView = oldView;
                    LOGGER.debug("Restored camera view for player {}", player.getName());
                }
            } else {
                jumpscareTimers.put(player, timer - 1);
            }
        }
    }

    public static void triggerJumpscareAuto(EntityLivingBase entity, EntityLivingBase target) {
        LOGGER.debug("triggerJumpscareAuto called - entity: {}, target: {}, world remote: {}",
                entity, target, entity.world.isRemote);

        if (entity.world.isRemote) {
            if (target instanceof EntityPlayerSP) {
                triggerJumpscareClient(entity, (EntityPlayerSP) target, 30);
            }
        } else {
            if (target instanceof EntityPlayerMP) {
                triggerJumpscareServer(entity, (EntityPlayerMP) target);
            }
        }
    }

    public static void triggerJumpscareServer(EntityLivingBase entity, EntityPlayerMP targetPlayer) {
        LOGGER.debug("triggerJumpscareServer called - entity: {}, targetPlayer: {}", entity, targetPlayer);

        if (entity == null || targetPlayer == null) {
            LOGGER.warn("Jumpscare failed: entity or targetPlayer is null");
            return;
        }

        int duration = 30;
        try {
            Field durationField = entity.getClass().getDeclaredField("JUMPSCARE_DURATION");
            durationField.setAccessible(true);
            duration = durationField.getInt(null);
            LOGGER.debug("Found custom jumpscare duration: {} ticks", duration);
        } catch (Exception e) {
            LOGGER.debug("Using default jumpscare duration (30 ticks) for {}", entity.getClass().getSimpleName());
        }

        ModNetwork.INSTANCE.sendTo(new PacketJumpscare(entity.getEntityId(), duration), targetPlayer);
        LOGGER.debug("Sent jumpscare packet to player {}", targetPlayer.getName());
    }

    public static void triggerJumpscareClient(EntityLivingBase entity, EntityPlayerSP player, int duration) {
        LOGGER.debug("triggerJumpscareClient called - entity: {}, player: {}, duration: {}",
                entity, player.getName(), duration);

        jumpscareTimers.put(player, duration);

        double eyeHeight;
        if (entity instanceof ICustomEyeHeight) {
            eyeHeight = ((ICustomEyeHeight) entity).getCustomEyeHeight();
        } else {
            eyeHeight = entity.getEyeHeight();
        }

        faceTargets.put(player, new Vec3d(entity.posX, entity.posY + eyeHeight, entity.posZ));
    }


    private static void forceCameraLook(EntityPlayerSP player, Vec3d target) {
        Vec3d lookVec = target.subtract(player.getPositionEyes(1.0f));
        double dist = sqrt(lookVec.x * lookVec.x + lookVec.z * lookVec.z);
        double yaw = Math.toDegrees(atan2(lookVec.z, lookVec.x)) - 90.0;
        double pitch = -Math.toDegrees(atan2(lookVec.y, dist));

        player.rotationYaw = (float) yaw;
        player.prevRotationYaw = (float) yaw;
        player.renderYawOffset = (float) yaw;
        player.prevRenderYawOffset = (float) yaw;
        player.rotationYawHead = (float) yaw;
        player.prevRotationYawHead = (float) yaw;
        player.rotationPitch = (float) pitch;
        player.setPositionAndRotation(player.posX, player.posY, player.posZ, (float) yaw, (float) pitch);

        LOGGER.debug("Forced camera look - Yaw: {}, Pitch: {}", yaw, pitch);
    }

    @SubscribeEvent
    public static void onInputUpdate(InputUpdateEvent event) {
        if (jumpscareTimers.containsKey(event.getEntityPlayer())) {
            event.getMovementInput().moveForward = 0f;
            event.getMovementInput().moveStrafe = 0f;
            event.getMovementInput().jump = false;
            event.getMovementInput().sneak = false;
            LOGGER.debug("Blocked input for player {}", event.getEntityPlayer().getName());
        }
    }
}
