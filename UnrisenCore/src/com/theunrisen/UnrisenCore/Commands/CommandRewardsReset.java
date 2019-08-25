package com.theunrisen.UnrisenCore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.theunrisen.UnrisenCore.Main.plugin;

public class CommandRewardsReset implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("rewardsreset")) {
            if (sender.isOp()) {
                if (args.length == 0) {
                    sender.sendMessage("§cYou need to enter an argument");
                } else {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target == null) {
                        sender.sendMessage("§4§lERROR §7You either entered the players name incorrectly or the player is not online");
                    } else {
                        sender.sendMessage("§aPlayers cooldown has been reset");
                        target.sendMessage("§aYour daily rewards cooldown has been reset");
                        plugin.getConfig().set(target.getName(), 15);
                        plugin.saveConfig();
                    }
                }
            } else {
                sender.sendMessage("§cYou do not have permission to execute this command");
            }

        }
        return false;
    }
}
