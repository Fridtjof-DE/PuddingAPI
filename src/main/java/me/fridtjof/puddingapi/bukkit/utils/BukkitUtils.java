package me.fridtjof.puddingapi.bukkit.utils;

import org.bukkit.Bukkit;

/**
 * BukkitsUtils is a general-purpose utility class for interacting with the Bukkit server.
 *
 * Example usages:
 * - Fetching the current Minecraft version.
 */
public class BukkitUtils {

    /**
     * Returns the current Minecraft version of the server.
     * Example output: "1.21.1"
     *
     * @return Minecraft version as a string
     */
    public static String getMinecraftVersion() {
        // Get the Bukkit version string, e.g., "1.21.1-R0.1-SNAPSHOT"
        String rawVersion = Bukkit.getBukkitVersion();

        // Split at the first dash and take the first part to get "1.21.1"
        return rawVersion.split("-")[0];
    }
}
