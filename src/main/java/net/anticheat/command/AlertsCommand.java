package net.anticheat.command;

import net.anticheat.AntiCheat;
import net.anticheat.user.User;
import net.anticheat.util.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AlertsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender == Bukkit.getConsoleSender()) {
            sender.sendMessage("this command cannot be executed in the console");
            return false;
        }

        User user = AntiCheat.INSTANCE.getUserManager().get((Player) sender);
        if (user == null) {
            Logger.error(sender.getName() + " tried to execute a command but isn't a player?");
            return false;
        }

        if (sender.hasPermission("anticheat.alerts")) {
            user.toggleAlerts();

            String message = AntiCheat.TOGGLE_ALERTS;
            if (user.getAlerts()) {
                message = message.replace("%state", "on");
            } else {
                message = message.replace("%state", "off");
            }

            user.sendMessage(message);
            return true;
        }

        user.sendMessage(AntiCheat.PERMISSION_MESSAGE);
        return false;
    }
}
