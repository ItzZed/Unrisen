package com.theunrisen.UnrisenCore;

import com.theunrisen.UnrisenCore.Commands.*;
import com.theunrisen.UnrisenCore.Listeners.playerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Plugin plugin;

    public void onEnable() {
        saveDefaultConfig();
        plugin = this;
        registerEvents(this, new playerJoinListener());
        registerEvents(this, new CommandRewards());
        this.getCommand("help").setExecutor(new CommandHelp());
        this.getCommand("discord").setExecutor(new CommandDiscord());
        this.getCommand("rewards").setExecutor(new CommandRewards());
        this.getCommand("rewardsreset").setExecutor(new CommandRewardsReset());

    }



    public void onDisable() {
        saveDefaultConfig();
        plugin = null;

    }



    public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }





}

