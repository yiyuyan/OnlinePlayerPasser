package cn.ksmcbrigade.opps;

import cn.ksmcbrigade.opps.events.JoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class OnlinePlayerPasser extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getPluginManager().registerEvents(new JoinEvent(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
