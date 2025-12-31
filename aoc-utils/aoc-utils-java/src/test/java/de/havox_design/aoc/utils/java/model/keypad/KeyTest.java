package de.havox_design.aoc.utils.java.model.keypad;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link Key} class.
 */
class KeyTest {
    /**
     * Tests the equals contract.
     */
    @Test
    void equalsContract() {
        EqualsVerifier.forClass(Key.class).verify();
    }
}
