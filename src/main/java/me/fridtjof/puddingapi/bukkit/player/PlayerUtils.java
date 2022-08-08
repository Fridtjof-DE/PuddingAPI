package me.fridtjof.puddingapi.bukkit.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PlayerUtils {

    public static void toggleFly(Player player) {
        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
        } else {
            player.setAllowFlight(true);
        }
    }

    public static void addAllPlayers(List<String> arguments) {
        for(Player p : Bukkit.getOnlinePlayers()){
            arguments.add(p.getName());
        }
    }

    public static boolean hasEmptyInventory(Player player) {
        for(ItemStack it : player.getInventory().getContents())
        {
            if(it != null) return false;
        }
        return true;
    }
}