package com.theunrisen.UnrisenCore.Commands;

import com.theunrisen.UnrisenCore.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDiscord implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("discord")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                sender.sendMessage("§bJoin our discord with this link:§f https://discord.gg/cbE8Vba");
            } else {
                sender.sendMessage("§Console cannot run this command");
            }
        }
        return false;
    }
}
