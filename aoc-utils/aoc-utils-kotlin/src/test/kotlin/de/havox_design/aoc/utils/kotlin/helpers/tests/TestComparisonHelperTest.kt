package de.havox_design.aoc.utils.kotlin.helpers.tests

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigInteger

/**
 * Test for TestComparisonHelper class.
 *
 * @constructor Creates test
 */
class TestComparisonHelperTest {
    @Test
    fun testShouldBeBigInter() {
        val objectUnderTest = BigInteger.valueOf(42);
        val expectedValue = BigInteger.valueOf(42);

        objectUnderTest.shouldBe(expectedValue)
    }

    @Test
    fun testShouldBeString() {
        val objectUnderTest = "Hello World";
        val expectedValue = "Hello World";

        objectUnderTest.shouldBe(expectedValue)
    }

    @Test
    fun testShouldBeMap() {
        val objectUnderTest = mapOf("Hello" to "World", "first" to "here", "last" to "here");
        val expectedValue = mapOf("Hello" to "World", "last" to "here", "first" to "here");

        objectUnderTest.shouldBe(expectedValue)
    }

    @Test
    fun testShouldContainAllCollection() {
        val objectUnderTest = listOf("Hello", "here", "there", "World").toCollection(ArrayList<String>());
        val expectedValue = listOf("Hello", "World").toCollection(ArrayList<String>());

        objectUnderTest.shouldContainAll(expectedValue)
    }

    @Test
    fun testShouldContainAllCollectionMessage() {
        val objectUnderTest = listOf("Hello", "here", "there", "World").toCollection(ArrayList<String>());
        val expectedValue = listOf("Hello", "World").toCollection(ArrayList<String>());

        objectUnderTest.shouldContainAll(expectedValue, "foobar")
    }
}