package de.havox_design.aoc.utils.java.helper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Test class for {@link JavaMathUtils}.
 */
class JavaMathUtilsTest {
    /**
     * <b>Given</b>: Two numbers.
     * <br/>
     * <b>When</b>: Calculating the greatest common divisor.
     * <br/>
     * <b>Then</b>: We shall get the expected value.
     *
     * @param a a number
     * @param b another number
     * @param expected the expected value
     */
    @ParameterizedTest
    @CsvSource({
            "1,1,1",
            "1,2,1",
            "2,3,1",
            "1,17,1",
            "2,34,2",
            "3,34,1"
    })
    void testGreatestCommonDivisor(long a, long b, long expected) {
        Assertions.assertEquals(expected, JavaMathUtils.greatestCommonDivisor(a, b));
    }

    /**
     * <b>Given</b>: Two numbers.
     * <br/>
     * <b>When</b>: Calculating the least common multiple.
     * <br/>
     * <b>Then</b>: We shall get the expected value.
     *
     * @param b another number
     * @param expected the expected value
     */
    @ParameterizedTest
    @CsvSource({
            "1,1,1",
            "1,2,2",
            "2,3,6",
            "1,17,17",
            "2,34,34",
            "3,34,102"
    })
    void testLeastCommonMultiple(long a, long b, long expected) {
        Assertions.assertEquals(expected, JavaMathUtils.leastCommonMultiple(a, b));
    }
}
