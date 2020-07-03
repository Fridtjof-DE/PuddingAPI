package tk.fridtjof.puddingapi.bukkit;

import org.bukkit.plugin.java.JavaPlugin;
import tk.fridtjof.puddingapi.bukkit.utils.Metrics;
import tk.fridtjof.puddingapi.bukkit.utils.UpdateChecker;

public final class PuddingAPI extends JavaPlugin {

    public static PuddingAPI instance;

    public PuddingAPI() {
        instance = this;
    }

    public static PuddingAPI getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        new UpdateChecker(this, 55465, "puddingapi.update");
        new Metrics(this, 8018);
        getCommand("puddingapi").setExecutor(new PuddingApiCmd());
    }

    @Override
    public void onDisable() {

    }
}
