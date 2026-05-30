package com.aermini.afklobby.menu;

import com.aermini.afklobby.AerAfklobby;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import java.util.List;

public class MenuManager {
    private static final String MENU_TITLE = "§a您由于挂机暂时进入了挂机厅";
    public static void openMenu(Player player) {
        if (player == null || !player.isOnline()) return;
        FileConfiguration config = AerAfklobby.getInstance().getPluginConfig();
        int size = config.getInt("slot", 27);
        Inventory inventory = Bukkit.createInventory(null, size, MENU_TITLE);
        for (String key : config.getKeys(false)) {
            if (key.equals("slot")) continue;
            ConfigurationSection section = config.getConfigurationSection(key);
            if (section == null) continue;
            List<Integer> slots = section.getIntegerList("slot");
            String materialName = section.getString("item");
            String name = section.getString("name");
            List<String> lore = section.getStringList("lore");
            try {
                Material material = Material.valueOf(materialName);
                ItemStack item = new ItemStack(material);
                ItemMeta meta = item.getItemMeta();
                if (meta != null) {
                    meta.setDisplayName(AerAfklobby.color(name));
                    if (lore != null && !lore.isEmpty()) {
                        meta.setLore(AerAfklobby.colorList(lore));
                    }
                    item.setItemMeta(meta);
                }
                for (int slot : slots) {
                    if (slot >= 0 && slot < size) {
                        inventory.setItem(slot, item);
                    }
                }
            } catch (IllegalArgumentException e) {
                AerAfklobby.getInstance().getLogger().warning("Invalid material: " + materialName);
            } catch (Exception e) {
                AerAfklobby.getInstance().getLogger().warning("Error loading item " + key + ": " + e.getMessage());
            }
        }

        player.openInventory(inventory);
    }

    public static String getHubServer(String key) {
        FileConfiguration config = AerAfklobby.getInstance().getPluginConfig();
        ConfigurationSection section = config.getConfigurationSection(key);
        if (section != null) {
            return section.getString("hubserver");
        }
        return null;
    }
}
