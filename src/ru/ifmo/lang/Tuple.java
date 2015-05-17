package ru.ifmo.lang;

public class Tuple {
    public final double x;
    public final double y;
    public final double z;

    public Tuple(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String toString() {
        return String.format("(%.3f, %.3f, %.3f)", x, y, z);
    }
}
