package com.theunrisen.UnrisenCore.Commands;

import com.theunrisen.UnrisenCore.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHelp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("help")) {
            if (sender instanceof Player) {
                System.out.println();
                Player player = (Player) sender;
                player.sendMessage("§f§l|-----------§b§lHelp§f§l-----------|");
                player.sendMessage("§aDo /f to view all faction commands");
                player.sendMessage("§aDo /shop to view all things you can buy");
                player.sendMessage("§aDo /discord to join our discord");
                player.sendMessage("§aOur website is https://theunrisen.com");
                player.sendMessage("§aIf you have any questions contact staff!");
                player.sendMessage("§f§l|-----------§b§lHelp§f§l-----------|");
            } else {
                sender.sendMessage("§Console cannot run this command");
            }
        }
        return false;
    }
}
