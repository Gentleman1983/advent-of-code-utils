package de.havox_design.aoc.utils.java.model.programs.duet.token;

import de.havox_design.aoc.utils.java.model.programs.duet.State;

import java.math.BigInteger;

/**
 * The token interface.
 */
public interface Token {
    /**
     * Calculates the {@link BigInteger} value of the given {@link State}.
     *
     * @param state the state
     * @return the value
     */
    BigInteger intValue(State state);
}
