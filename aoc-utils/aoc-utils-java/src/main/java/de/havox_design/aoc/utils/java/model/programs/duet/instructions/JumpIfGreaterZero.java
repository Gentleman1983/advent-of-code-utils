package de.havox_design.aoc.utils.java.model.programs.duet.instructions;

import de.havox_design.aoc.utils.java.model.programs.duet.State;
import de.havox_design.aoc.utils.java.model.programs.duet.token.Token;
import de.havox_design.aoc.utils.java.model.programs.duet.token.TokenProvider;

import java.math.BigInteger;

/**
 * The jup if greater zero {@link Instruction}.
 */
public class JumpIfGreaterZero implements Instruction {
    /**
     * The condition {@link Token}.
     */
    private final Token condition;
    /**
     * The offset {@link Token}.
     */
    private final Token offset;

    /**
     * The Constructor.
     *
     * @param condition the condition
     * @param offset the offset
     */
    public JumpIfGreaterZero(final String condition, final String offset) {
        this.condition = TokenProvider.createToken(condition);
        this.offset = TokenProvider.createToken(offset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final State state) {
        final BigInteger intValue = condition.intValue(state);

        if (intValue.signum() > 0) {
            state.jump(offset.intValue(state).intValueExact());
        }
    }
}
