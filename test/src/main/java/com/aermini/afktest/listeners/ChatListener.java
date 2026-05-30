package com.aermini.afktest.listeners;

import com.aermini.afktest.AerAfktest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    private final AerAfktest plugin;
    public ChatListener(AerAfktest plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        plugin.updatePlayerAction(event.getPlayer());
    }
}
