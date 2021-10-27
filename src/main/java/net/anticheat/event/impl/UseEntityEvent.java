package net.anticheat.event.impl;

import lombok.Getter;
import net.anticheat.event.Event;

public class UseEntityEvent extends Event {
    @Getter
    private final int entityId;

    @Getter
    private final Action action;

    public UseEntityEvent(int entityId, Action action) {
        this.entityId = entityId;
        this.action = action;
    }

    public enum Action {
        INTERACT, ATTACK, INTERACT_AT
    }
}
