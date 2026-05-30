package com.aermini.afklobby.listeners;

import com.aermini.afklobby.AerAfklobby;
import com.aermini.afklobby.menu.MenuManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player)) return;
        Player player = (Player) event.getPlayer();
        if (event.getInventory().getTitle().equals("§a您由于挂机暂时进入了挂机厅")) {
            Bukkit.getScheduler().runTask(AerAfklobby.getInstance(), () -> {
                if (player.isOnline()) {
                    MenuManager.openMenu(player);
                }
            });
        }
    }
}
