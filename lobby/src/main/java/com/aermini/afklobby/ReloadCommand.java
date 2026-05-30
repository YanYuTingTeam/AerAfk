package com.aermini.afklobby;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("afklobby.admin")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission!");
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            AerAfklobby.getInstance().reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "AerAfklobby configuration reloaded!");
            return true;
        }

        sender.sendMessage(ChatColor.YELLOW + "Usage: /afklobby reload");
        return true;
    }
}
