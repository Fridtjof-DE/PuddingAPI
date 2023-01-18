package me.fridtjof.puddingapi.bukkit.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DependencyChecker {

    JavaPlugin executingPlugin;
    Logger logger;

    public DependencyChecker(JavaPlugin executingPlugin, Logger logger) {
        this.executingPlugin = executingPlugin;
        this.logger = logger;
    }

    public void checkForDependency(String pluginName, boolean hardDepend) {

        String hasBeenFound = " has been found, hooking in!";
        String notFoundButNeeded = " was not found, but is needed! Disabling plugin!";
        String notFoundNotNeeded = " was not found, continuing without!";

        if (Bukkit.getPluginManager().getPlugin(pluginName) != null) {
            logger.info(pluginName + hasBeenFound);
        } else {
            if (hardDepend) {
                logger.warn(pluginName + notFoundButNeeded);
                Bukkit.getPluginManager().disablePlugin(executingPlugin);
            } else {
                logger.info(notFoundNotNeeded);
            }
        }
    }
}

