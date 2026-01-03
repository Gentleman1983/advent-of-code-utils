package de.havox_design.aoc.utils.java.search.breadth_first_search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class AoC2015Day22RPGWizardFightTest {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, int expectedSum) {
        Assertions.assertEquals(expectedSum, AoC2015Day22RPGWizardFight.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2015/day22/day22.txt", 953)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, int expectedSum) {
        Assertions.assertEquals(expectedSum, AoC2015Day22RPGWizardFight.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2015/day22/day22.txt", 1289)
        );
    }
}
