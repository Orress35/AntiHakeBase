package net.anticheat;

import lombok.Getter;
import net.anticheat.event.PacketListener;
import net.anticheat.user.UserManager;
import net.anticheat.util.tick.TickHandler;
import org.bukkit.plugin.Plugin;

public enum AntiCheat {
    INSTANCE;

    public static final String INFO_MESSAGE = "&8[&3AntiCheat&8] &3AntiCheat v" + AntiCheat.class.getPackage().getImplementationVersion() + " &7by &3Orress";
    public static final String FLAG_MESSAGE = "&8[&3AntiCheat&8] &3%player &7failed &3%check &8[&7VL: &3%vl&8]";
    public static final String TOGGLE_ALERTS = "&8[&3AntiCheat&8] &7Alerts are now &3%state";
    public static final String PERMISSION_MESSAGE = "&8[&3AntiCheat&8] &7Alerts are now &3%state";

    @Getter
    private Plugin plugin;

    @Getter
    private final UserManager userManager = new UserManager();

    @Getter
    private final PacketListener packetListener = new PacketListener();

    @Getter
    private final TickHandler tickHandler = new TickHandler();

    @Getter
    private int serverVersion;

    public void start(final Plugin plugin) {
        this.plugin = plugin;

        String stringVersion = plugin.getServer().getClass().getPackage().getName();
        stringVersion = stringVersion.substring(stringVersion.lastIndexOf('.') + 1);
        serverVersion = Integer.parseInt(stringVersion.split("_")[0].substring(1) + "" + stringVersion.split("_")[1]);

        packetListener.start();
        tickHandler.start();
    }

    public void stop(final Plugin plugin) {
        this.plugin = plugin;

        packetListener.stop();
        tickHandler.stop();
    }
}
