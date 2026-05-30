package com.aermini.afktest.listeners;

import com.aermini.afktest.AerAfktest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {
    private final AerAfktest plugin;
    public CommandListener(AerAfktest plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        plugin.updatePlayerAction(event.getPlayer());
    }
}
