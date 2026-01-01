package de.havox_design.aoc.utils.java.model.programs.duet.instructions;

import de.havox_design.aoc.utils.java.model.programs.duet.State;
import de.havox_design.aoc.utils.java.model.programs.duet.token.Token;
import de.havox_design.aoc.utils.java.model.programs.duet.token.TokenProvider;

/**
 * The add {@link Instruction}.
 */
public class Send implements Instruction {
    /**
     * The value {@link Token}.
     */
    private final Token value;

    /**
     * The Constructor.
     *
     * @param value the value
     */
    public Send(final String value) {
        this.value = TokenProvider.createToken(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final State state) {
        state.send(value.intValue(state));
    }
}
