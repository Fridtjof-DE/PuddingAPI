package me.fridtjof.puddingapi.bukkit.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.module.ModuleDescriptor.Version;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

//based of https://api.github.com/repos/rockquiet/joinprotection/releases/latest <3

public class ModrinthUpdateChecker {

    public ModrinthUpdateChecker(JavaPlugin plugin, String modrinthId, String loader) {

        Logger logger = plugin.getLogger();

        logger.info("Checking for an update on Modrinth...");

        try {
            URL obj = new URL("https://api.modrinth.com/v2/project/" + modrinthId + "/version?game_versions=[%22" + plugin.getDescription().getAPIVersion() + "%22]&loaders=[%22" + loader + "%22]");

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Fridtjof-DE/PuddingAPI");

            if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
                logger.warning("Unable to check for updates: HTTP-" + con.getResponseMessage());
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

            if (jsonResponse.get(0) == null) {
                logger.warning("The plugin has not been approved for this version of the game or loader. You proceed at your own risk.");
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
            throw new RuntimeException(e);
        }
    }
}