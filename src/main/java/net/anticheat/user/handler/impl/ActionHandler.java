package net.anticheat.user.handler.impl;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import net.anticheat.event.impl.SneakEvent;
import net.anticheat.event.impl.SprintEvent;
import net.anticheat.user.User;
import net.anticheat.user.handler.Handler;

public class ActionHandler extends Handler
{
    public ActionHandler(User user)
    {
        super(user);
    }

    public void handle(PacketContainer packet)
    {
        if (packet.getType() != PacketType.Play.Client.ENTITY_ACTION)
            return;

        EnumWrappers.PlayerAction action = packet.getPlayerActions().read(0);

        /* sprinting */
        if (action == EnumWrappers.PlayerAction.START_SPRINTING) {
            SprintEvent e;

            if (user.isSprinting()) {
                e = new SprintEvent(true, false);
            } else {
                e = new SprintEvent(true, true);
            }

            user.setSprinting(true);
            user.onEvent(e);
        } else if (action == EnumWrappers.PlayerAction.STOP_SPRINTING) {
            SprintEvent e;

            if (user.isSprinting()) {
                e = new SprintEvent(false, true);
            } else {
                e = new SprintEvent(false, false);
            }

            user.setSprinting(false);
            user.onEvent(e);
        }

        /* sneaking */
        if (action == EnumWrappers.PlayerAction.START_SNEAKING) {
            SneakEvent e;

            if (user.isSneaking()) {
                e = new SneakEvent(true, false);
            } else {
                e = new SneakEvent(true, true);
            }

            user.setSneaking(true);
            user.onEvent(e);
        } else if (action == EnumWrappers.PlayerAction.STOP_SNEAKING) {
            SneakEvent e;

            if (user.isSneaking()) {
                e = new SneakEvent(false, true);
            } else {
                e = new SneakEvent(false, false);
            }

            user.setSneaking(false);
            user.onEvent(e);
        }
    }
}
