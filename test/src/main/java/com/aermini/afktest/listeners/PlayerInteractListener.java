package com.aermini.afktest.listeners;

import com.aermini.afktest.AerAfktest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    private final AerAfktest plugin;
    public PlayerInteractListener(AerAfktest plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        plugin.updatePlayerAction(event.getPlayer());
    }
}
