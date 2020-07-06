package tk.fridtjof.puddingapi.bukkit.chat;

import org.bukkit.ChatColor;

public class ChatAPI {

    public static String format(String msg) {
        return format('&', msg);
    }

    public static String format(char c, String msg) {
        return ChatColor.translateAlternateColorCodes(c, msg);
    }
}
