package me.fridtjof.puddingapi.bukkit.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleDescriptor.Version;
import java.net.HttpURLConnection;
import java.net.URL;

//based of https://api.github.com/repos/rockquiet/joinprotection/releases/latest <3

public class ModrinthUpdateChecker {

    public ModrinthUpdateChecker(JavaPlugin plugin, String modrinthId, String loader) {

        try {
            URL obj = new URL("https://api.modrinth.com/v2/project/" + modrinthId + "/version?game_versions=[%22" + plugin.getDescription().getAPIVersion() + "%22]&loaders=[%22" + loader + "%22]");

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Fridtjof-DE/PuddingAPI");

            if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
                plugin.getLogger().warning("Unable to check for updates...");
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

            Version latest = Version.parse(jsonResponse.get(0).getAsJsonObject().get("version_number").getAsString());
            Version current = Version.parse(plugin.getDescription().getVersion());
            int compare = latest.compareTo(current);

            if (compare > 0) {
                plugin.getLogger().info("An update is available! Latest version: " + latest + ", you are using: " + current);
            } else if (compare < 0) {
                plugin.getLogger().warning("You are running a newer version of the plugin than released. If you are using a development build, please report any bugs on the project's GitHub.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}