package de.havox_design.aoc.utils.java.model.programs.jsonparser;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link JSONString} class.
 */
class JSONStringTest {
    /**
     * Tests the equals contract.
     */
    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(JSONString.class).withNonnullFields("string").verify();
    }
}
