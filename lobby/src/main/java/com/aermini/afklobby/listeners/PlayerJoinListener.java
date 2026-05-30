package com.aermini.afklobby.listeners;

import com.aermini.afklobby.AerAfklobby;
import com.aermini.afklobby.PlayerStateManager;
import com.aermini.afklobby.menu.MenuManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        for (Player other : player.getServer().getOnlinePlayers()) {
            if (other != null && other.isOnline() && other != player) {
                player.hidePlayer(other);
                other.hidePlayer(player);
            }
        }
        Bukkit.getScheduler().runTaskLater(AerAfklobby.getInstance(), () -> {
            if (player.isOnline()) {
                PlayerStateManager.getInstance().setPlayerAFK(player);
                MenuManager.openMenu(player);
            }
        }, 5L);
    }
}
