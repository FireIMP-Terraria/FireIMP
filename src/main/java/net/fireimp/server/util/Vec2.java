package net.fireimp.server.util;

import net.fireimp.server.datatypes.Location;

public class Vec2 {
    public static final Vec2 ZERO = new Vec2(0, 0);
    private double x;
    private double y;

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return (Math.round(x*1000) / 1000) + ", " + (Math.round(y*1000) / 1000);
    }

    public Vec2 divide(double i) {
        return new Vec2(getX() / i, getY() / i);
    }

    public Vec2 multiply(double i) {
        return new Vec2(getX() * i, getY() * i);
    }

    public double distanceSquared(Vec2 vec2) {
        double xDist = getX() - vec2.getX();
        double yDist = getY() - vec2.getY();
        return (xDist * xDist) + (yDist * yDist);
    }
}
