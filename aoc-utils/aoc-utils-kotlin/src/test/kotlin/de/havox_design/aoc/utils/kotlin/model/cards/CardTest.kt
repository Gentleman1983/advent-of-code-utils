package de.havox_design.aoc.utils.kotlin.model.cards

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Test for Card class.
 */
class CardTest {
    @Test
    fun testThrowsExceptionOnUnknownSymbol() {
        val unknownSymbol: Char = 'x'
        val expectedMessage = "No knows card symbol '${unknownSymbol}'"

        val exception = assertThrows(IllegalArgumentException::class.java) { Card.from(unknownSymbol) }
        assertEquals(expectedMessage, exception.message)
    }
}