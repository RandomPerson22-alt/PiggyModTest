package com.randomperson22.piggymodtest.network;

import com.randomperson22.piggymodtest.events.Jumpscare_Handler;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketJumpscare implements IMessage {
    private int entityId;
    private int duration;

    public PacketJumpscare() {}
    public PacketJumpscare(int entityId, int duration) {
        this.entityId = entityId;
        this.duration = duration;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeInt(duration);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        entityId = buf.readInt();
        duration = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketJumpscare, IMessage> {
        @Override
        public IMessage onMessage(PacketJumpscare message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                Entity entity = Minecraft.getMinecraft().world.getEntityByID(message.entityId);
                EntityPlayerSP player = Minecraft.getMinecraft().player;
                if (entity instanceof EntityLivingBase) {
                    // Trigger jumpscare only for the targeted player
                    Jumpscare_Handler.triggerJumpscareClient((EntityLivingBase) entity, player, message.duration);
                }
            });
            return null;
        }
    }
}
