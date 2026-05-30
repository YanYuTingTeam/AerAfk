package com.aermini.afklobby;

import com.aermini.afklobby.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class AerAfklobby extends JavaPlugin {
    private static AerAfklobby instance;
    private FileConfiguration config;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        config = getConfig();
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoinListener(), this);
        pm.registerEvents(new InventoryClickListener(this), this);
        pm.registerEvents(new InventoryCloseListener(), this);
        pm.registerEvents(new ChatListener(), this);
        pm.registerEvents(new CommandPreprocessListener(), this);
        pm.registerEvents(new PlayerQuitListener(), this);
        getCommand("afklobby").setExecutor(new ReloadCommand());
        getLogger().info("AerAfklobby 已启用");
    }

    @Override
    public void onDisable() {
        getLogger().info("AerAfklobby 已禁用");
    }

    public static AerAfklobby getInstance() {
        return instance;
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        config = getConfig();
    }

    public FileConfiguration getPluginConfig() {
        return config;
    }

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> colorList(List<String> list) {
        List<String> colored = new ArrayList<>();
        for (String line : list) {
            colored.add(ChatColor.translateAlternateColorCodes('&', line));
        }
        return colored;
    }
}
