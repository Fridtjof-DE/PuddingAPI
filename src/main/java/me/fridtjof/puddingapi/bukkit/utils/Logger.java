package me.fridtjof.puddingapi.bukkit.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import me.fridtjof.puddingapi.bukkit.chat.ChatAPI;

public class Logger {

    private JavaPlugin plugin = null;
    private boolean debugMode = false;

    public Logger(JavaPlugin javaPlugin) {
        this.plugin = javaPlugin;
    }

    public Logger(JavaPlugin javaPlugin, boolean debugMode) {
        this.plugin = javaPlugin;
        this.debugMode = debugMode;
    }

    public void info(String msg) {
        print(msg, "");
    }

    public void warn(String msg) {
        print(msg, "§c");
    }

    public void debug(String msg) {
        if(debugMode) {
            print(msg, "§e");
        }
    }

    private void print(String msg, String prefix) {
        msg = ChatAPI.format(msg);
        msg = prefix + "[" + plugin.getName() + "] " + msg;
        Bukkit.getServer().getConsoleSender().sendMessage(msg);
    }
}