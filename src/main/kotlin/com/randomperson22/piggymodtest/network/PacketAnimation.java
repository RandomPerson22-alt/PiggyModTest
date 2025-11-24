package com.randomperson22.piggymodtest.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.randomperson22.piggymodtest.entities.EntityPiggyBase;

public class PacketAnimation implements IMessage {
    private int entityId;
    private String animationName;
    private boolean isJumpscaring;

    public PacketAnimation() {}

    public PacketAnimation(int entityId, String animationName, boolean isJumpscaring) {
        this.entityId = entityId;
        this.animationName = animationName;
        this.isJumpscaring = isJumpscaring;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        entityId = buf.readInt();
        animationName = ByteBufUtils.readUTF8String(buf);
        isJumpscaring = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(entityId);
        ByteBufUtils.writeUTF8String(buf, animationName);
        buf.writeBoolean(isJumpscaring);
    }

    public static class Handler implements IMessageHandler<PacketAnimation, IMessage> {
        @Override
        public IMessage onMessage(PacketAnimation message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                Entity entity = Minecraft.getMinecraft().world.getEntityByID(message.entityId);
                if (entity instanceof EntityPiggyBase) {
                    EntityPiggyBase piggy = (EntityPiggyBase) entity;
                    piggy.setCurrentAnimation(message.animationName);
                    piggy.setJumpscaring(message.isJumpscaring);
                }
            });
            return null;
        }
    }
}
