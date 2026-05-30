package com.aermini.afklobby.listeners;

import com.aermini.afklobby.PlayerStateManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        for (Player other : player.getServer().getOnlinePlayers()) {
            if (other != null && other.isOnline() && other != player) {
                try {
                    other.showPlayer(player);
                    player.showPlayer(other);
                } catch (Exception e) {
                }
            }
        }
        
        PlayerStateManager.getInstance().restorePlayer(player);
    }
}

