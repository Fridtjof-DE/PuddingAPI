package me.fridtjof.puddingapi.bukkit.utils;

import me.fridtjof.puddingapi.bukkit.PuddingAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PuddingAPIVersionChecker {

    //TODO also check for recommended PuddingAPI version on modrinth

    // using built-in java logger
    public PuddingAPIVersionChecker(JavaPlugin executingPlugin, long expectedVersion) {

        java.util.logging.Logger logger = executingPlugin.getLogger();
        long currentPuddingVersion = PuddingAPI.getInstance().getVersion();

        String newerThanExpectedMsg = "PuddingAPI version is newer than expected but should work fine.";
        String expectedWouldBeMsg = "Expected version would be: " + expectedVersion;
        String usingCorrectMsg = "Using correct PuddingAPI version.";
        String outdatedDetectedMsg = "Detected an outdated version of PuddingAPI.";
        String installedIsMsg = "Installed version is: " + currentPuddingVersion;


        if(currentPuddingVersion > expectedVersion) {
            logger.info(newerThanExpectedMsg);
            logger.info(expectedWouldBeMsg);
            return;
        }

        if(currentPuddingVersion == expectedVersion) {
            logger.info(usingCorrectMsg);
            return;
        }

        logger.warning(outdatedDetectedMsg);
        logger.warning(expectedWouldBeMsg);
        logger.warning(installedIsMsg);
        Bukkit.getPluginManager().disablePlugin(executingPlugin);
    }


    // using unnecessary custom logger
    @Deprecated
    public PuddingAPIVersionChecker(JavaPlugin executingPlugin, Logger logger, long expectedVersion) {

        long currentPuddingVersion = PuddingAPI.getInstance().getVersion();

        String newerThanExpectedMsg = "PuddingAPI version is newer than expected but should work fine.";
        String expectedWouldBeMsg = "Expected version would be: " + expectedVersion;
        String usingCorrectMsg = "Using correct PuddingAPI version.";
        String outdatedDetectedMsg = "Detected an outdated version of PuddingAPI.";
        String installedIsMsg = "Installed version is: " + currentPuddingVersion;


        if(currentPuddingVersion > expectedVersion) {
            logger.info(newerThanExpectedMsg);
            logger.info(expectedWouldBeMsg);
            return;
        }

        if(currentPuddingVersion == expectedVersion) {
            logger.info(usingCorrectMsg);
            return;
        }

        logger.warn(outdatedDetectedMsg);
        logger.warn(expectedWouldBeMsg);
        logger.warn(installedIsMsg);
        Bukkit.getPluginManager().disablePlugin(executingPlugin);
    }
}
