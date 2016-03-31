package net.fireimp.server.util;

import net.fireimp.server.TerrariaServer;

public class Maths {

    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    public static double nextDouble(double min, double max) {
        return min + TerrariaServer.GLOBAL_RANDOM.nextDouble() * (max - min);
    }

    public static int nextInt(int min, int max) {
        return min + TerrariaServer.GLOBAL_RANDOM.nextInt(max - min);
    }
}
