package com.randomperson22.piggymodtest.network

import com.randomperson22.piggymodtest.events.JumpscareHandler
import io.netty.buffer.ByteBuf
import net.minecraft.client.Minecraft
import net.minecraft.entity.EntityLivingBase
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext

class PacketJumpscare() : IMessage {
    private var entityId: Int = 0
    private var duration: Int = 0

    constructor(entityId: Int, duration: Int) : this() {
        this.entityId = entityId
        this.duration = duration
    }

    override fun toBytes(buf: ByteBuf) {
        buf.writeInt(entityId)
        buf.writeInt(duration)
    }

    override fun fromBytes(buf: ByteBuf) {
        entityId = buf.readInt()
        duration = buf.readInt()
    }

    class Handler : IMessageHandler<PacketJumpscare, IMessage> {
        override fun onMessage(message: PacketJumpscare, ctx: MessageContext): IMessage? {
            Minecraft.getMinecraft().addScheduledTask {
                val entity = Minecraft.getMinecraft().world.getEntityByID(message.entityId)
                val player = Minecraft.getMinecraft().player
                if (entity is EntityLivingBase) {
                    // Trigger jumpscare only for the targeted player
                    JumpscareHandler.triggerJumpscareClient(entity, player, message.duration)
                }
            }
            return null
        }
    }
}
