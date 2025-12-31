package de.havox_design.aoc.utils.java.model.keypad;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class AoC2016Day02BathroomSecurityTest {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, String expected) {
        Assertions.assertEquals(expected, AoC2016Day02BathroomSecurity.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2016/day02/day02Sample.txt", "1985")
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, String expected) {
        Assertions.assertEquals(expected, AoC2016Day02BathroomSecurity.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2016/day02/day02Sample.txt", "5DB3")
        );
    }
}
