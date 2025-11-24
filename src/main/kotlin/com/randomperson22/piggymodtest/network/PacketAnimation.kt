package com.randomperson22.piggymodtest.network

import com.randomperson22.piggymodtest.entities.EntityPiggyBase
import io.netty.buffer.ByteBuf
import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.network.ByteBufUtils
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext

class PacketAnimation() : IMessage {
    private var entityId: Int = 0
    private var animationName: String = ""
    private var isJumpscaring: Boolean = false

    constructor(entityId: Int, animationName: String, isJumpscaring: Boolean) : this() {
        this.entityId = entityId
        this.animationName = animationName
        this.isJumpscaring = isJumpscaring
    }

    override fun fromBytes(buf: ByteBuf) {
        entityId = buf.readInt()
        animationName = ByteBufUtils.readUTF8String(buf)
        isJumpscaring = buf.readBoolean()
    }

    override fun toBytes(buf: ByteBuf) {
        buf.writeInt(entityId)
        ByteBufUtils.writeUTF8String(buf, animationName) // now guaranteed non-null
        buf.writeBoolean(isJumpscaring)
    }

    class Handler : IMessageHandler<PacketAnimation, IMessage> {
        override fun onMessage(message: PacketAnimation, ctx: MessageContext): IMessage? {
            Minecraft.getMinecraft().addScheduledTask {
                val entity = Minecraft.getMinecraft().world.getEntityByID(message.entityId)
                if (entity is EntityPiggyBase) {
                    entity.setCurrentAnimation(message.animationName)
                    entity.setJumpscaring(message.isJumpscaring)
                }
            }
            return null
        }
    }
}
