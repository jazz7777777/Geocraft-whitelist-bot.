package com.geocraft.whitelist;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public final class GeocraftWhitelist extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("gwhitelist").setExecutor(this);
        getLogger().info("GeocraftWhitelist has been enabled successfully!");
        
        // Ensure data folder exists
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("GeocraftWhitelist has been disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("geocraft.whitelist")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatColor.YELLOW + "Usage: /gwhitelist <add|remove> <player>");
            return true;
        }

        String action = args[0].toLowerCase();
        String targetPlayer = args[1];

        if (action.equals("add")) {
            // Dispatch native minecraft whitelist command safely from console
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "whitelist add " + targetPlayer);
            sender.sendMessage(ChatColor.GREEN + "Successfully whitelisted " + targetPlayer + "!");
        } else if (action.equals("remove")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "whitelist remove " + targetPlayer);
            sender.sendMessage(ChatColor.RED + "Successfully removed " + targetPlayer + " from whitelist.");
        } else {
            sender.sendMessage(ChatColor.YELLOW + "Usage: /gwhitelist <add|remove> <player>");
        }

        return true;
    }
}
