package com.aermini.afklobby;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerStateManager {
    private static PlayerStateManager instance;
    private final Map<UUID, GameMode> playerGameModes = new HashMap<>();
    public static PlayerStateManager getInstance() {
        if (instance == null) {
            instance = new PlayerStateManager();
        }
        return instance;
    }

    public void setPlayerAFK(Player player) {
        if (player == null || !player.isOnline()) return;
        playerGameModes.put(player.getUniqueId(), player.getGameMode());
        player.setGameMode(GameMode.SPECTATOR);
        player.setSpectatorTarget(null);
    }

    public void restorePlayer(Player player) {
        if (player == null) return;
        UUID uuid = player.getUniqueId();
        if (playerGameModes.containsKey(uuid)) {
            player.setGameMode(playerGameModes.get(uuid));
            playerGameModes.remove(uuid);
        }
    }

    public GameMode getOriginalGameMode(UUID uuid) {
        return playerGameModes.get(uuid);
    }
}
