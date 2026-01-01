package de.havox_design.aoc.utils.java.model.programs.duet.token;

import de.havox_design.aoc.utils.java.model.programs.duet.State;

import java.math.BigInteger;

/**
 * The representation of a constant {@link Token}.
 */
public class Constant implements Token {
    /**
     * The value.
     */
    private final BigInteger value;

    /**
     * Constructor for the constant {@link Token}.
     *
     * @param value the value
     */
    public Constant(final BigInteger value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigInteger intValue(final State state) {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return value.toString();
    }
}
