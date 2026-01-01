package de.havox_design.aoc.utils.java.model.programs.duet.instructions;

import de.havox_design.aoc.utils.java.model.programs.duet.State;

import java.math.BigInteger;

/**
 * The add {@link Instruction}.
 */
public class Receive implements Instruction {
    /**
     * The variable name.
     */
    private final String variableName;

    /**
     * The Constructor.
     *
     * @param value the value
     */
    public Receive(final String value) {
        variableName = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final State state) {
        final BigInteger value = state.receiveValue();

        if (value == null) {
            state.setRunning(false);
        } else {
            state.setValue(variableName, value);
        }
    }
}
