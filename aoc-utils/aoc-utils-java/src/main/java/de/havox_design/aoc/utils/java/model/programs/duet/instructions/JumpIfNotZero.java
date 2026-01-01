package de.havox_design.aoc.utils.java.model.programs.duet.instructions;

import de.havox_design.aoc.utils.java.model.programs.duet.State;
import de.havox_design.aoc.utils.java.model.programs.duet.token.Token;
import de.havox_design.aoc.utils.java.model.programs.duet.token.TokenProvider;

import java.math.BigInteger;

/**
 * The jump if not zero {@link Instruction}.
 */
public class JumpIfNotZero implements Instruction {
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
     * @param cond
     * @param offs
     */
    public JumpIfNotZero(final String cond, final String offs) {
        this.condition = TokenProvider.createToken(cond);
        this.offset = TokenProvider.createToken(offs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final State state) {
        final BigInteger intValue = condition.intValue(state);

        if (intValue.signum() != 0) {
            state.jump(offset.intValue(state).intValueExact());
        }
    }
}
