package net.anticheat.event.impl;

import lombok.Getter;
import net.anticheat.event.Event;

public class SneakEvent extends Event {
    @Getter
    private final boolean sneaking, valid;

    public SneakEvent(boolean sneaking, boolean valid)
    {
        this.sneaking = sneaking;
        this.valid = valid;
    }
}
