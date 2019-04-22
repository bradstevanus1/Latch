package com.brad.latch.util;

import java.util.Comparator;

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
    public static <T extends Number & Comparable<T>> T clamp(T value, T min, T max) {
        if (value.compareTo(min) < 0)
            return min;
        else if (value.compareTo(max) > 0)
            return max;
        return value;
    }

}
