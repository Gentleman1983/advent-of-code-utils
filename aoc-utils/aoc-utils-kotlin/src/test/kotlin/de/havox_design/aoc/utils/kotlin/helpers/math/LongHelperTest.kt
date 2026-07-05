package de.havox_design.aoc.utils.kotlin.helpers.math

import de.havox_design.aoc.utils.kotlin.helpers.isUpperCase
import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

/**
 * Test for LongHelper class.
 */
class LongHelperTest {
    @ParameterizedTest
    @MethodSource("getDataForTestAbsoluteMaximum")
    fun testAbsoluteMaximum(long1: Long, long2: Long, expectedValue: Long) {
        absoluteMax(long1, long2).shouldBe(expectedValue)
    }

    @ParameterizedTest
    @MethodSource("getDataForTestAbsoluteMinimum")
    fun testAbsoluteMinimum(long1: Long, long2: Long, expectedValue: Long) {
        absoluteMin(long1, long2).shouldBe(expectedValue)
    }

    @ParameterizedTest
    @MethodSource("getDataForTestPowInt")
    fun testPowInt(objectUnderTest: Int, expectedValue: Long) {
        2L.pow(objectUnderTest).shouldBe(expectedValue)
    }

    @ParameterizedTest
    @MethodSource("getDataForTestIterableLongProduct")
    fun testIterableLongProduct(objectUnderTest: Iterable<Long>, expectedValue: Long) {
        objectUnderTest.product().shouldBe(expectedValue)
    }

    companion object {
        @JvmStatic
        private fun getDataForTestAbsoluteMaximum(): Stream<Arguments> =
            Stream.of(
                Arguments.of(0L, 0L, 0L),
                Arguments.of(1L, 2L, 2L),
                Arguments.of(3L, 2L, 3L),
                Arguments.of(-1L, 2L, 2L),
                Arguments.of(1L, -2L, 2L),
                Arguments.of(-1L, -2L, 2L)
            )

        @JvmStatic
        private fun getDataForTestAbsoluteMinimum(): Stream<Arguments> =
            Stream.of(
                Arguments.of(0L, 0L, 0L),
                Arguments.of(1L, 2L, 1L),
                Arguments.of(3L, 2L, 2L),
                Arguments.of(-1L, 2L, 1L),
                Arguments.of(1L, -2L, 1L),
                Arguments.of(-1L, -2L, 1L)
            )

        @JvmStatic
        private fun getDataForTestPowInt(): Stream<Arguments> =
            Stream.of(
                Arguments.of(0, 1L),
                Arguments.of(1, 2L),
                Arguments.of(2, 4L),
                Arguments.of(3, 8L),
                Arguments.of(4, 16L),
                Arguments.of(5, 32L),
                Arguments.of(6, 64L),
                Arguments.of(7, 128L),
                Arguments.of(8, 256L),
                Arguments.of(9, 512L),
                Arguments.of(10, 1_024L)
            )

        @JvmStatic
        private fun getDataForTestIterableLongProduct(): Stream<Arguments> =
            Stream.of(
                Arguments.of(listOf(1L), 1L),
                Arguments.of(listOf(1L, 1L), 1L),
                Arguments.of(listOf(2L, 2L), 4L),
                Arguments.of(listOf(2L, 4L), 8L),
                Arguments.of(listOf(2L, 2L, 4L), 16L)
            )
    }
}