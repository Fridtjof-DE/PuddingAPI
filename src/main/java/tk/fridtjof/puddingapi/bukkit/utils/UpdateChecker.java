package tk.fridtjof.puddingapi.bukkit.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

// UpdateChecker based on https://github.com/Benz56/Async-Update-Checker/blob/master/UpdateChecker.java
public class UpdateChecker {

    private JavaPlugin javaPlugin;
    private String localPluginVersion;
    private String spigotPluginVersion;

    Logger logger;
    private String perm;
    private String pluginName;

    private int id = 0;
    private final String ERR_MSG = "&cUpdate checker failed!";
    private Permission updatePerm;
    private final long CHECK_INTERVAL = 12_000; //In ticks.

    public UpdateChecker(final JavaPlugin javaPlugin, int spigotMcId, String updatePermission) {
        this.javaPlugin = javaPlugin;
        this.localPluginVersion = javaPlugin.getDescription().getVersion();
        this.id = spigotMcId;
        this.perm = updatePermission;
        this.pluginName = javaPlugin.getName();
        logger = new Logger(javaPlugin);
        updatePerm = new Permission(perm, PermissionDefault.TRUE);
        checkForUpdate();
    }

    public void checkForUpdate() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getScheduler().runTaskAsynchronously(javaPlugin, () -> {
                    try {
                        final HttpsURLConnection connection = (HttpsURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=" + id).openConnection();
                        connection.setRequestMethod("GET");
                        spigotPluginVersion = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
                    } catch (final IOException e) {
                        logger.warn(ERR_MSG);
                        e.printStackTrace();
                        cancel();
                        return;
                    }

                    String UPDATE_MSG = "&c" + "Version " + spigotPluginVersion + " is available at:&b https://www.spigotmc.org/resources/" + id + "/updates &c- version " + localPluginVersion + " is installed!";

                    try {
                        int newV = Integer.parseInt(spigotPluginVersion.replaceAll("\\.", ""));
                        int thisV = Integer.parseInt(spigotPluginVersion.replaceAll("\\.", ""));
                        if (newV <= thisV) return;
                    } catch(NumberFormatException exception) {
                        if (localPluginVersion.equals(spigotPluginVersion)) return;
                    }

                    logger.warn(ChatColor.translateAlternateColorCodes('&',UPDATE_MSG));

                    Bukkit.getScheduler().runTask(javaPlugin, () -> Bukkit.getPluginManager().registerEvents(new Listener() {
                        @EventHandler(priority = EventPriority.MONITOR)
                        public void onPlayerJoin(final PlayerJoinEvent event) {
                            final Player player = event.getPlayer();
                            if (!player.hasPermission(updatePerm)) return;
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b[" + pluginName + "] " + UPDATE_MSG));
                        }
                    }, javaPlugin));

                    cancel();
                });
            }
        }.runTaskTimer(javaPlugin, 0, CHECK_INTERVAL);
    }
}