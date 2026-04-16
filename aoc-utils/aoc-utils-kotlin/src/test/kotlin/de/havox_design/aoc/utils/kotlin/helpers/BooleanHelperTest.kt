package de.havox_design.aoc.utils.kotlin.helpers

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


class BooleanHelperTest {
    @ParameterizedTest
    @MethodSource("getDataForToIntTests")
    fun testToInt(objectUnderTest: Boolean, expectedValue: Int) {
        assertEquals(objectUnderTest.toInt(), expectedValue)
    }

    companion object{
        @JvmStatic
        private fun getDataForToIntTests(): Stream<Arguments> =
            Stream.of(
                Arguments.of(true, 1),
                Arguments.of(false, 0)
            )
    }
}
