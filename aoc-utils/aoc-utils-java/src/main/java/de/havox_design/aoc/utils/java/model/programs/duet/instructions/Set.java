package de.havox_design.aoc.utils.java.model.programs.duet.instructions;

import de.havox_design.aoc.utils.java.model.programs.duet.State;
import de.havox_design.aoc.utils.java.model.programs.duet.token.Token;
import de.havox_design.aoc.utils.java.model.programs.duet.token.TokenProvider;

/**
 * The add {@link Instruction}.
 */
public class Set implements Instruction {
    /**
     * The value {@link Token}.
     */
    private final Token value;
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
    public Set(final String variableName, final String value) {
        this.variableName = variableName;
        this.value = TokenProvider.createToken(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final State state) {
        state.setValue(variableName, value.intValue(state));
    }

    /**
     * Returns the value.
     *
     * @return the value
     */
    public Token getValue() {
        return value;
    }
}
