package de.havox_design.aoc.utils.kotlin.model.positions.position_2d

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day24Test {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        AoC2022Day24BlizzardBasin(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(
        filename: String,
        expectedResult1stWayToGoal: Int,
        expectedResultWayBackToStart: Int,
        expectedResult2ndWayToGoal: Int,
        expectedResultTotal: Int
    ) =
        assertAll(
            { AoC2022Day24BlizzardBasin(filename).processPart2().get1stWayToGoalResult().shouldBe(expectedResult1stWayToGoal) },
            { AoC2022Day24BlizzardBasin(filename).processPart2().getWayBackToStartResult().shouldBe(expectedResultWayBackToStart) },
            { AoC2022Day24BlizzardBasin(filename).processPart2().get2ndWayToGoalResult().shouldBe(expectedResult2ndWayToGoal) },
            { AoC2022Day24BlizzardBasin(filename).processPart2().getTotalWayResult().shouldBe(expectedResultTotal) }
        )

    @ParameterizedTest
    @MethodSource("getDataForTestStartAndEnd")
    fun testStartAndEnd(filename: String, expectedStart: Position2d<Int>, expectedEnd: Position2d<Int>) =
        assertAll(
            { AoC2022Day24BlizzardBasin(filename).getStart().shouldBe(expectedStart) },
            { AoC2022Day24BlizzardBasin(filename).getEnd().shouldBe(expectedEnd) }
        )

    @ParameterizedTest
    @MethodSource("getDataForTestMovesDestinations")
    fun testMovesDestinations(move: AoC2022Day24Moves, expectedResult: Position2d<Int>) =
        move.getDirection(Position2d(0, 0)).shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day24/day24SampleEasy.txt", 10),
                Arguments.of("de/havox_design/aoc2022/day24/day24SampleComplex.txt", 18)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day24/day24SampleEasy.txt", 10, 10, 10, 30),
                Arguments.of("de/havox_design/aoc2022/day24/day24SampleComplex.txt", 18, 23, 13, 54)
            )

        @JvmStatic
        private fun getDataForTestStartAndEnd(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day24/day24SampleEasy.txt", Position2d(1, 0), Position2d(5, 6)),
                Arguments.of("de/havox_design/aoc2022/day24/day24SampleComplex.txt", Position2d(1, 0), Position2d(6, 5))
            )

        @JvmStatic
        private fun getDataForTestMovesDestinations(): Stream<Arguments> =
            Stream.of(
                Arguments.of(AoC2022Day24Moves.NORTH, Position2d(0, -1)),
                Arguments.of(AoC2022Day24Moves.EAST, Position2d(1, 0)),
                Arguments.of(AoC2022Day24Moves.SOUTH, Position2d(0, 1)),
                Arguments.of(AoC2022Day24Moves.WEST, Position2d(-1, 0)),
                Arguments.of(AoC2022Day24Moves.WAIT, Position2d(0, 0))
            )
    }
}

private fun Array<Int>.get1stWayToGoalResult(): Int = this[0]
private fun Array<Int>.getWayBackToStartResult(): Int = this[1]
private fun Array<Int>.get2ndWayToGoalResult(): Int = this[2]
private fun Array<Int>.getTotalWayResult(): Int = this[3]
