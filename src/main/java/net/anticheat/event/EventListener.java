package net.anticheat.event;

import net.anticheat.event.impl.MoveEvent;
import net.anticheat.event.impl.SneakEvent;
import net.anticheat.event.impl.SprintEvent;
import net.anticheat.event.impl.UseEntityEvent;

public interface EventListener {
    void onMove(MoveEvent e);
    void onUseEntity(UseEntityEvent e);
    void onSneak(SneakEvent e);
    void onSprint(SprintEvent e);
}
