package net.anticheat;

import net.anticheat.command.AlertsCommand;
import net.anticheat.command.MainCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiCheatPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        AntiCheat.INSTANCE.start(this);
        getCommand("alerts").setExecutor(new AlertsCommand());
        getCommand("alerts").setExecutor(new MainCommand());
    }

    @Override
    public void onDisable() {
        AntiCheat.INSTANCE.stop(this);
    }
}
