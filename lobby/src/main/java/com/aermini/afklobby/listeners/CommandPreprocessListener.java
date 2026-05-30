package com.aermini.afklobby.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandPreprocessListener implements Listener {
    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        if (!message.startsWith("/afklobby")) {
            event.setCancelled(true);
        } else if (!player.hasPermission("afklobby.admin")) {
            event.setCancelled(true);
        }
    }
}
