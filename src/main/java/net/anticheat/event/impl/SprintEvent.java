package net.anticheat.event.impl;

import lombok.Getter;
import net.anticheat.event.Event;

public class SprintEvent extends Event {
    @Getter
    private final boolean sprinting, valid;

    public SprintEvent(boolean sprinting, boolean valid)
    {
        this.sprinting = sprinting;
        this.valid = valid;
    }
}
