package de.havox_design.aoc.utils.java.model.programs.duet.instructions;

import de.havox_design.aoc.utils.java.model.programs.duet.State;
import de.havox_design.aoc.utils.java.model.programs.duet.token.Token;
import de.havox_design.aoc.utils.java.model.programs.duet.token.TokenProvider;

import java.math.BigInteger;

/**
 * The modulo {@link Instruction}.
 */
public class Modulo implements Instruction {
    /**
     * The modulus {@link Token}.
     */
    private final Token modulus;
    /**
     * The variable name.
     */
    private final String variableName;

    /**
     * The Constructor.
     *
     * @param variableName the variable name
     * @param value the value
     */
    public Modulo(final String variableName, final String value) {
        this.variableName = variableName;
        this.modulus = TokenProvider.createToken(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final State state) {
        BigInteger currentValue = state.getValue(variableName);

        currentValue = currentValue.mod(modulus.intValue(state));
        state.setValue(variableName, currentValue);
    }
}
