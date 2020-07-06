package tk.fridtjof.puddingapi.bukkit.player;

import org.bukkit.entity.Player;

public class PlayerAPI {

    public static void toggleFly(Player player) {
        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
        } else {
            player.setAllowFlight(true);
        }
    }
}