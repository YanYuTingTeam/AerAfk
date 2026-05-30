package com.aermini.afktest.listeners;

import com.aermini.afktest.AerAfktest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final AerAfktest plugin;
    public PlayerJoinListener(AerAfktest plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        plugin.resetPlayerAction(event.getPlayer().getUniqueId());
    }
}
