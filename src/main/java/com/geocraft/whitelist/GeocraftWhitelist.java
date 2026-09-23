package com.geocraft.whitelist;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class GeocraftWhitelist extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        getLogger().info("GeocraftWhitelist has been enabled successfully!");
        getCommand("gwhitelist").setExecutor(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("GeocraftWhitelist has been disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("geocraft.admin")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatColor.YELLOW + "Usage: /gwhitelist <add|remove> <player>");
            return true;
        }

        String action = args[0].toLowerCase();
        String targetName = args[1];

        if (action.equals("add")) {
            OfflinePlayer target = Bukkit.getOfflinePlayer(targetName);
            target.setWhitelisted(true);
            sender.sendMessage(ChatColor.GREEN + targetName + " has been added to the whitelist.");
        } else if (action.equals("remove")) {
            OfflinePlayer target = Bukkit.getOfflinePlayer(targetName);
            target.setWhitelisted(false);
            sender.sendMessage(ChatColor.YELLOW + targetName + " has been removed from the whitelist.");
        } else {
            sender.sendMessage(ChatColor.RED + "Unknown action. Use 'add' or 'remove'.");
        }

        return true;
    }
}
