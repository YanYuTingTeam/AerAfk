package com.aermini.afktest;

import com.aermini.afktest.listeners.PlayerJoinListener;
import com.aermini.afktest.listeners.PlayerQuitListener;
import com.aermini.afktest.listeners.PlayerInteractListener;
import com.aermini.afktest.listeners.PlayerMoveListener;
import com.aermini.afktest.listeners.ChatListener;
import com.aermini.afktest.listeners.CommandListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AerAfktest extends JavaPlugin {
    private static AerAfktest instance;
    private AFKTask afkTask;
    private final Map<UUID, Long> lastActionTime = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoinListener(this), this);
        pm.registerEvents(new PlayerQuitListener(this), this);
        pm.registerEvents(new PlayerInteractListener(this), this);
        pm.registerEvents(new PlayerMoveListener(this), this);
        pm.registerEvents(new ChatListener(this), this);
        pm.registerEvents(new CommandListener(this), this);
        getCommand("afktest").setExecutor(new ReloadCommand(this));
        afkTask = new AFKTask(this);
        afkTask.runTaskTimer(this, 20L * 60L, 20L * 60L);
        getLogger().info("AerAfktest 已启用");
    }

    @Override
    public void onDisable() {
        if (afkTask != null) afkTask.cancel();
        getLogger().info("AerAfktest 已禁用");
    }

    public static AerAfktest getInstance() {
        return instance;
    }

    public void updatePlayerAction(Player player) {
        lastActionTime.put(player.getUniqueId(), System.currentTimeMillis());
    }

    public Long getLastActionTime(UUID uuid) {
        return lastActionTime.get(uuid);
    }

    public void removePlayer(UUID uuid) {
        lastActionTime.remove(uuid);
    }

    public void resetPlayerAction(UUID uuid) {
        lastActionTime.put(uuid, System.currentTimeMillis());
    }

    public Map<UUID, Long> getLastActionTimes() {
        return lastActionTime;
    }
}
