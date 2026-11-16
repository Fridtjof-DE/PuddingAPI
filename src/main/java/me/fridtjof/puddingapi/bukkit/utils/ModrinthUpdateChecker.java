package me.fridtjof.puddingapi.bukkit.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.module.ModuleDescriptor.Version;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModrinthUpdateChecker implements Listener {

    private static final String MODRINTH_API_URL = "https://api.modrinth.com/v2/project/%s/version?game_versions=%s&loaders=%s";
    private static final String MODRINTH_PAGE_URL = "https://modrinth.com/plugin/%s/version/%s";
    private static final String USER_AGENT = "Fridtjof-DE/PuddingAPI";

    private final JavaPlugin plugin;
    private final Logger logger;
    private final String pluginName;
    private final String modrinthId;
    private final String loader;
    private final String updatePermission;

    private volatile UpdateStatus updateStatus = UpdateStatus.PENDING;
    private Version currentVersion;
    private Version latestVersion;
    private String updateMessage;
    private String resourceUrl;

    public ModrinthUpdateChecker(JavaPlugin plugin, String modrinthId, String loader) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        this.pluginName = plugin.getDescription().getName();
        this.modrinthId = modrinthId;
        this.loader = loader;
        this.updatePermission = pluginName.toLowerCase() + ".update";

        registerUpdatePermission();

        // Run asynchronously to avoid blocking the main server thread during startup
        Bukkit.getScheduler().runTaskAsynchronously(plugin, this::checkForUpdate);
    }

    private void registerUpdatePermission() {
        if (Bukkit.getPluginManager().getPermission(updatePermission) == null) {
            Permission permission = new Permission(
                    updatePermission,
                    "Allows receiving update notifications for " + pluginName,
                    PermissionDefault.OP
            );
            Bukkit.getPluginManager().addPermission(permission);
        }
    }

    private void checkForUpdate() {
        logger.info("Checking for a plugin update on Modrinth...");
        String gameVersion = BukkitUtils.getMinecraftVersion();

        try {
            String encodedGameVersions = URLEncoder.encode("[\"" + gameVersion + "\"]", StandardCharsets.UTF_8);
            String encodedLoaders = URLEncoder.encode("[\"" + loader + "\"]", StandardCharsets.UTF_8);

            URL requestUrl = new URL(String.format(MODRINTH_API_URL, modrinthId, encodedGameVersions, encodedLoaders));
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                updateStatus = UpdateStatus.CONNECTION_FAILED;
                logger.warning("Unable to check for updates: HTTP " + responseCode + " " + connection.getResponseMessage());
                logger.warning("Requested URL: " + requestUrl);
                return;
            }

            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            JsonArray jsonResponse = new Gson().fromJson(response.toString(), JsonArray.class);
            if (jsonResponse == null || jsonResponse.isEmpty()) {
                updateStatus = UpdateStatus.NOT_APPROVED;
                logger.warning("Modrinth's response is empty: Probably the plugin has not been approved for this game version or loader. You proceed at your own risk.");
                logger.warning("Requested URL: " + requestUrl);
                return;
            }

            String latestVersionRaw = jsonResponse.get(0).getAsJsonObject().get("version_number").getAsString();
            this.latestVersion = Version.parse(latestVersionRaw);
            this.currentVersion = Version.parse(plugin.getDescription().getVersion());

            evaluateVersionComparison();

        } catch (Exception e) {
            updateStatus = UpdateStatus.EXCEPTION;
            logger.log(Level.WARNING, "An exception occurred while checking for an update: " + e.getMessage(), e);
        }
    }

    private void evaluateVersionComparison() {
        int compare = latestVersion.compareTo(currentVersion);

        if (compare > 0) {
            updateStatus = UpdateStatus.OUTDATED;
            updateMessage = "§7New update available!\n§7Latest version: §a" + latestVersion + "§7, you are using: §4" + currentVersion + "§7!";
            resourceUrl = String.format(MODRINTH_PAGE_URL, modrinthId, latestVersion);
        } else if (compare < 0) {
            updateStatus = UpdateStatus.DEV_BUILD;
            updateMessage = "§7You are running a newer version of " + pluginName + " than released or approved for this MC version. If you are using a development build, please report any bugs on the project's GitHub.";
        } else {
            updateStatus = UpdateStatus.UP_TO_DATE;
            logger.info("No update available for this game version!");
            return;
        }

        if (updateStatus.shouldNotifyPlayer()) {
            notifyConsoleAndRegisterListener();
        }
    }

    private void notifyConsoleAndRegisterListener() {
        // Strip Minecraft color codes for clean console output
        logger.warning(ChatColor.stripColor(updateMessage).replace("\n", " "));

        if (updateStatus.shouldShowResourceLink() && resourceUrl != null) {
            logger.warning("Modrinth -> " + resourceUrl);
        }

        // Event registration on the main server thread
        Bukkit.getScheduler().runTask(plugin, () -> Bukkit.getPluginManager().registerEvents(this, plugin));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!updateStatus.shouldNotifyPlayer() || updateMessage == null) {
            return;
        }

        Player player = event.getPlayer();
        if (!hasUpdatePermission(player)) {
            return;
        }

        sendUpdateNotification(player);
    }

    public void sendUpdateNotification(Player player) {
        if (!updateStatus.shouldNotifyPlayer() || updateMessage == null) {
            return;
        }

        player.sendMessage("");
        player.sendMessage("§8[§d" + pluginName + "§8] " + updateMessage);

        if (updateStatus.shouldShowResourceLink() && resourceUrl != null) {
            player.spigot().sendMessage(buildResourcePageComponent());
        }

        player.sendMessage("");
    }

    private boolean hasUpdatePermission(Player player) {
        return player.hasPermission(updatePermission);
    }

    private TextComponent buildResourcePageComponent() {
        TextComponent line = new TextComponent(TextComponent.fromLegacyText(" §a-> §7Resource Page §8["));

        TextComponent link = new TextComponent("Modrinth");
        link.setColor(ChatColor.GREEN);
        link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, resourceUrl));
        link.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(TextComponent.fromLegacyText("§7-> Go to §aModrinth §7resource page!"))));

        TextComponent suffix = new TextComponent(TextComponent.fromLegacyText("§8]"));

        line.addExtra(link);
        line.addExtra(suffix);
        return line;
    }

    //  Getters for potential future extensions

    public UpdateStatus getUpdateStatus() {
        return updateStatus;
    }

    public Version getCurrentVersion() {
        return currentVersion;
    }

    public Version getLatestVersion() {
        return latestVersion;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public boolean isUpdateAvailable() {
        return updateStatus == UpdateStatus.OUTDATED;
    }
}