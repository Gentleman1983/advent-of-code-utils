package de.havox_design.aoc.utils.java.model.programs.duet.instructions;

import de.havox_design.aoc.utils.java.model.programs.duet.State;
import de.havox_design.aoc.utils.java.model.programs.duet.token.Token;
import de.havox_design.aoc.utils.java.model.programs.duet.token.TokenProvider;

import java.math.BigInteger;

/**
 * The add {@link Instruction}.
 */
public class Substract implements Instruction {
    /**
     * The variable name.
     */
    private final String variableName;
    /**
     * The subtrahend {@link Token}.
     */
    private final Token subtrahend;

    /**
     * The Constructor.
     *
     * @param variableName the variable name
     * @param value the value
     */
    public Substract(final String variableName, final String value) {
        this.variableName = variableName;
        this.subtrahend = TokenProvider.createToken(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final State state) {
        BigInteger currentValue = state.getValue(variableName);

        currentValue = currentValue.subtract(subtrahend.intValue(state));

        state.setValue(variableName, currentValue);
    }
}
