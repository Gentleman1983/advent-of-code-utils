package de.havox_design.aoc.utils.java.model.programs.duet.token;

import de.havox_design.aoc.utils.java.model.programs.duet.State;

import java.math.BigInteger;

/**
 * The representation of a variable {@link Token}.
 */
public class Variable implements Token {
    /**
     * The variable name.
     */
    private final String name;

    /**
     * The constructor for the variable {@link Token}.
     *
     * @param name the name
     */
    public Variable(final String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigInteger intValue(final State state) {
        return state.getValue(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return name;
    }
}
