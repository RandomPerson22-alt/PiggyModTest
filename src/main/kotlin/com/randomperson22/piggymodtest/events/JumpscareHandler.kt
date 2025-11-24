package com.randomperson22.piggymodtest.events;

import java.lang.reflect.Field;
import java.util.WeakHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

@SideOnly(Side.CLIENT)
public class Jumpscare_Handler {
    private static final Logger LOGGER = LogManager.getLogger("PiggyMod/Jumpscare");
    private static final WeakHashMap<EntityPlayer, Integer> jumpscareTimers = new WeakHashMap<>();
    private static final WeakHashMap<EntityPlayer, Vec3d> faceTargets = new WeakHashMap<>();
    private static final WeakHashMap<EntityPlayer, Integer> previousViewModes = new WeakHashMap<>();

    public static void init() {
        MinecraftForge.EVENT_BUS.register(new Jumpscare_Handler());
        LOGGER.info("Jumpscare handler initialized");
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.player;
        if (player == null) return;

        if (jumpscareTimers.containsKey(player)) {
            int timer = jumpscareTimers.get(player);
            LOGGER.debug("Jumpscare timer: {} ticks remaining for player {}", timer, player.getName());

            if (!previousViewModes.containsKey(player)) {
                int currentView = mc.gameSettings.thirdPersonView;
                previousViewModes.put(player, currentView);
                if (currentView != 0) {
                    mc.gameSettings.thirdPersonView = 0;
                    LOGGER.debug("Forced first-person view for player {}", player.getName());
                }
            }

            if (faceTargets.containsKey(player)) {
                forceCameraLook(player, faceTargets.get(player));
            }

            if (timer <= 0) {
                LOGGER.debug("Jumpscare ended for player {}", player.getName());
                jumpscareTimers.remove(player);
                faceTargets.remove(player);
                if (previousViewModes.containsKey(player)) {
                    mc.gameSettings.thirdPersonView = previousViewModes.remove(player);
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
            // Client-side: singleplayer or local client
            LOGGER.debug("Client-side jumpscare triggered");
            if (target instanceof EntityPlayerSP) {
                Jumpscare_Handler.triggerJumpscareClient(entity, (EntityPlayerSP) target, 30);
            }
        } else {
            // Server-side: multiplayer
            LOGGER.debug("Server-side jumpscare triggered");
            if (target instanceof EntityPlayerMP) {
                Jumpscare_Handler.triggerJumpscareServer(entity, (EntityPlayerMP) target);
            }
        }
    }
        
    public static void triggerJumpscareServer(EntityLivingBase entity, EntityPlayerMP targetPlayer) {
        LOGGER.debug("triggerJumpscareServer called - entity: {}, targetPlayer: {}", 
            entity, targetPlayer);
        
        if (entity == null || targetPlayer == null) {
            LOGGER.warn("Jumpscare failed: entity or targetPlayer is null");
            return;
        }

        int duration = 30; // default
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

        float eyeHeight = (entity instanceof ICustomEyeHeight)
                ? ((ICustomEyeHeight) entity).getCustomEyeHeight()
                : entity.getEyeHeight();
        faceTargets.put(player, new Vec3d(entity.posX, entity.posY + eyeHeight, entity.posZ));
    }

    private void forceCameraLook(EntityPlayerSP player, Vec3d target) {
        Vec3d lookVec = target.subtract(player.getPositionEyes(1.0F));
        double dist = Math.sqrt(lookVec.x * lookVec.x + lookVec.z * lookVec.z);
        float yaw = (float)(Math.atan2(lookVec.z, lookVec.x) * (180F / Math.PI)) - 90.0F;
        float pitch = (float)-(Math.atan2(lookVec.y, dist) * (180F / Math.PI));

        player.rotationYaw = player.prevRotationYaw = player.renderYawOffset =
        player.prevRenderYawOffset = player.rotationYawHead = player.prevRotationYawHead = yaw;
        player.rotationPitch = pitch;
        player.setPositionAndRotation(player.posX, player.posY, player.posZ, yaw, pitch);
        
        LOGGER.debug("Forced camera look - Yaw: {}, Pitch: {}", yaw, pitch);
    }

    @SubscribeEvent
    public void onInputUpdate(InputUpdateEvent event) {
        if (jumpscareTimers.containsKey(event.getEntityPlayer())) {
            event.getMovementInput().moveForward = event.getMovementInput().moveStrafe = 0;
            event.getMovementInput().jump = event.getMovementInput().sneak = false;
            LOGGER.debug("Blocked input for player {}", event.getEntityPlayer().getName());
        }
    }
}