/*package com.randomperson22.piggymodtest.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class JumpscareCommand extends CommandBase {

    @Override
    public String getName() {
        return "jumpscare"; // the command name (/jumpscare)
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/jumpscare - triggers the jumpscare animation";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        if (sender.getCommandSenderEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();

            sender.sendMessage(new TextComponentString("Jumpscare triggered!"));
        }
    }
}
*/