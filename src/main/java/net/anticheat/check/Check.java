package net.anticheat.check;

import net.anticheat.event.EventListener;
import net.anticheat.event.impl.MoveEvent;
import net.anticheat.event.impl.SneakEvent;
import net.anticheat.event.impl.SprintEvent;
import net.anticheat.event.impl.UseEntityEvent;
import net.anticheat.user.User;
import net.anticheat.util.other.AlertUtil;

public class Check implements EventListener {
    protected final User user;

    private final String name;
    private final int max;
    private final boolean experimental, punishable;

    private final CheckInfo checkInfo;

    protected double vl;

    public Check(User user) {
        this.user = user;

        checkInfo = this.getClass().getAnnotation(CheckInfo.class);

        name = checkInfo.name();
        max = checkInfo.max();
        experimental = checkInfo.experimental();
        punishable = checkInfo.punishable();
    }

    public void fail() {
        vl++;

        AlertUtil.alert(user, checkInfo, (int) vl);

        if (vl >= max) {
            punish();
        }
    }

    public void punish() {
        vl = 0;
    }

    @Override public void onMove(MoveEvent e) { }
    @Override public void onUseEntity(UseEntityEvent e) { }
    @Override public void onSneak(SneakEvent e) { }
    @Override public void onSprint(SprintEvent e) { }
}
