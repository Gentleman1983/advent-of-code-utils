package de.havox_design.aoc.utils.kotlin.helpers

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

/**
 * Test for StringHelper class.
 *
 * @constructor Creates test
 */
class StringHelperTest {
    @ParameterizedTest
    @MethodSource("getDataForToTestIsUpperCase")
    fun testIsUpperCase(objectUnderTest: String, expectedValue: Boolean) {
        assertEquals(objectUnderTest.isUpperCase(), expectedValue)
    }

    @ParameterizedTest
    @MethodSource("getDataForTestIsLowerCase")
    fun testIsLowerCase(objectUnderTest: String, expectedValue: Boolean) {
        assertEquals(objectUnderTest.isLowerCase(), expectedValue)
    }

    @ParameterizedTest
    @MethodSource("getDataForTestParseInts")
    fun testParseInts(objectUnderTest: String, delimiters: Array<String>, radix: Int = 10, expectedValue: List<Int>) {
        assertEquals(objectUnderTest.parseInts(*delimiters, radix = radix), expectedValue)
    }

    companion object {
        @JvmStatic
        private fun getDataForToTestIsUpperCase(): Stream<Arguments> =
            Stream.of(
                Arguments.of("COMPLETEUPPERCASE", true),
                Arguments.of("UPPER_CASE_WITH_UNDERSCORE", true),
                Arguments.of("CamelCase", false),
                Arguments.of("lower_case_with_underscore", false),
                Arguments.of("completelowercase", false)
            )

        @JvmStatic
        private fun getDataForTestIsLowerCase(): Stream<Arguments> =
            Stream.of(
                Arguments.of("COMPLETEUPPERCASE", false),
                Arguments.of("UPPER_CASE_WITH_UNDERSCORE", false),
                Arguments.of("CamelCase", false),
                Arguments.of("lower_case_with_underscore", true),
                Arguments.of("completelowercase", true)
            )

        @JvmStatic
        private fun getDataForTestParseInts(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "10,9,8,7,6,5,4,3,2,1,0",
                    arrayOf(","),
                    10,
                    listOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0)
                ),
                Arguments.of(
                    "1 , 2,3",
                    arrayOf(","),
                    10,
                    listOf(1, 2, 3)
                ),
                Arguments.of(
                    "1,2,3",
                    arrayOf(";"),
                    10,
                    emptyList<Int>()
                ),
                Arguments.of(
                    "1,2;3",
                    arrayOf(",", ";"),
                    10,
                    listOf(1, 2, 3)
                ),
                Arguments.of(
                    "e,a,v",
                    arrayOf(","),
                    10,
                    emptyList<Int>()
                )
            )
    }
}