package me.fridtjof.puddingapi.bukkit.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.module.ModuleDescriptor.Version;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class ModrinthUpdateChecker {

    /**
     * ModrinthUpdateChecker is a utility class for checking if a newer version of a plugin
     * is available on Modrinth for a specific Minecraft game version and loader.
     *
     * It connects to the Modrinth API, fetches the latest release for the given game version
     * and loader, and compares it against the currently running plugin version.
     *
     * Key Features:
     * - Automatically logs update information to the server console.
     * - Warns if the plugin is outdated or running a newer/development version.
     * - Notifies when the plugin is not approved for the current game version or loader.
     *
     * Usage:
     * new ModrinthUpdateChecker(pluginInstance, "modrinthProjectId", "loaderName");
     *
     * Note:
     * - Requires a valid plugin instance for logging and version retrieval.
     * - Uses Gson to parse JSON responses from the Modrinth API.
     * - Handles basic HTTP errors and logs warnings accordingly.
     *
     * Based on: https://api.github.com/repos/rockquiet/joinprotection/releases/latest
     */
    public ModrinthUpdateChecker(JavaPlugin plugin, String modrinthId, String loader) {

        Logger logger = plugin.getLogger();
        logger.info("Checking for an plugin update on Modrinth...");

        String gameVersion = BukkitUtils.getMinecraftVersion();

        try {
            URL requestUrl = new URL("https://api.modrinth.com/v2/project/" + modrinthId + "/version?game_versions=[%22" + gameVersion + "%22]&loaders=[%22" + loader + "%22]");

            HttpURLConnection con = (HttpURLConnection) requestUrl.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Fridtjof-DE/PuddingAPI");

            if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
                logger.warning("Unable to check for updates: HTTP " + con.getResponseCode() + " " + con.getResponseMessage());
                logger.warning("Requested URL: " + requestUrl.toString());
                return;
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            StringBuilder response = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();

            JsonArray jsonResponse = new Gson().fromJson(response.toString(), JsonArray.class);

            if (jsonResponse.isEmpty()) {
                logger.warning("Modrinth's response is empty: Probably The plugin has not been approved for this version of the game or loader. You proceed at your own risk.");
                return;
            }
            Version latest = Version.parse(jsonResponse.get(0).getAsJsonObject().get("version_number").getAsString());
            Version current = Version.parse(plugin.getDescription().getVersion());
            int compare = latest.compareTo(current);


            if (compare > 0) {
                logger.warning("An update is available! Latest version: " + latest + ", you are using: " + current);
                return;
            }
            if (compare < 0) {
                logger.warning("You are running a newer version of the plugin than released. If you are using a development build, please report any bugs on the project's GitHub.");
                return;
            }
            logger.info("No update available for this game version!");

        } catch (Exception e) {
            logger.warning("An exception occurred while checking for an update: " + e.getMessage());
            e.printStackTrace();
        }
    }
}