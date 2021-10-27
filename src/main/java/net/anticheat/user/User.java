package net.anticheat.user;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import lombok.Getter;
import lombok.Setter;
import net.anticheat.check.Check;
import net.anticheat.check.CheckManager;
import net.anticheat.event.Event;
import net.anticheat.event.impl.MoveEvent;
import net.anticheat.event.impl.UseEntityEvent;
import net.anticheat.user.handler.Handler;
import net.anticheat.user.handler.impl.ActionHandler;
import net.anticheat.util.Logger;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class User {
    @Getter
    private final Player player;

    @Getter
    private final CheckManager checkManager = new CheckManager(this);

    private final List<Handler> handlers = Arrays.asList(new ActionHandler(this));

    private boolean alerts;

    @Getter @Setter
    private boolean sneaking, sprinting;

    public User(Player player)
    {
        this.player = player;
    }

    public void sendPacket(PacketContainer packet)
    {
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
        } catch (Exception ignored) {
            Logger.error("failed to send " + packet.getClass().getSimpleName() + " to " + player.getName());
        }
    }

    public String getName()
    {
        return player.getName();
    }

    public void sendMessage(String message)
    {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public boolean getAlerts()
    {
        return alerts;
    }

    public void toggleAlerts()
    {
        alerts = !alerts;
    }

    public void onEvent(Event e)
    {
        if (e instanceof MoveEvent) {
            MoveEvent event = (MoveEvent) e;
            for (Check check: checkManager.getChecks())
                check.onMove(event);
        } else if (e instanceof UseEntityEvent) {
            UseEntityEvent event = (UseEntityEvent) e;
            for (Check check: checkManager.getChecks())
                check.onUseEntity(event);
        }
    }

    public void handle(PacketContainer packet)
    {
        for (Handler handler: handlers)
            handler.handle(packet);
    }
}
