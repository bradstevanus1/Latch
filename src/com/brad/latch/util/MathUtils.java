package com.brad.latch.util;

import com.brad.latch.entity.Entity;

public class MathUtils {

    private MathUtils() {}

    public static <T extends Number & Comparable<T>> T min(T value, T min) {
        return (value.compareTo(min) < 0) ? min : value;
    }

    public static <T extends Number & Comparable<T>> T max(T value, T max) {
        return (value.compareTo(max) > 0) ? max : value;
    }

    /**
     * Returns the given value, or the boundaries at the max and min
     * @param value Given value.
     * @param min   Minimum value.
     * @param max   Maximum value.
     * @param <T>   A Comparable Number.
     * @return      A Comparable Number.
     */
    public static <T extends Number & Comparable<T>> T clamp(final T value, final T min, final T max) {
        if (value.compareTo(min) < 0)
            return min;
        else if (value.compareTo(max) > 0)
            return max;
        return value;
    }

    /**
     * Returns the value as a percentage of the max,
     * in the range 0 - 100
     * @param value The value.
     * @param max   The maximum.
     * @return      The percentage.
     */
    public static int percentageOf(final int value, final int max) {
        return (int) (value/((double)max) * 100);
    }

    /**
     * Returns true if the entities are within a
     * specified radius of eachother. Returns
     * false otherwise.
     * @param entity1   First entity.
     * @param entity2   Second entity.
     * @param radius    Search radius.
     * @return          True if the entities are within range of
     *                  eachother, false otherwise.
     */
    public static boolean inRange(final Entity entity1, final Entity entity2, final int radius) {
        if (inRange((int) entity1.getX(), (int) entity2.getX(), radius) &&
                inRange((int) entity1.getY(), (int) entity2.getY(), radius)) {
            return true;
        }
        return false;
    }

    public static boolean inRange(final int num1, final int num2, final int range) {
        int max = num1 + range;
        int min = num1 - range;
        if (num2 >= min && num2 <= max)
            return true;
        return false;
    }
}
