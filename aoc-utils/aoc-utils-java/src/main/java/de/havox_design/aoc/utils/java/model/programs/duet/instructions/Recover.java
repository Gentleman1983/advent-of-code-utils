package de.havox_design.aoc.utils.java.model.programs.duet.instructions;

import de.havox_design.aoc.utils.java.model.programs.duet.State;
import de.havox_design.aoc.utils.java.model.programs.duet.token.Token;
import de.havox_design.aoc.utils.java.model.programs.duet.token.TokenProvider;

import java.math.BigInteger;

/**
 * The add {@link Instruction}.
 */
public class Recover implements Instruction {
    /**
     * The value {@link Token}.
     */
    private final Token value;

    /**
     * The Constructor.
     *
     * @param value the value
     */
    public Recover(final String value) {
        this.value = TokenProvider.createToken(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final State state) {
        final BigInteger intValue = value.intValue(state);

        if (intValue.signum() != 0) {
            final BigInteger currentSound = state.getCurrentSound();

            state.recoverSound(currentSound);
        }
    }
}
