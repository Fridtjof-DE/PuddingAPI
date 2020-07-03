package tk.fridtjof.puddingapi.bukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class PuddingApiCmd implements CommandExecutor {

    JavaPlugin plugin = PuddingAPI.getInstance();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        commandSender.sendMessage("§b" + plugin.getName() + " v" + plugin.getDescription().getVersion() + " for Spigot v" + plugin.getDescription().getAPIVersion());

        return false;
    }
}
