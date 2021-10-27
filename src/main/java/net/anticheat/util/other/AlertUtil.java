package net.anticheat.util.other;

import net.anticheat.AntiCheat;
import net.anticheat.check.CheckInfo;
import net.anticheat.user.User;

public class AlertUtil {
    public static void alert(User user, CheckInfo checkInfo, int vl) {
        String message = AntiCheat.FLAG_MESSAGE
                .replace("%player", user.getName())
                .replace("%check", checkInfo.name())
                .replace("%vl", String.valueOf(vl));

        for (User staff: AntiCheat.INSTANCE.getUserManager().getUsers()) {
            if (staff.getAlerts())
                staff.sendMessage(message);
        }
    }
}
