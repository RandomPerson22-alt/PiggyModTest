package com.randomperson22.piggymodtest.network;

import com.randomperson22.piggymodtest.events.Jumpscare_Handler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketJumpscare implements IMessage {
    private int entityId;
    private int duration;

    public PacketJumpscare() {}

    public PacketJumpscare(int entityId, int duration) {
        this.entityId = entityId;
        this.duration = duration;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        PacketBuffer buffer = new PacketBuffer(buf);
        this.entityId = buffer.readInt();
        this.duration = buffer.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        PacketBuffer buffer = new PacketBuffer(buf);
        buffer.writeInt(this.entityId);
        buffer.writeInt(this.duration);
    }

    public static class Handler implements IMessageHandler<PacketJumpscare, IMessage> {
        @Override
        public IMessage onMessage(PacketJumpscare message, MessageContext ctx) {
            if (ctx.side == Side.CLIENT) {
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    Entity e = Minecraft.getMinecraft().world.getEntityByID(message.entityId);
                    if (e instanceof EntityLivingBase) {
                        EntityPlayerSP player = Minecraft.getMinecraft().player;
                        Jumpscare_Handler.triggerJumpscareClient((EntityLivingBase) e, player, message.duration);
                    }
                });
            }
            return null; // no response packet
        }
    }
}
