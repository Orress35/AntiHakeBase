package net.anticheat.event;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import net.anticheat.AntiCheat;
import net.anticheat.user.User;
import net.anticheat.util.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PacketListener implements Listener {
    public void start() {
        Logger.info("starting packet listener...");

        for (Player player: Bukkit.getOnlinePlayers()) {
            AntiCheat.INSTANCE.getUserManager().add(player);
        }

        Bukkit.getPluginManager().registerEvents(this, AntiCheat.INSTANCE.getPlugin());

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(AntiCheat.INSTANCE.getPlugin(),
            PacketType.Play.Client.ENTITY_ACTION
        ) {
            @Override
            public void onPacketReceiving(PacketEvent e) {
                if (!e.isPlayerTemporary())
                    onReceive(e);
            }
        });

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(AntiCheat.INSTANCE.getPlugin()) {
            @Override
            public void onPacketSending(PacketEvent e) {
                if (e.isPlayerTemporary())
                    onSend(e);
            }
        });
    }

    public void stop() {
        Logger.info("stopping packet listener...");

        AntiCheat.INSTANCE.getUserManager().clearUsers();
        HandlerList.unregisterAll(this);
        ProtocolLibrary.getProtocolManager().removePacketListeners(AntiCheat.INSTANCE.getPlugin());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

    }

    public void onReceive(PacketEvent e) {
        User user = AntiCheat.INSTANCE.getUserManager().get(e.getPlayer());
        if (user == null)
            return;
        user.handle(e.getPacket());
    }

    public void onSend(PacketEvent e) {
        User user = AntiCheat.INSTANCE.getUserManager().get(e.getPlayer());
        if (user == null)
            return;
        user.handle(e.getPacket());
    }
}
