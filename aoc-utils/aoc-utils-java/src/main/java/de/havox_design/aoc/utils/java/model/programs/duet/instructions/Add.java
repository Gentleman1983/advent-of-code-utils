package de.havox_design.aoc.utils.java.model.programs.duet.instructions;

import de.havox_design.aoc.utils.java.model.programs.duet.State;
import de.havox_design.aoc.utils.java.model.programs.duet.token.Token;
import de.havox_design.aoc.utils.java.model.programs.duet.token.TokenProvider;

import java.math.BigInteger;

/**
 * The add {@link Instruction}.
 */
public class Add implements Instruction {
    /**
     * The {@link Token} added.
     */
    private final Token addend;
    /**
     * The variable name.
     */
    private final String variableName;

    /**
     * Constructor.
     *
     * @param variableName the variable name
     * @param value the value
     */
    public Add(final String variableName, final String value) {
        this.variableName = variableName;
        this.addend = TokenProvider.createToken(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final State state) {
        BigInteger currentValue = state.getValue(variableName);

        currentValue = currentValue.add(addend.intValue(state));
        state.setValue(variableName, currentValue);
    }
}
