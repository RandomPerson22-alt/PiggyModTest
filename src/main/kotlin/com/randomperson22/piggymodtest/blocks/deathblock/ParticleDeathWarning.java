package com.randomperson22.piggymodtest.blocks.deathblock;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleDeathWarning extends Particle {
    private static final ResourceLocation TEXTURE = new ResourceLocation("piggymodtest:textures/blocks/invisibledeathblock.png");

    public ParticleDeathWarning(World world, double x, double y, double z) {
        super(world, x, y, z);
        this.particleMaxAge = 20; // lasts 1 second (20 ticks)
        this.particleGravity = 0.0F;
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks,
                               float rotationX, float rotationZ, float rotationYZ,
                               float rotationXY, float rotationXZ) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
        // This is basically the same as ParticleBarrier
        float scale = 0.5F;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)this.posX - interpPosX, (float)this.posY - interpPosY + 1.2F, (float)this.posZ - interpPosZ);
        GlStateManager.scale(scale, scale, scale);
        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buf = tess.getBuffer();
        buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buf.pos(-1, -1, 0).tex(0, 1).endVertex();
        buf.pos(1, -1, 0).tex(1, 1).endVertex();
        buf.pos(1, 1, 0).tex(1, 0).endVertex();
        buf.pos(-1, 1, 0).tex(0, 0).endVertex();
        tess.draw();
        GlStateManager.popMatrix();
    }
}