package com.theunrisen.UnrisenCore.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class playerJoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().sendTitle("§f§lWelcome§b§l", "§fDo §b/help §fto get started!");

    }
}
