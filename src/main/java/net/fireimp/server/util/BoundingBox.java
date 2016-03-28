package net.fireimp.server.util;

public class BoundingBox {
    public static final BoundingBox ZERO = new BoundingBox(Vec2.ZERO, Vec2.ZERO);
    private Vec2 min;
    private Vec2 max;

    public BoundingBox(Vec2 start, Vec2 end) {
        double minX = Math.min(start.getX(), end.getX());
        double minY = Math.min(start.getY(), end.getY());
        min = new Vec2(minX, minY);
        double maxX = Math.max(start.getX(), end.getX());
        double maxY = Math.max(start.getY(), end.getY());
        max = new Vec2(maxX, maxY);
    }

    public Vec2 min() {
        return min;
    }

    public Vec2 max() {
        return max;
    }

    public BoundingBox expandOut(double x, double y) {
        return new BoundingBox(new Vec2(min.getX() - x, min.getY() - y), new Vec2(max.getX() + x, max.getY() + y));
    }

    public BoundingBox expand(double x, double y) {
        return new BoundingBox(new Vec2(min.getX(), min.getY()), new Vec2(max.getX() + x, max.getY() + y));
    }

    public boolean contains(Vec2 point) {
        return point.getX() > min.getX() && point.getY() > min.getY()
                && point.getX() < max.getX() && point.getY() < max.getY();
    }

    public double getWidth() {
        return Math.abs(min.getX() - max.getX());
    }

    public double getHeight() {
        return Math.abs(min.getY() - max.getY());
    }
}
