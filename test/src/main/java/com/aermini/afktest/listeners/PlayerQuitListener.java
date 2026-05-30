package com.aermini.afktest.listeners;

import com.aermini.afktest.AerAfktest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private final AerAfktest plugin;
    public PlayerQuitListener(AerAfktest plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.removePlayer(event.getPlayer().getUniqueId());
    }
}
