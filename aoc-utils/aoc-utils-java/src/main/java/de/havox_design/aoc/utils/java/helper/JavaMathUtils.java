package de.havox_design.aoc.utils.java.helper;

/**
 * Some utilities for math in Java.
 */
public class JavaMathUtils {
    /**
     * Private constructor.
     */
    private JavaMathUtils() {
        // Utility class...
    }

    /**
     * Calculates the greatest common divisor of two numbers.
     *
     * @param a the one number
     * @param b the other number
     * @return the greatest common divisor
     */
    public static long greatestCommonDivisor(long a, long b) {
        if (b == 0) {
            return a;
        }

        return greatestCommonDivisor(b, a % b);
    }

    /**
     * Calculates the least common multiple of two numbers.
     *
     * @param a the one number
     * @param b the other number
     * @return the least common multiple
     */
    public static long leastCommonMultiple(long a, long b) {
        if (a == 0 || b == 0) {
            return 0;
        }

        long absNumberA = Math.abs(a);
        long absNumberB = Math.abs(b);
        long absHigherNumber = Math.max(absNumberA, absNumberB);
        long absLowerNumber = Math.min(absNumberA, absNumberB);
        long lcm = absHigherNumber;

        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }

        return lcm;
    }
}
