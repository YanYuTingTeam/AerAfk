package com.aermini.afktest;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.UUID;

public class AFKTask extends BukkitRunnable {
    private final AerAfktest plugin;
    public AFKTask(AerAfktest plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        int afkTime = plugin.getConfig().getInt("afktime", 600);
        String afkServer = plugin.getConfig().getString("afkserver", "afking");
        long currentTime = System.currentTimeMillis();
        for (Player player : Bukkit.getOnlinePlayers()) {
            UUID uuid = player.getUniqueId();
            Long lastAction = plugin.getLastActionTime(uuid);
            if (lastAction != null) {
                long secondsSinceLastAction = (currentTime - lastAction) / 1000;
                if (secondsSinceLastAction >= afkTime) {
                    sendToServer(player, afkServer);
                    plugin.resetPlayerAction(uuid);
                }
            }
        }
    }

    private void sendToServer(Player player, String server) {
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
