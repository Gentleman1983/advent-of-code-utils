package de.havox_design.aoc.utils.java.model.programs.duet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.stream.Stream;

class AoC2017Day18DuetTest {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, BigInteger expected) {
        Assertions.assertEquals(expected, AoC2017Day18Duet.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2017/day18/day18Sample1.txt", BigInteger.valueOf(4)),
                Arguments.of("de/havox_design/aoc2017/day18/day18Sample3.txt", BigInteger.valueOf(3423)),
                Arguments.of("de/havox_design/aoc2017/day18/day18Sample4.txt", BigInteger.valueOf(42))
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, BigInteger expected) {
        Assertions.assertEquals(expected, AoC2017Day18Duet.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2017/day18/day18Sample1.txt", BigInteger.valueOf(1)),
                Arguments.of("de/havox_design/aoc2017/day18/day18Sample2.txt", BigInteger.valueOf(3)),
                Arguments.of("de/havox_design/aoc2017/day18/day18Sample3.txt", BigInteger.valueOf(7493))
        );
    }
}
