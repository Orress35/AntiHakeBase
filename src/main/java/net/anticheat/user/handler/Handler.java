package net.anticheat.user.handler;

import com.comphenix.protocol.events.PacketContainer;
import net.anticheat.user.User;

public abstract class Handler
{
    protected final User user;

    protected Handler(User user)
    {
        this.user = user;
    }

    public abstract void handle(PacketContainer packet);
}
