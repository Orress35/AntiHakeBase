package net.anticheat.util.wrappers;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.World;

public class Vec3 {
    @Getter @Setter
    private double x, y, z;

    public Vec3(double x, double y, double z) {
        set(x, y, z);
    }

    public Vec3(Loc loc) {
        set(loc.getX(), loc.getY(), loc.getZ());
    }

    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getDeltaX(Vec3 other) {
        return x - other.x;
    }

    public double getDeltaY(Vec3 other) {
        return y - other.y;
    }

    public double getDeltaZ(Vec3 other) {
        return z - other.z;
    }

    public double getDeltaH(Vec3 other) {
        final double deltaX = x - other.x;
        final double deltaZ = z - other.z;
        return Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
    }

    public double getDelta(Vec3 other) {
        final double deltaX = x - other.x;
        final double deltaY = y - other.y;
        final double deltaZ = z - other.z;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
    }

    public boolean equals(Vec3 other) {
        return x == other.x && y == other.y && z == other.z;
    }

    public Location toBukkitLocation(World world) {
        return new Location(world, x, y, z);
    }
}
