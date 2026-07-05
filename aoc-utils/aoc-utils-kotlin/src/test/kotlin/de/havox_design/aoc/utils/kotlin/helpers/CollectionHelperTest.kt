package de.havox_design.aoc.utils.kotlin.helpers

import de.havox_design.aoc.utils.kotlin.helpers.math.product
import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldContainAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertInstanceOf
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.LinkedList
import java.util.stream.Stream

/**
 * Test for CollectionHelper class.
 */
class CollectionHelperTest {
    @Test
    fun testLinkedListOfElements() {
        val objectUnderTest = linkedListOf(1L, 2L, 3L, 4L)
        val elements = arrayOf(1L, 2L, 3L, 4L)

        assertAll(
            "linkedListOfElements",
            { assertNotNull(objectUnderTest) },
            { assertInstanceOf<LinkedList<Long>>(objectUnderTest) },
            { assertEquals(elements.size, objectUnderTest.size) },
            { objectUnderTest.shouldContainAll(elements.toList()) }
        )
    }

    @ParameterizedTest
    @MethodSource("getDataForTestCollectionStringFilterNotEmpty")
    fun testCollectionStringFilterNotEmpty(objectUnderTest: Collection<String>, expectedValue: Collection<String>) {
        objectUnderTest.filterNotEmpty().shouldBe(expectedValue)
    }

    @ParameterizedTest
    @MethodSource("getDataForTestIntArrayAbs")
    fun testIntArrayAbs(objectUnderTest: IntArray, expectedValue: Int) {
        objectUnderTest.abs.shouldBe(expectedValue)
    }

    @ParameterizedTest
    @MethodSource("getDataForTestIterableMinAndMax")
    fun testIterableMinAndMax(objectUnderTest: Iterable<Int>, expectedValue: Pair<Int, Int>, isEmpty: Boolean) {
        if (isEmpty) {
            assertThrows<IllegalArgumentException> { objectUnderTest.minAndMax() }
        } else {
            objectUnderTest.minAndMax().shouldBe(expectedValue)
        }
    }

    @ParameterizedTest
    @MethodSource("getDataForTestIterableIntProduct")
    fun testIterableIntProduct(objectUnderTest: Iterable<Int>, expectedValue: Int) {
        objectUnderTest.product().shouldBe(expectedValue)
    }

    companion object {
        @JvmStatic
        private fun getDataForTestCollectionStringFilterNotEmpty(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf("Hello", "World", "!"),
                    listOf("Hello", "World", "!")
                ),
                Arguments.of(
                    listOf("Hello", "empty", "", "World", "!"),
                    listOf("Hello", "empty", "World", "!")
                ),
                Arguments.of(
                    listOf("", " "),
                    listOf(" "),
                ),
                Arguments.of(
                    listOf(""),
                    emptyList<String>()
                )
            )

        @JvmStatic
        private fun getDataForTestIntArrayAbs(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    intArrayOf(1),
                    1
                ),
                Arguments.of(
                    intArrayOf(1, 2, 3, 4),
                    10
                ),
                Arguments.of(
                    intArrayOf(-1, 2, -3, 4),
                    10,
                ),
                Arguments.of(
                    intArrayOf(),
                    0
                )
            )

        @JvmStatic
        private fun getDataForTestIterableMinAndMax(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(1),
                    Pair(1, 1),
                    false
                ),
                Arguments.of(
                    emptyList<Int>(),
                    Pair(0, 0),
                    true
                ),
                Arguments.of(
                    listOf(1, 2, 3, 4),
                    Pair(1, 4),
                    false
                ),
                Arguments.of(
                    listOf(-1, 2, -3, 4),
                    Pair(-3, 4),
                    false
                )
            )

        @JvmStatic
        private fun getDataForTestIterableIntProduct(): Stream<Arguments> =
            Stream.of(
                Arguments.of(listOf(1), 1),
                Arguments.of(listOf(1, 1), 1),
                Arguments.of(listOf(2, 2), 4),
                Arguments.of(listOf(2, 4), 8),
                Arguments.of(listOf(2, 2, 4), 16)
            )
    }
}
