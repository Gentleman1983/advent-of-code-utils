package de.havox_design.aoc.utils.kotlin.model.positions.position_2d

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

/**
 * Test for Position2dHelper class.
 *
 * @constructor Creates test
 */
class Position2dHelperTest {
    @ParameterizedTest
    @MethodSource("getDataForTestPosition2dIntMinus")
    fun testPosition2dIntMinus(pointA: Position2d<Int>, pointB: Position2d<Int>, expectedResult: Position2d<Int>) =
        (pointA - pointB).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestPosition2dIntClockwise")
    fun testPosition2dIntClockwise(pointA: Position2d<Int>, degrees: Int, expectedResult: Position2d<Int>) =
        pointA.clockwise(degrees).shouldBe(expectedResult)

    @Test
    fun testPosition2dIntClockwiseInvalidDegrees() {
        assertThrows<UnsupportedOperationException> { Position2d(23, 42).clockwise(42) }
    }

    @ParameterizedTest
    @MethodSource("getDataForTestPosition2dIntCounterClockwise")
    fun testPosition2dIntCounterClockwise(pointA: Position2d<Int>, degrees: Int, expectedResult: Position2d<Int>) =
        pointA.counterClockwise(degrees).shouldBe(expectedResult)

    @Test
    fun testPosition2dIntCounterClockwiseInvalidDegrees() {
        assertThrows<UnsupportedOperationException> { Position2d(23, 42).counterClockwise(42) }
    }

    @ParameterizedTest
    @MethodSource("getDataForTestPosition2dIntDistance")
    fun testPosition2dIntDistance(pointA: Position2d<Int>, pointB: Position2d<Int>, expectedResult: Int) =
        pointA.distance(pointB).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestPosition2dLongDistance")
    fun testPosition2dLongDistance(pointA: Position2d<Long>, pointB: Position2d<Long>, expectedResult: Long) =
        pointA.distance(pointB).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestPosition2dIntNeighbors")
    fun testPosition2dIntNeighbors(point: Position2d<Int>, expectedResult: List<Position2d<Int>>) =
        point.neighbours().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestPosition2dIntDiagonalNeighbors")
    fun testPosition2dIntDiagonalNeighbors(point: Position2d<Int>, expectedResult: List<Position2d<Int>>) =
        point.diagonalNeighbours().shouldBe(expectedResult)

    @Test
    fun testPosition2dIntAllDirections() {
        assertAll(
            "directions",
            { Position2d(0, 0).north().shouldBe(Position2d(x = 0, y = -1)) },
            { Position2d(0, 0).northeast().shouldBe(Position2d(x = 1, y = -1)) },
            { Position2d(0, 0).east().shouldBe(Position2d(x = 1, y = 0)) },
            { Position2d(0, 0).southeast().shouldBe(Position2d(x = 1, y = 1)) },
            { Position2d(0, 0).south().shouldBe(Position2d(x = 0, y = 1)) },
            { Position2d(0, 0).southwest().shouldBe(Position2d(x = -1, y = 1)) },
            { Position2d(0, 0).west().shouldBe(Position2d(x = -1, y = 0)) },
            { Position2d(0, 0).northwest().shouldBe(Position2d(x = -1, y = -1)) }
        )
    }

    companion object {
        @JvmStatic
        private fun getDataForTestPosition2dIntMinus(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    Position2d(0, 0),
                    Position2d(0, 0),
                    Position2d(0, 0)
                ),
                Arguments.of(
                    Position2d(0, 0),
                    Position2d(23, 42),
                    Position2d(x = -23, y = -42)
                ),
                Arguments.of(
                    Position2d(-11, 22),
                    Position2d(11, -22),
                    Position2d(x = -22, y = 44)
                )
            )

        @JvmStatic
        private fun getDataForTestPosition2dIntClockwise(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    Position2d(23, 42),
                    90,
                    Position2d(x = 42, y = -23)
                ),
                Arguments.of(
                    Position2d(23, 42),
                    180,
                    Position2d(x = -23, y = -42)
                ),
                Arguments.of(
                    Position2d(23, 42),
                    270,
                    Position2d(x = -42, y = 23)
                )
            )

        @JvmStatic
        private fun getDataForTestPosition2dIntCounterClockwise(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    Position2d(23, 42),
                    90,
                    Position2d(x = -42, y = 23)
                ),
                Arguments.of(
                    Position2d(23, 42),
                    180,
                    Position2d(x = -23, y = -42)
                ),
                Arguments.of(
                    Position2d(23, 42),
                    270,
                    Position2d(x = 42, y = -23)
                )
            )

        @JvmStatic
        private fun getDataForTestPosition2dIntDistance(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    Position2d(0, 0),
                    Position2d(0, 0),
                    0
                ),
                Arguments.of(
                    Position2d(0, 0),
                    Position2d(23, 42),
                    65
                ),
                Arguments.of(
                    Position2d(-11, 22),
                    Position2d(11, -22),
                    66
                )
            )

        @JvmStatic
        private fun getDataForTestPosition2dLongDistance(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    Position2d(0L, 0L),
                    Position2d(0L, 0L),
                    0L
                ),
                Arguments.of(
                    Position2d(0L, 0L),
                    Position2d(23L, 42L),
                    65L
                ),
                Arguments.of(
                    Position2d(-11L, 22L),
                    Position2d(11L, -22L),
                    66L
                )
            )

        @JvmStatic
        private fun getDataForTestPosition2dIntNeighbors(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    Position2d(0, 0),
                    listOf(
                        Position2d(x = 1, y = 0),
                        Position2d(x = -1, y = 0),
                        Position2d(x = 0, y = 1),
                        Position2d(x = 0, y = -1)
                    )
                ),
                Arguments.of(
                    Position2d(23, 42),
                    listOf(
                        Position2d(x = 24, y = 42),
                        Position2d(x = 22, y = 42),
                        Position2d(x = 23, y = 43),
                        Position2d(x = 23, y = 41)
                    )
                ),
                Arguments.of(
                    Position2d(11, -22),
                    listOf(
                        Position2d(x = 12, y = -22),
                        Position2d(x = 10, y = -22),
                        Position2d(x = 11, y = -21),
                        Position2d(x = 11, y = -23)
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestPosition2dIntDiagonalNeighbors(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    Position2d(0, 0),
                    listOf(
                        Position2d(x = 1, y = 1),
                        Position2d(x = 1, y = -1),
                        Position2d(x = -1, y = 1),
                        Position2d(x = -1, y = -1)
                    )
                ),
                Arguments.of(
                    Position2d(23, 42),
                    listOf(
                        Position2d(x = 24, y = 43),
                        Position2d(x = 24, y = 41),
                        Position2d(x = 22, y = 43),
                        Position2d(x = 22, y = 41)
                    )
                ),
                Arguments.of(
                    Position2d(11, -22),
                    listOf(
                        Position2d(x = 12, y = -21),
                        Position2d(x = 12, y = -23),
                        Position2d(x = 10, y = -21),
                        Position2d(x = 10, y = -23)
                    )
                )
            )
    }
}