package net.anticheat.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Logger {
    public static void info(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void error(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "ERROR: " + message));
    }
}
