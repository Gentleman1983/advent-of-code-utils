package de.havox_design.aoc.utils.kotlin.model.positions.position_3d

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

/**
 * Test for Position3dHelper class.
 *
 * @constructor Creates test
 */
class Position3dHelperTest {
    @ParameterizedTest
    @MethodSource("getDataForTestPosition3dIntAdjacent")
    fun testPosition3dIntAdjacent(offset: Int, expectedResult: Collection<Position3d<Int>>) =
        Position3d(23, 42, 112).adjacent(offset).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestPosition3dIntDistanceTo")
    fun testPosition3dIntDistanceTo(pointA: Position3d<Int>, pointB: Position3d<Int>, expectedResult: Double) =
        assertAll(
            "distance",
            { pointA.distanceTo(pointB).shouldBe(expectedResult) },
            { pointB.distanceTo(pointA).shouldBe(expectedResult) }
        )

    @ParameterizedTest
    @MethodSource("getDataForTestPosition3dIntManhattanDistance")
    fun testPosition3dIntManhattanDistance(pointA: Position3d<Int>, pointB: Position3d<Int>, expectedResult: Int) =
        assertAll(
            "distance",
            { pointA.manhattanDistance(pointB).shouldBe(expectedResult) },
            { pointB.manhattanDistance(pointA).shouldBe(expectedResult) }
        )

    companion object {
        @JvmStatic
        private fun getDataForTestPosition3dIntAdjacent(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    1,
                    listOf(
                        Position3d(x = 22, y = 41, z = 111),
                        Position3d(x = 22, y = 41, z = 112),
                        Position3d(x = 22, y = 41, z = 113),
                        Position3d(x = 22, y = 42, z = 111),
                        Position3d(x = 22, y = 42, z = 112),
                        Position3d(x = 22, y = 42, z = 113),
                        Position3d(x = 22, y = 43, z = 111),
                        Position3d(x = 22, y = 43, z = 112),
                        Position3d(x = 22, y = 43, z = 113),
                        Position3d(x = 23, y = 41, z = 111),
                        Position3d(x = 23, y = 41, z = 112),
                        Position3d(x = 23, y = 41, z = 113),
                        Position3d(x = 23, y = 42, z = 111),
                        Position3d(x = 23, y = 42, z = 112),
                        Position3d(x = 23, y = 42, z = 113),
                        Position3d(x = 23, y = 43, z = 111),
                        Position3d(x = 23, y = 43, z = 112),
                        Position3d(x = 23, y = 43, z = 113),
                        Position3d(x = 24, y = 41, z = 111),
                        Position3d(x = 24, y = 41, z = 112),
                        Position3d(x = 24, y = 41, z = 113),
                        Position3d(x = 24, y = 42, z = 111),
                        Position3d(x = 24, y = 42, z = 112),
                        Position3d(x = 24, y = 42, z = 113),
                        Position3d(x = 24, y = 43, z = 111),
                        Position3d(x = 24, y = 43, z = 112),
                        Position3d(x = 24, y = 43, z = 113)
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestPosition3dIntDistanceTo(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    Position3d(0,0,0),
                    Position3d(0,0,0),
                    0.0
                ),
                Arguments.of(
                    Position3d(0,0,0),
                    Position3d(23,42,112),
                    121.80722474467596
                ),
                Arguments.of(
                    Position3d(-11,22,44),
                    Position3d(11,-22,-44),
                    100.81666528902848
                )
            )

        @JvmStatic
        private fun getDataForTestPosition3dIntManhattanDistance(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    Position3d(0,0,0),
                    Position3d(0,0,0),
                    0
                ),
                Arguments.of(
                    Position3d(0,0,0),
                    Position3d(23,42,112),
                    177
                ),
                Arguments.of(
                    Position3d(-11,22,44),
                    Position3d(11,-22,-44),
                    154
                )
            )
    }
}