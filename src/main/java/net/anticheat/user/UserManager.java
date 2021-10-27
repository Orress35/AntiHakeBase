package net.anticheat.user;

import org.bukkit.entity.Player;

import java.util.*;

public class UserManager {
    private final Map<UUID, User> userMap = new HashMap<>();

    public void add(Player player) {
        userMap.put(player.getUniqueId(), new User(player));
    }

    public void remove(Player player) {
        userMap.remove(player.getUniqueId());
    }

    public User get(Player player) {
        return userMap.get(player.getUniqueId());
    }

    public List<User> getUsers() {
        return new ArrayList<>(userMap.values());
    }

    public void clearUsers() {
        userMap.clear();
    }
}
