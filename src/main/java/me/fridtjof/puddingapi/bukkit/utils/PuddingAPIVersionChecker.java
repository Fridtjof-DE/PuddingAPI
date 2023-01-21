package me.fridtjof.puddingapi.bukkit.utils;

import me.fridtjof.puddingapi.bukkit.PuddingAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PuddingAPIVersionChecker {

    public PuddingAPIVersionChecker(JavaPlugin executingPlugin, Logger logger, long expectedVersion) {

        long currentPuddingVersion = PuddingAPI.getInstance().getVersion();

        String expectedWouldBe = "Expected version would be: " + expectedVersion;

        if(currentPuddingVersion > expectedVersion) {
            logger.info("PuddingAPI version is newer than expected but should work fine.");
            logger.info(expectedWouldBe);
            return;
        }

        if(currentPuddingVersion == expectedVersion) {
            logger.info("Using correct PuddingAPI version");
            return;
        }

        logger.warn("Detected an outdated version of PuddingAPI.");
        logger.warn(expectedWouldBe);
        logger.warn("Installed version is: " + currentPuddingVersion);
        Bukkit.getPluginManager().disablePlugin(executingPlugin);

    }
}
