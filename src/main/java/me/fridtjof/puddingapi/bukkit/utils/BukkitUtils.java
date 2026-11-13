package me.fridtjof.puddingapi.bukkit.utils;

import org.bukkit.Bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * BukkitsUtils is a general-purpose utility class for interacting with the Bukkit server.
 *
 * Example usages:
 * - Fetching the current Minecraft version.
 */
public class BukkitUtils {

    private static final Pattern VERSION_PATTERN = Pattern.compile("^(\\d+\\.\\d+(?:\\.\\d+)?)");

    /**
     * Returns the current Minecraft version extracted from Bukkit.getBukkitVersion().
     * Supports both old formats ("1.19.4-R0.1-SNAPSHOT")
     * and new formats ("26.1.2.build.63-stable").
     *
     * @return Minecraft version as a string
     */
    public static String getMinecraftVersion() {
        String rawVersion = Bukkit.getBukkitVersion();

        // Get the Bukkit version string, e.g., "1.21.1-R0.1-SNAPSHOT"
        Matcher matcher = VERSION_PATTERN.matcher(rawVersion);
        if (matcher.find()) {
            return matcher.group(1);
        }

        // Fallback
        return rawVersion.split("-")[0];
    }
}

