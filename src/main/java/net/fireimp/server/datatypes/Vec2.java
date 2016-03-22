package net.fireimp.server.datatypes;

public class Vec2 {
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

}
