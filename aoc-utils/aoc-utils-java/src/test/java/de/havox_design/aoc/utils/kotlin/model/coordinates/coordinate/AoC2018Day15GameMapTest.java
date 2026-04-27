package de.havox_design.aoc.utils.kotlin.model.coordinates.coordinate;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class AoC2018Day15GameMapTest {
    @Test
    void testContracts() {
        EqualsVerifier
                .forClass(AoC2018Day15GameMap.class)
                .verify();
    }
}
