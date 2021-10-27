package net.anticheat.check;

import net.anticheat.user.User;

import java.util.ArrayList;
import java.util.List;

public class CheckManager {
    private final List<Check> checks = new ArrayList<>();

    public CheckManager(User user) {

    }

    public List<Check> getChecks() {
        return checks;
    }
}
