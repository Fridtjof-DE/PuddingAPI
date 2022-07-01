package me.fridtjof.puddingapi.bukkit.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtils {

    public static String format(String msg) {
        return format('&', msg);
    }

    public static String format(char c, String msg) {
        return ChatColor.translateAlternateColorCodes(c, msg);
    }

    public static void sendTabList(String header, String footer) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.setPlayerListHeaderFooter(ChatUtils.format(header), ChatUtils.format(footer));
        }
    }
}
