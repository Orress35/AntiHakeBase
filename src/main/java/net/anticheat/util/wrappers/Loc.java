package net.anticheat.util.wrappers;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.World;

public class Loc {
    @Getter @Setter
    private double x, y, z;

    @Getter @Setter
    private float yaw, pitch;

    public Loc(double x, double y, double z, float yaw, float pitch) {
        set(x, y, z, yaw, pitch);
    }

    public Loc(double x, double y, double z) {
        set(x, y, z);
    }

    public Loc(Vec3 vec) {
        set(vec.getX(), vec.getY(), vec.getZ());
    }

    public void set(double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getDeltaX(Loc other) {
        return x - other.x;
    }

    public double getDeltaY(Loc other) {
        return y - other.y;
    }

    public double getDeltaZ(Loc other) {
        return z - other.z;
    }

    public double getDeltaH(Loc other) {
        final double deltaX = x - other.x;
        final double deltaZ = z - other.z;
        return Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
    }

    public double getDelta(Loc other) {
        final double deltaX = x - other.x;
        final double deltaY = y - other.y;
        final double deltaZ = z - other.z;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
    }

    public boolean equals(Loc other) {
        return x == other.x && y == other.y && z == other.z && yaw == other.yaw && pitch == other.pitch;
    }

    public Location toBukkitLocation(World world) {
        return new Location(world, x, y, z, yaw, pitch);
    }
}
