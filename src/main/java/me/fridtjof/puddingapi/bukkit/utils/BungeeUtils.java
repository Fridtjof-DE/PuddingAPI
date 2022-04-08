package me.fridtjof.puddingapi.bukkit.utils;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class BungeeUtils {

    //works with Velocity too
    public static void sendPlayerToServer(Player player, String servername, Plugin plugin) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("Connect");
            out.writeUTF(servername);
            b.close();
            out.close();
        } catch (Exception exception) {
            exception.printStackTrace();
            //logger.warning("Failed to connect to " + servername);
        }
        player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
    }
}
