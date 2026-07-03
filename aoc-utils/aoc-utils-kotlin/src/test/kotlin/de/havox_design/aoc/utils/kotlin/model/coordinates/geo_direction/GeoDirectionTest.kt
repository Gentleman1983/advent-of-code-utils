package de.havox_design.aoc.utils.kotlin.model.coordinates.geo_direction

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import de.havox_design.aoc.utils.kotlin.model.positions.position_2d.Position2d
import de.havox_design.aoc.utils.kotlin.model.positions.position_2d.minus
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

/**
 * Test for GeoDirection class.
 *
 * @constructor Creates test
 */
class GeoDirectionTest {
    @ParameterizedTest
    @MethodSource("getDataForTestNavigation")
    fun testNavigation(
        objectUnderTest: GeoDirection,
        expectedLeft: GeoDirection,
        expectedOpposite: GeoDirection,
        expectedRight: GeoDirection
    ) =
        assertAll(
            "directions",
            { objectUnderTest.left.shouldBe(expectedLeft) },
            { objectUnderTest.opposite.shouldBe(expectedOpposite) },
            { objectUnderTest.right.shouldBe(expectedRight) }
        )


    @ParameterizedTest
    @MethodSource("getDataForTestTurn")
    fun testTurn(
        objectUnderTest: GeoDirection,
        expectedLeft: GeoDirection,
        expectedOpposite: GeoDirection,
        expectedRight: GeoDirection,
        times: Int
    ) =
        assertAll(
            "directions",
            { objectUnderTest.left(times).shouldBe(expectedLeft) },
            { objectUnderTest.opposite(times).shouldBe(expectedOpposite) },
            { objectUnderTest.right(times).shouldBe(expectedRight) }
        )

    companion object {
        @JvmStatic
        private fun getDataForTestNavigation(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    GeoDirection.NORTH,
                    GeoDirection.WEST,
                    GeoDirection.SOUTH,
                    GeoDirection.EAST
                ),
                Arguments.of(
                    GeoDirection.SOUTH,
                    GeoDirection.EAST,
                    GeoDirection.NORTH,
                    GeoDirection.WEST
                ),
                Arguments.of(
                    GeoDirection.WEST,
                    GeoDirection.SOUTH,
                    GeoDirection.EAST,
                    GeoDirection.NORTH
                ),
                Arguments.of(
                    GeoDirection.EAST,
                    GeoDirection.NORTH,
                    GeoDirection.WEST,
                    GeoDirection.SOUTH
                ),
                Arguments.of(
                    GeoDirection.NONE,
                    GeoDirection.NONE,
                    GeoDirection.NONE,
                    GeoDirection.NONE
                )
            )

        @JvmStatic
        private fun getDataForTestTurn(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    GeoDirection.NORTH,
                    GeoDirection.WEST,
                    GeoDirection.SOUTH,
                    GeoDirection.EAST,
                    1
                ),
                Arguments.of(
                    GeoDirection.NORTH,
                    GeoDirection.SOUTH,
                    GeoDirection.EAST,
                    GeoDirection.SOUTH,
                    2
                ),
                Arguments.of(
                    GeoDirection.NORTH,
                    GeoDirection.EAST,
                    GeoDirection.NORTH,
                    GeoDirection.WEST,
                    3
                ),
                Arguments.of(
                    GeoDirection.NORTH,
                    GeoDirection.NORTH,
                    GeoDirection.WEST,
                    GeoDirection.NORTH,
                    4
                ),
                Arguments.of(
                    GeoDirection.NORTH,
                    GeoDirection.WEST,
                    GeoDirection.SOUTH,
                    GeoDirection.EAST,
                    5
                )
            )
    }
}