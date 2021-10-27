package net.anticheat.event.impl;

import lombok.Getter;
import net.anticheat.event.Event;
import net.anticheat.util.wrappers.Loc;

public class MoveEvent extends Event {
    @Getter
    private final Loc to, from;

    @Getter
    private final boolean onGround;

    public MoveEvent(Loc to, Loc from, boolean onGround) {
        this.to = to;
        this.from = from;
        this.onGround = onGround;
    }
}
