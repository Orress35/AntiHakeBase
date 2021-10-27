package net.anticheat.util.tick;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import net.anticheat.AntiCheat;
import net.anticheat.user.User;
import net.anticheat.util.Logger;
import org.bukkit.Bukkit;

public class TickHandler {
    private short transaction;
    private int taskId;

    public void start() {
        Logger.info("starting tick handler...");

        taskId = Bukkit.getScheduler().runTaskTimer(AntiCheat.INSTANCE.getPlugin(), () -> {
            transaction++;

            if (transaction > -20)
                transaction = -32000;

            PacketContainer packet = new PacketContainer(AntiCheat.INSTANCE.getServerVersion() < 116 ? PacketType.Play.Server.TRANSACTION : PacketType.Play.Server.PING);
            for (User user: AntiCheat.INSTANCE.getUserManager().getUsers()) {
                user.sendPacket(packet);
            }
        }, 0L, 1L).getTaskId();
    }

    public void stop() {
        Logger.info("stopping tick handler...");

        if (taskId != -1) {
            Bukkit.getScheduler().cancelTask(taskId);
        } else {
            Logger.error("tried to stop a task that isn't running");
        }

        taskId = -1;
    }
}
