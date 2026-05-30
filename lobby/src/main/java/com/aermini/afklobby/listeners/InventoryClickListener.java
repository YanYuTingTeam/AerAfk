package com.aermini.afklobby.listeners;

import com.aermini.afklobby.AerAfklobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.List;

public class InventoryClickListener implements Listener {
    private final AerAfklobby plugin;
    public InventoryClickListener(AerAfklobby plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        if (event.getInventory().getTitle().equals("§a您由于挂机暂时进入了挂机厅")) {
            event.setCancelled(true);
            int slot = event.getSlot();
            ItemStack clicked = event.getCurrentItem();
            if (clicked != null && clicked.hasItemMeta()) {
                String itemName = clicked.getItemMeta().getDisplayName();
                FileConfiguration config = plugin.getPluginConfig();
                for (String key : config.getKeys(false)) {
                    if (key.equals("slot")) continue;
                    List<Integer> slots = config.getIntegerList(key + ".slot");
                    String name = config.getString(key + ".name");
                    if (slots.contains(slot) && name != null &&
                            AerAfklobby.color(name).equals(itemName)) {
                        String hubserver = config.getString(key + ".hubserver");
                        if (hubserver != null) {
                            connectToServer(player, hubserver);
                        }
                        break;
                    }
                }
            }
        }
    }

    private void connectToServer(Player player, String server) {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            out.writeUTF("Connect");
            out.writeUTF(server);
            player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
