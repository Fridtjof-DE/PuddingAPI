package me.fridtjof.puddingapi.bukkit;

import me.fridtjof.puddingapi.bukkit.utils.Logger;
import me.fridtjof.puddingapi.bukkit.utils.Metrics;
import me.fridtjof.puddingapi.bukkit.utils.ModrinthUpdateChecker;
import me.fridtjof.puddingapi.bukkit.utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PuddingAPI extends JavaPlugin {

    public static PuddingAPI instance;

    public PuddingAPI() {
        instance = this;
    }

    public static PuddingAPI getInstance() {
        return instance;
    }

    Logger logger = new Logger(this);

    @Override
    public void onEnable() {

        new ModrinthUpdateChecker(this, "PSN4XhQK", "spigot");
        //legacy update checker
        //new UpdateChecker(this, 55465, "puddingapi.update");

        new Metrics(this, 8018);
        getCommand("puddingapi").setExecutor(new PuddingApiCmd());
    }

    @Override
    public void onDisable() {

    }

    public long getVersion() {
        return Long.parseLong(this.getDescription().getVersion());
    }
}
